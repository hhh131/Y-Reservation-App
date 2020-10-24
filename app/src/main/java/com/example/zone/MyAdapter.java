package com.example.zone;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.CustomViewHolder> {

    private ArrayList<MainData> arrayList;

    public MyAdapter(ArrayList<MainData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_testitem, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.CustomViewHolder holder, int position) {
        holder.L_seat1.setText(arrayList.get(position).getL_seat1().getText());
        holder.L_seat2.setText(arrayList.get(position).getL_seat2().getText());
        holder.L_seat3.setText(arrayList.get(position).getL_seat3().getText());

        holder.R_seat1.setText(arrayList.get(position).getR_seat1().getText());
        holder.R_seat2.setText(arrayList.get(position).getR_seat2().getText());
        holder.R_seat3.setText(arrayList.get(position).getR_seat3().getText());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //여기는 각 버튼에 대한 이벤트
                Button btn = (Button)view;

                String number = btn.getText().toString();
                switch (number)
                {
                    case "1":

                        break;
                }

                // 텍스트 값에 따라서 switch로 동작시키면 될거 같음
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
        //리스트 뷰 생성 다하면 그때 0 반환
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected Button L_seat1;
        protected Button L_seat2;
        protected Button L_seat3;
        protected Button R_seat1;
        protected Button R_seat2;
        protected Button R_seat3;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            this.L_seat1 = (Button) itemView.findViewById(R.id.L_seat1);
            this.L_seat2 = (Button) itemView.findViewById(R.id.L_seat2);
            this.L_seat3 = (Button) itemView.findViewById(R.id.L_seat3);
            this.R_seat1 = (Button) itemView.findViewById(R.id.R_seat1);
            this.R_seat2 = (Button) itemView.findViewById(R.id.R_seat2);
            this.R_seat3 = (Button) itemView.findViewById(R.id.R_seat3);
        }
    }
}