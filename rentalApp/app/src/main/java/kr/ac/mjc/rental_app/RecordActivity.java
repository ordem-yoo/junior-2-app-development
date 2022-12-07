package kr.ac.mjc.rental_app;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecordActivity extends AppCompatActivity {

    ArrayList<Record> recordList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_screen);

        RecyclerView recordRv=findViewById(R.id.record_Rv);

        for(int i=0; i<=10; i++ ){
            Record record = new Record();
            record.getRental_start();
            record.getRental_finish();
            recordList.add(record);
        }

        RecordAdapter adapter = new RecordAdapter(recordList);
        recordRv.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recordRv.setLayoutManager(layoutManager);
    }
}
