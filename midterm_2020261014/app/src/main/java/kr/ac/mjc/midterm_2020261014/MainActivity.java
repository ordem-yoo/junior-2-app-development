package kr.ac.mjc.midterm_2020261014;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText stdnum = findViewById(R.id.std_num);
        EditText password = findViewById(R.id.std_passwd);
        Button loginBtn = findViewById(R.id.login_btn);

        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String number = stdnum.getText().toString();
                String passwd =password.getText().toString();
                if (number.equals("2020261014") && passwd.equals("1234")){
                    Intent intent = new Intent(MainActivity.this,RvPage.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(), "아이디 또는 패스워드가 틀렸습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}