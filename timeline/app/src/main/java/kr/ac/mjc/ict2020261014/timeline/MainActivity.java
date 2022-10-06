package kr.ac.mjc.ict2020261014.timeline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    ArrayList<Photo> mPhotoList = new ArrayList<>();
    TimelineAdapter mTimelineAdapter;

    Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        RecyclerView timelineRv= findViewById(R.id.timeline_rv);
        ViewPager2 viewPager = findViewById(R.id.viewpager);

        mTimelineAdapter = new TimelineAdapter(mPhotoList);
//        timelineRv.setAdapter(mTimelineAdapter);
//        timelineRv.setLayoutManager(new LinearLayoutManager(this));
        viewPager.setAdapter(mTimelineAdapter);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .get()
                .url("https://api.thecatapi.com/v1/images/search?limit=10")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d("MainActivity",e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String body=response.body().string();
                Log.d("MainActivity",body);
                ArrayList<Photo> photoList;
                Type listType = new TypeToken<ArrayList<Photo>>(){}.getType();
                Gson gson = new Gson();
                photoList = gson.fromJson(body,listType);

//                for(Photo photo:photoList){
//                    Log.d("MainActivity",photo.getUrl());
//                }

                mPhotoList.addAll(photoList);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mTimelineAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }
}