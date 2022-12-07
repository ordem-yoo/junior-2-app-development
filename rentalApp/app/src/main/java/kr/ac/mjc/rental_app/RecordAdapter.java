package kr.ac.mjc.rental_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {
    ArrayList<Record> mList;

    public RecordAdapter(ArrayList<Record> list){
        this.mList=list;
    }

    @NonNull
    @Override
    public RecordAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecordAdapter.ViewHolder holder, int position) {

        Record record = mList.get(position);
        holder.bind(record);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView rentalTv;
        TextView returnTv;
        TextView rentalTimeTv;
        TextView returnTimeTv;
        ImageView returnIv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rentalTv=itemView.findViewById(R.id.rental_Tv);
            rentalTimeTv = itemView.findViewById(R.id.rentaltime_Tv);
            returnTv=itemView.findViewById(R.id.return_Tv);
            returnTimeTv = itemView.findViewById(R.id.returntime_Tv);
            returnIv= itemView.findViewById(R.id.imageView2);
        }
        public void bind(Record record){

            rentalTimeTv.setText(record.getRental_start());
            returnTimeTv.setText(record.getRental_finish());
//            returnIv.setImageURI();
        }
    }
}
