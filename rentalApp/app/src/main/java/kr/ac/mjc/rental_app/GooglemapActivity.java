package kr.ac.mjc.rental_app;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class GooglemapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    final static int TAKE_PICTURE = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.googlemap_screen);

        Button rentalBtn = findViewById(R.id.rental_Btn);
        Button recordBtn = findViewById(R.id.record_Btn);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        rentalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                barcodeLauncher.launch(new ScanOptions()
                        .setPrompt("Please take a picture of the QR code to fit the square")
                        
                        );

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
    protected void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        super.onActivityResult(requestCode, resultCode, intent);


    }

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    Toast.makeText(GooglemapActivity.this, "Cancelled", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(GooglemapActivity.this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                }
            });
}
