package kr.ac.mjc.ict2020261014.recyclerview;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder>{

    ArrayList<Student> mList;

    public StudentAdapter(ArrayList<Student> list){
        this.mList=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.d("StudentAdapter","onCreateViewHolder");

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student,parent,false);

       ViewHolder holder= new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("StudentAdapter",String.format("onBindViewHolder[%d]",position));

        Student student=mList.get(position);
        holder.bind(student);
    }

    @Override
    public int getItemCount() {
        Log.d("StudentAdapter",String.format("getItemCount[%d]",mList.size()));

        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameTv;
        TextView numberTv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv=itemView.findViewById(R.id.name_tv);
            numberTv=itemView.findViewById(R.id.number_tv);
        }
        public void bind(Student student){
            nameTv.setText(student.getName());
            numberTv.setText(student.getNumber());
        }
    }
}
