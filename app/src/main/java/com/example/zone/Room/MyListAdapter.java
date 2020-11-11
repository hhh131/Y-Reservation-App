package com.example.zone.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zone.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.CustomViewHolder> {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    private ArrayList<ListData> middledata;
    String Zone="Seminar";
    public interface MymiddleViewClickListener {
        void onMiddleItemClicked(int position, TextView textView);
    }

    private MymiddleViewClickListener mListener;

    public void setOnClickListener(MymiddleViewClickListener listener) { mListener = listener; }

    public MyListAdapter(ArrayList<ListData> middledata) {
        this.middledata = middledata;
    }

    public MyListAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_middlelist, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    void testMethod(DataSnapshot dataSnapshot)
    {

    }

    public void onBindViewHolder(@NonNull final MyListAdapter.CustomViewHolder holder, int position){
        holder.subject.setText(middledata.get(position).getSubject().getText());
        //holder.subject.setBackgroundColor(Color.WHITE);
        if(mListener != null){
            final int pos = position + 1;

            holder.subject.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){

                    mListener.onMiddleItemClicked(pos,holder.subject);         //seminarday에서 for문 접근이 어려워;; 그냥 pos로 get 으로 접근해야 할듯;;
                }

            });


        }

    }

    @Override
    public int getItemCount() {
        return (null != middledata ? middledata.size() : 0);
        //리스트 뷰 생성 다하면 그때 0 반환
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView subject;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            this.subject = (TextView) itemView.findViewById(R.id.btnday);

        }
    }

}
