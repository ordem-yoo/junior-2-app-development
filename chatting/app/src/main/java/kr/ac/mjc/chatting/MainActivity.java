package kr.ac.mjc.chatting;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView chattingRv;
    EditText textEt;
    Button sendBtn;

    String mNickname;

    FirebaseFirestore firestore;

    List<Message> mMessageList = new ArrayList<>();

    ChattingAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        firestore = FirebaseFirestore.getInstance();
        mNickname = getIntent().getStringExtra("nickname");
        adapter = new ChattingAdapter(mMessageList, mNickname);

        chattingRv = findViewById(R.id.chatting_rv);
        chattingRv.setAdapter(adapter);

        chattingRv.setLayoutManager(new LinearLayoutManager(this));

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

        firestore.collection("chatting").orderBy("sendDate", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        List<DocumentChange> changeList =value.getDocumentChanges();
                        for(DocumentChange change:changeList){
                            if(change.getType()==DocumentChange.Type.ADDED){
                                Message message = change.getDocument().toObject(Message.class);
                                mMessageList.add(message);
                            }
                        }
                        adapter.notifyDataSetChanged();
                        chattingRv.scrollToPosition(mMessageList.size()-1);
                    }
                });

    }
}