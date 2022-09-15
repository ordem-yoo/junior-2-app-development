package kr.ac.mjc.ict2020261014.activity_example;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class JoinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        Intent intent =getIntent();
        String email = intent.getStringExtra("email");
        String password = intent.getStringExtra("password");

        EditText emailEt = findViewById(R.id.email_et);
        emailEt.setText(email);

        EditText passwordEt = findViewById(R.id.password_et);
        passwordEt.setText(password);


    }
}
