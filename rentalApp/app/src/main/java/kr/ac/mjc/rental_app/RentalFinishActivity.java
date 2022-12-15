package kr.ac.mjc.rental_app;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RentalFinishActivity extends AppCompatActivity {
    String device_num, startTime;
    long usetime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.return_screen);

        // 데이터 가져오기
        Intent intent = getIntent();
        device_num = intent.getStringExtra("Device Number");
        startTime = intent.getStringExtra("Start Time");
        usetime = intent.getLongExtra("Rental Time", 0);

        // 뷰 지정
        TextView deviceNumTv = findViewById(R.id.deviceNum_Tv);
        TextView rentalTimeTv = findViewById(R.id.rentalTime_Tv);
        TextView rentalCostTv = findViewById(R.id.rentalCost_Tv);
        Button confirmBtn = findViewById(R.id.confirm_Btn);

        // 뷰 데이터 연결
        deviceNumTv.setText(device_num);

        String time = calTime(usetime);
        rentalTimeTv.setText(time);

        String stringTime = Long.toString(calCost());
        rentalCostTv.setText(stringTime+"₩");


        // confirm 버튼을 확인 할 때
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RentalFinishActivity.this, GooglemapActivity.class);
                startActivity(intent);
            }
        });
    }

    // 시간 계산
    String calTime(long time) {
        long hour = 0;
        long min = 0;
        long sec = 0;

        if (time > 3600) {
            hour = time / 3600;
            min = (time % 3600) / 60;
            sec = (time % 3600) % 60;
        } else if (time > 60) {
            min = time / 60;
            sec = time % 60;
        } else if (time < 60) {
            sec = time;
        }

        return hour + "h " + min + "m " + sec + "s ";
    }

    long calCost() {
        return 580+(usetime/60)*120;
    }

}

