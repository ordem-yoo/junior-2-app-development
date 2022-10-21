package kr.ac.mjc.midterm_2020261014;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RvPage extends AppCompatActivity {
    ArrayList<Student> studentArrayList =new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerviewwidget);

        RecyclerView studentRv=findViewById(R.id.student_rv);

        for(int i=1; i<=100; i++){
            Student student = new Student();
            student.setName("유민상"+i);
            studentArrayList.add(student);
        }
        StudentAdapter adapter = new StudentAdapter(studentArrayList);
        studentRv.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        studentRv.setLayoutManager(layoutManager);
    }
}
