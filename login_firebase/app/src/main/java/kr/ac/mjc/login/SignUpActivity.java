package kr.ac.mjc.login;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        EditText emailEt = findViewById(R.id.email_et);
        EditText passwordEt = findViewById(R.id.password_et);

        Button signupBtn = findViewById(R.id.signup_bt);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEt.getText().toString();
                String password = passwordEt.getText().toString();

                if(email.equals("")){
                    Toast.makeText(SignUpActivity.this, "enter your email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.equals("")){
                    Toast.makeText(SignUpActivity.this, "enter your password", Toast.LENGTH_SHORT).show();
                    return;
                }

                FirebaseAuth auth = FirebaseAuth.getInstance();

                auth.createUserWithEmailAndPassword(email,password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                            FirebaseUser user = authResult.getUser();
                            Log.d("SignUpActivity",user.getEmail());
                            finish();
                            Toast.makeText(SignUpActivity.this, "Sign up completed", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("SignUpActivity",e.getMessage());
                                Toast.makeText(SignUpActivity.this, "Sign up failed", Toast.LENGTH_SHORT).show();

                            }
                        });

            }
        });
    }
}
