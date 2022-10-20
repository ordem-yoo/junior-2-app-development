package kr.ac.mjc.ict2020261014.googlemap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap mGoogleMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        EditText keywordEt=findViewById(R.id.keyword_et);
        keywordEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i== EditorInfo.IME_ACTION_SEARCH) {
                    String keyword = keywordEt.getText().toString();
                    Log.d("MainActivity", keyword);
                    if(!keyword.equals("")){
                        search(keyword);
                    }
                }
                return false;
            }
        });
    }

    public void search(String keyword){
        OkHttpClient client = new OkHttpClient();
        Request request=new Request.Builder()
                .get()
                .url("http://dapi.kakao.com/v2/local/search/keyword.json?query="+keyword)
                .header("Authorization","KakaoAK f5188ec17444d1a543de712635d0098b")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d("MainActivity",e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String body = response.body().string();
                Log.d("MainActivity",body);
                Gson gson = new Gson();
                KakaoDocument document = gson.fromJson(body,KakaoDocument.class);
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setDocument(document);
                    }
                });
            }
        });
    }

    public void setDocument(KakaoDocument document){
        LatLngBounds.Builder builder = LatLngBounds.builder();
        for(KakaoLocation location : document.getDocuments()){
            LatLng position =  new LatLng(location.getY(),location.getX());
            MarkerOptions options = new MarkerOptions()
                    .position(position)
                    .title(location.getPlace_name());
            mGoogleMap.addMarker(options);
            builder.include(position);
        }
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 100)
        );
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.mGoogleMap=googleMap;
    }
}