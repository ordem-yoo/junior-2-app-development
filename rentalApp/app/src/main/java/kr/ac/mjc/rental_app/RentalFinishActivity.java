package kr.ac.mjc.rental_app;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RentalFinishActivity extends AppCompatActivity {

    String startTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rental_finish_screen);

        TextView startTimeTV = findViewById(R.id.cnt_startTime_Tv);

        Intent intent = getIntent();
        startTime = getIntent().getStringExtra("StartTime");
        startTimeTV.setText(startTime);
        Log.d("time",startTime);
    }
}

