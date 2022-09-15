package kr.ac.mjc.ict2020261014.activity_example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity","onCreate");

       EditText emailEt = findViewById(R.id.email_et);
       EditText passwordEt = findViewById(R.id.password_et);
       Button loginBtn = findViewById(R.id.login_btn);
       Button signupBtn = findViewById(R.id.signup_btn);

       signupBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String email = emailEt.getText().toString();
               String password = passwordEt.getText().toString();

               Intent intent= new Intent(MainActivity.this, JoinActivity.class);
               intent.putExtra("email",email);
               intent.putExtra("password",password);
               startActivity(intent);
           }
       });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity", "onDestroy");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("MainActivity", "onRestart");
    }
}