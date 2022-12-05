package kr.ac.mjc.ict2007261051.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView timelineRv;
    List<Post> mPostList = new ArrayList<>();
    TimelineAdapter timelineAdapter;

    FirebaseFirestore firestore;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        timelineRv = view.findViewById(R.id.timeline_rv);
        timelineAdapter = new TimelineAdapter(mPostList);

        timelineRv.setLayoutManager(new LinearLayoutManager(getContext()));
        timelineRv.setAdapter(timelineAdapter);

        firestore = FirebaseFirestore.getInstance();

        firestore.collection("posts")
                .orderBy("uploadDate", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot snapshot : documents) {
                            Post post = snapshot.toObject(Post.class);
                            mPostList.add(post);
                        }
                        timelineAdapter.notifyDataSetChanged();
                    }
                });
    }
}
