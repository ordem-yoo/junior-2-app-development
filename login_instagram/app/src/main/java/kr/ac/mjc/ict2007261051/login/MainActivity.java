package kr.ac.mjc.ict2007261051.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseAuth auth=FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null){
            completeLogin();
        }

        EditText emailEt=findViewById(R.id.email_et);
        EditText passwordEt=findViewById(R.id.password_et);

        Button loginBtn=findViewById(R.id.login_btn);
        Button joinBtn=findViewById(R.id.join_btn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=emailEt.getText().toString();
                String password=passwordEt.getText().toString();

                FirebaseAuth auth=FirebaseAuth.getInstance();


                auth.signInWithEmailAndPassword(email,password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(MainActivity.this, "로그인이 완료되었습니다", Toast.LENGTH_SHORT).show();
                                Log.d("MainActivity",authResult.getUser().getEmail());
                                completeLogin();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("MainActivity",e.getMessage());
                            }
                        });
            }
        });

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,JoinActivity.class);
                startActivity(intent);
            }
        });
    }
    public void completeLogin(){
        Intent intent=new Intent(this,SecondActivity.class);
        startActivity(intent);
        finish();
    }
}





