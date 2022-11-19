package kr.ac.mjc.chatting;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

public class ChattingAdapter extends RecyclerView.Adapter<ChattingAdapter.ViewHolder> {

    final int VIEW_TYPE_ME = 1;
    final int VIEW_TYPE_OTHER = 2;

    List<Message> mMessageList;
    String mNickname;

    public ChattingAdapter(List<Message> messageList, String nickname) {
        this.mMessageList = messageList;
        this.mNickname = nickname;
    }

    @Override
    public int getItemViewType(int position) {
        Message message = mMessageList.get(position);
        if (message.getNickname().equals(mNickname)) {
            return VIEW_TYPE_ME;
        } else {
            return VIEW_TYPE_OTHER;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ME) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_me, parent, false);
            MeViewHolder viewHolder = new MeViewHolder(view);
            return viewHolder;
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_other, parent, false);
            OtherViewHolder viewHolder = new OtherViewHolder(view);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Message  message =mMessageList.get(position);
        holder.bind(message);
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    public class MeViewHolder extends ViewHolder {
        public MeViewHolder(@Nullable View itemView) {
            super(itemView);
        }

        @Override
        public void bind(Message message) {
            textTv.setText(message.getText());
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            String time = sdf.format(message.getSendDate());
            timeTv.setText(time);
        }
    }

    public class OtherViewHolder extends ViewHolder {

        TextView nickanmeTv;

        public OtherViewHolder(@Nullable View itemView) {
            super(itemView);
            nickanmeTv = itemView.findViewById(R.id.nickname_tv);
        }

        @Override
        public void bind(Message message) {
            textTv.setText(message.getText());
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            String time = sdf.format(message.getSendDate());
            timeTv.setText(time);
            nickanmeTv.setText(message.getNickname());
        }
    }

    abstract public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textTv;
        TextView timeTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTv = itemView.findViewById(R.id.text_tv);
            timeTv = itemView.findViewById(R.id.time_tv);
        }

        abstract public void bind(Message message);
    }
}
