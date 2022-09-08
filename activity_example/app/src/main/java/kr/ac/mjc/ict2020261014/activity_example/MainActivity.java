package kr.ac.mjc.ict2020261014.activity_example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       EditText emailEt = findViewById(R.id.email_et);
       EditText passwordEt = findViewById(R.id.password_et);
       Button loginBtn = findViewById(R.id.login_btn);
       Button signupBtn = findViewById(R.id.signup_btn);

       signupBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent= new Intent(MainActivity.this, JoinActivity.class);
               startActivity(intent);
           }
       });

    }
}