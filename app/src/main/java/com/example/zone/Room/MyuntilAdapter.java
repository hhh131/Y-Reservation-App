package com.example.zone.Room;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zone.R;

import java.util.ArrayList;

public class MyuntilAdapter  extends RecyclerView.Adapter<MyuntilAdapter.CustomViewHolder> {
    private ArrayList<ListData> untilData;

    public interface MyuntilViewClickListener {
        void onUntilItemClicked(int position, TextView textView);
    }

    private MyuntilViewClickListener uListener;

    public void setOnClickListener(MyuntilViewClickListener listener) { uListener = listener; }

    public MyuntilAdapter(ArrayList<ListData> untilData) {
        this.untilData = untilData;
    }

    public MyuntilAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_middlelist, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    public void onBindViewHolder(@NonNull final MyuntilAdapter.CustomViewHolder holder, int position){
        holder.subject.setText(untilData.get(position).getSubject().getText());

        if(uListener != null){
            final int pos = position + 1;

            holder.subject.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    uListener.onUntilItemClicked(pos,holder.subject);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return (null != untilData ? untilData.size() : 0);
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
