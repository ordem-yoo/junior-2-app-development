package kr.ac.mjc.chatting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.Nullable;

public class NicknameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nickname);
        EditText nicknameEt = findViewById(R.id.nickname_et);
        Button enterBtn = findViewById(R.id.enter_btn);

        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nickname = nicknameEt.getText().toString();
                if(!nickname.equals("")){
                    Intent intent = new Intent(NicknameActivity.this, MainActivity.class);
                    intent.putExtra("nickname",nickname);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
