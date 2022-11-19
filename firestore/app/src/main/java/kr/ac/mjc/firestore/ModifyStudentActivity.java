package kr.ac.mjc.firestore;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.Nullable;

public class ModifyStudentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        String id = getIntent().getStringExtra("id");

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        EditText studentNameEt = findViewById(R.id.student_name_et);
        EditText studentNumberEt = findViewById(R.id.student_number_et);
        EditText studentDepartmentEt = findViewById(R.id.student_department_et);

        Button modifyBtn = findViewById(R.id.add_student_btn);
        modifyBtn.setText("학생 정보 수정");

        firestore.collection("Student").document(id).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Student student = documentSnapshot.toObject(Student.class);
                        studentNameEt.setText(student.getName());
                        studentNumberEt.setText(student.getNumber());
                        studentDepartmentEt.setText(student.getDepartment());

                    }
                });

        modifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = new Student();
                student.setName(studentNameEt.getText().toString());
                student.setNumber(studentNumberEt.getText().toString());
                student.setDepartment(studentDepartmentEt.getText().toString());
                firestore.collection("Student").document(id).set(student)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                setResult(RESULT_OK);
                                finish();
                            }
                        });
            }
        });
    }

}
