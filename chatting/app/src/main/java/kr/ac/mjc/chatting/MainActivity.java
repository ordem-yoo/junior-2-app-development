package kr.ac.mjc.chatting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    RecyclerView chattingRv;
    EditText textEt;
    Button sendBtn;

    String mNickname;

    FirebaseFirestore firestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firestore = FirebaseFirestore.getInstance();
        mNickname = getIntent().getStringExtra("nickname");


        chattingRv = findViewById(R.id.chatting_rv);

        textEt = findViewById(R.id.text_et);
        sendBtn = findViewById(R.id.send_btn);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = textEt.getText().toString();
                if (!text.equals("")) {
                    Message message = new Message();
                    message.setNickname(mNickname);
                    message.setText(text);

                    firestore.collection("chatting").document().set(message)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    textEt.setText("");
                                    Log.d("MJC", "Send Success");
                                }
                            });


                }
            }
        });

    }
}