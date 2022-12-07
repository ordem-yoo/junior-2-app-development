package kr.ac.mjc.ict2007261051.login;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.ViewHolder> {

    private List<Post> mPostList;

    public TimelineAdapter(List<Post> postList){
        this.mPostList = postList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = mPostList.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return mPostList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView useridTv;
        TextView textTv;
        ImageView imageIv;


        public ViewHolder(@NonNull View itemView){
            super(itemView);
            useridTv = itemView.findViewById(R.id.userid_Tv);
            textTv = itemView.findViewById(R.id.text_Tv);
            imageIv= itemView.findViewById(R.id.image_Iv);
        }

        public void bind(Post post){
            useridTv.setText(post.getUserId());
            textTv.setText(post.getText());
            Glide.with(imageIv.getContext()).load(post.getImageUrl()).into(imageIv);

        }
    }
}
