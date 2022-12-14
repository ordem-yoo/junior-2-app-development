package kr.ac.mjc.rental_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class GooglemapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private IntentIntegrator qrCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.googlemap_screen);

        Button rentalBtn = findViewById(R.id.rental_Btn);
        Button recordBtn = findViewById(R.id.record_Btn);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        qrCode = new IntentIntegrator(this);
        qrCode.setOrientationLocked(false);
        qrCode.setPrompt("Please take a picture of the QR code to fit the square");
        qrCode.setBeepEnabled(false);

        rentalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qrCode.initiateScan();
            }
        });

        recordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GooglemapActivity.this, RecordActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        mMap = googleMap;
        LatLng Seoul = new LatLng(37.5846465, 126.9251874);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(Seoul);
        markerOptions.title("명지전문대학");
        markerOptions.snippet("현재 위치");
        mMap.addMarker(markerOptions);

        mMap.moveCamera((CameraUpdateFactory.newLatLngZoom(Seoul, 17)));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                Intent contentIntent = new Intent(GooglemapActivity.this, CheckTimeActivity.class);
                contentIntent.putExtra("Device Number", result.getContents());
                startActivity(contentIntent);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, intent);
        }
    }
}
