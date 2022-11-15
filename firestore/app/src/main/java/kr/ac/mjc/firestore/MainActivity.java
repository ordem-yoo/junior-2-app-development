package kr.ac.mjc.firestore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final int REQ_ADD_STUDENT=5198;

    ArrayList<Student> mStudentList = new ArrayList<>();
    StudentAdapter mStudentAdapter;

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addStudentBtn = findViewById(R.id.add_student_btn);
        RecyclerView studentListRv = findViewById(R.id.student_list_rv);

        mStudentAdapter = new StudentAdapter(mStudentList);
        studentListRv.setAdapter(mStudentAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        studentListRv.setLayoutManager(layoutManager);

        addStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddStudentActivity.class);
                startActivityForResult(intent,REQ_ADD_STUDENT);
            }
        });

        firestore.collection("student").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
                for(DocumentSnapshot snapshot:documents) {
                 Student student = snapshot.toObject(Student.class);
                 mStudentList.add(student);
                }
                mStudentAdapter.notifyDataSetChanged();
            }
        });
    }
}