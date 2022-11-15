package kr.ac.mjc.firestore;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        EditText studentNameEt = findViewById(R.id.student_name_et);
        EditText studentNumberEt = findViewById(R.id.student_number_et);
        EditText studentDepartmentEt = findViewById(R.id.student_department_et);

        Button addStudentBtn = findViewById(R.id.add_student_btn);

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        addStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = studentNameEt.getText().toString();
                String number = studentNumberEt.getText().toString();
                String department = studentDepartmentEt.getText().toString();

                Student student = new Student();
                student.setName(name);
                student.setNumber(number);
                student.setDepartment(department);

                firestore.collection("student").add(student)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                setResult(RESULT_OK);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });
            }
        });
    }
}
