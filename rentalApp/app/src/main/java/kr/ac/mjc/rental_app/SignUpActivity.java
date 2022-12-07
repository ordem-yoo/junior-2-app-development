package kr.ac.mjc.rental_app;

import android.os.Bundle;
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
        setContentView(R.layout.signup_screen);

        EditText idEt = findViewById(R.id.email_Et);
        EditText passwordEt = findViewById(R.id.password_Et);
        EditText checkpasswordEt = findViewById(R.id.check_password_Et);
        EditText emailEt = findViewById(R.id.email_Et);
        EditText phonenumberEt = findViewById(R.id.phone_number_Et);

        Button signupBtn = findViewById(R.id.signup_Btn);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = idEt.getText().toString();
                String password = passwordEt.getText().toString();
                String checkpassword = checkpasswordEt.getText().toString();
                String email = emailEt.getText().toString();
                String phonenumber = phonenumberEt.getText().toString();

                if (id.equals("")) {
                    Toast.makeText(SignUpActivity.this, "enter your email", Toast.LENGTH_SHORT).show();
                    return;
                }else if (password.equals("")||checkpassword.equals("")){
                    Toast.makeText(SignUpActivity.this, "enter your password", Toast.LENGTH_SHORT).show();
                    return;
                }else if (email.equals("")){
                    Toast.makeText(SignUpActivity.this, "enter your nickname", Toast.LENGTH_SHORT).show();
                    return;
                }else if (phonenumber.equals("")){
                    Toast.makeText(SignUpActivity.this, "enter your phone number", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(checkpassword)) {
                    Toast.makeText(SignUpActivity.this, "Password does not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                FirebaseAuth auth = FirebaseAuth.getInstance();

                auth.createUserWithEmailAndPassword(id, password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                FirebaseUser user = authResult.getUser();
                                finish();
                                Toast.makeText(SignUpActivity.this, "Sign Up complete", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SignUpActivity.this, "Sign Up Failed Try again", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
