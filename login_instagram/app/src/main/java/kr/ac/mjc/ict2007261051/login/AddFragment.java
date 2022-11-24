package kr.ac.mjc.ict2007261051.login;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class AddFragment extends Fragment {

    ImageView imageIv;
    EditText textEt;
    Button uploadBtn;

    Uri selectedImage;

    final int REQ_IMAGE_PICK = 5198;

    FirebaseAuth auth;
    FirebaseStorage storage;
    FirebaseFirestore firestore;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageIv = view.findViewById(R.id.image_iv);
        textEt = view.findViewById(R.id.text_et);
        uploadBtn = view.findViewById(R.id.upload_btn);

        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        firestore = FirebaseFirestore.getInstance();

        imageIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQ_IMAGE_PICK);
            }
        });

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedImage == null){// 사용자가 이미지를 선택안했을때
                    Toast.makeText(getContext(),"Please Select Image",Toast.LENGTH_SHORT).show();
                    return;
                }
                String text = textEt.getText().toString();
                if(text.equals("")){
                    Toast.makeText(getContext(),"Please fill out application",Toast.LENGTH_SHORT).show();
                    return;
                }
                String fileName = UUID.randomUUID().toString();
                storage.getReference().child("image").child(fileName).putFile(selectedImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        taskSnapshot.getMetadata().getReference().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String imageUrl = uri.toString();
                                Log.d("mjc",imageUrl);
                            }
                        });

                    }
                });
                Toast.makeText(getContext(),"Upload complete",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_IMAGE_PICK && resultCode == RESULT_OK) {
            selectedImage = data.getData();
            imageIv.setImageURI(selectedImage);
        }
    }
}
