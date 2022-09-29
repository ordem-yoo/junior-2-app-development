package kr.ac.mjc.ict2020261014.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Student> studentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView studentRv=findViewById(R.id.student_Rv);

        for(int i =0; i<=1000; i++){
            Student student = new Student();
            student.setName("유민상"+i);
            student.setNumber("2020261014"+i);
            studentList.add(student);
        }
        StudentAdapter adapter = new StudentAdapter(studentList);
        studentRv.setAdapter(adapter);

//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        studentRv.setLayoutManager(layoutManager);
    }
}