package kr.ac.mjc.ict2007261051.login;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class JoinActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        FirebaseAuth auth=FirebaseAuth.getInstance();

        EditText emailEt=findViewById(R.id.email_et);
        EditText passwordEt=findViewById(R.id.password_et);

        Button joinBtn=findViewById(R.id.join_btn);

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=emailEt.getText().toString();
                String password=passwordEt.getText().toString();

                if(email.equals("")){
                    Toast.makeText(JoinActivity.this, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.equals("")){
                    Toast.makeText(JoinActivity.this, "패스워드를 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.signOut();
                auth.createUserWithEmailAndPassword(email,password)

                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(JoinActivity.this, "회원가입이 완료되었습니다", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Log.d("JoinActivity",e.getMessage());
                            }
                        });
                
                
            }
        });
    }
}
