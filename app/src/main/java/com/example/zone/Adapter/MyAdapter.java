package com.example.zone.Adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zone.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.zone.JoinLogin.LoginActivity.loginId;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.CustomViewHolder> {

    private ArrayList<MainData> arrayList;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    String MySeatNum;
    Context context;
    public interface MyRecyclerViewClickListener {
        void onItemClicked_1(int position);
        void onItemClicked_2(int position);
        void onItemClicked_3(int position);
        void onItemClicked_4(int position);
        void onItemClicked_5(int position);
        void onItemClicked_6(int position);
        void onItemClicked_7(int position);
        void onItemClicked_8(int position);
        void onItemClicked_9(int position);
        void onItemClicked_10(int position);
        void onItemClicked_11(int position);
        void onItemClicked_12(int position);
    }

    private MyRecyclerViewClickListener mListener;

    public void setOnClickListener(MyRecyclerViewClickListener listener){
        mListener = listener;
    }

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
    public void onBindViewHolder(@NonNull final MyAdapter.CustomViewHolder holder, int position) {
        holder.L_seat1.setText(arrayList.get(position).getL_seat1().getText());
        holder.L_seat2.setText(arrayList.get(position).getL_seat2().getText());
        holder.L_seat3.setText(arrayList.get(position).getL_seat3().getText());
        holder.R_seat1.setText(arrayList.get(position).getR_seat1().getText());
        holder.R_seat2.setText(arrayList.get(position).getR_seat2().getText());
        holder.R_seat3.setText(arrayList.get(position).getR_seat3().getText());
        holder.L_seat4.setText(arrayList.get(position).getL_seat4().getText());
        holder.L_seat5.setText(arrayList.get(position).getL_seat5().getText());
        holder.L_seat6.setText(arrayList.get(position).getL_seat6().getText());
        holder.R_seat4.setText(arrayList.get(position).getR_seat4().getText());
        holder.R_seat5.setText(arrayList.get(position).getR_seat5().getText());
        holder.R_seat6.setText(arrayList.get(position).getR_seat6().getText());


        Query query = myRef.child("reservation").child("QuietZone").child(loginId);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
            try {
                if (snapshot.child("id").getValue() != null) {
                    MySeatNum = snapshot.child("seatNum").getValue().toString();
                } else {
                    MySeatNum = "null";
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }
                Query query = myRef.child("Seat").child("QuietZone");
                query.addListenerForSingleValueEvent(new ValueEventListener() {


                    @Override
                    public void onDataChange(DataSnapshot datasnapshot) {

                        SeatCheck(datasnapshot,holder.L_seat1);
                        SeatCheck(datasnapshot,holder.L_seat2);
                        SeatCheck(datasnapshot,holder.L_seat3);
                        SeatCheck(datasnapshot,holder.R_seat1);
                        SeatCheck(datasnapshot,holder.R_seat2);
                        SeatCheck(datasnapshot,holder.R_seat3);
                        SeatCheck(datasnapshot,holder.L_seat4);
                        SeatCheck(datasnapshot,holder.L_seat5);
                        SeatCheck(datasnapshot,holder.L_seat6);
                        SeatCheck(datasnapshot,holder.R_seat4);
                        SeatCheck(datasnapshot,holder.R_seat5);
                        SeatCheck(datasnapshot,holder.R_seat6);

                }

                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.w("loadUser:onCancelled", databaseError.toException());
                }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });





















        if(mListener != null){



            final int pos = position * 12;
            holder.L_seat1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemClicked_1(1 + pos);
                }
            });
            holder.L_seat2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemClicked_2(2 + pos);
                }
            });
            holder.L_seat3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemClicked_3(3 + pos);
                }
            });
            holder.R_seat1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemClicked_4(4 + pos);
                }
            });
            holder.R_seat2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemClicked_5(5 + pos);
                }
            });
            holder.R_seat3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemClicked_6(6 + pos);
                }
            });
            holder.L_seat4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemClicked_7(7 + pos);
                }
            });
            holder.L_seat5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemClicked_8(8 + pos);
                }
            });
            holder.L_seat6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemClicked_9(9 + pos);
                }
            });
            holder.R_seat4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemClicked_10(10 + pos);
                }
            });
            holder.R_seat5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemClicked_11(11 + pos);
                }
            });
            holder.R_seat6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemClicked_12(12 + pos);
                }
            });


        }

    }



    public void SeatCheck(DataSnapshot datasnapshot,Button btn)
    {

        if (datasnapshot.child(btn.getText().toString()).child("seatNum").getValue().equals(MySeatNum)) {
          btn.setBackground(ContextCompat.getDrawable(btn.getContext(), R.drawable.round_bg_seat_my));

        } else  if (datasnapshot.child(btn.getText().toString()).child("status").getValue().equals(true)) {

        btn.setBackground(ContextCompat.getDrawable(btn.getContext(), R.drawable.round_bg_seat_on));
    }

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
        protected Button L_seat4;
        protected Button L_seat5;
        protected Button L_seat6;
        protected Button R_seat4;
        protected Button R_seat5;
        protected Button R_seat6;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            this.L_seat1 = (Button) itemView.findViewById(R.id.L_seat1);
            this.L_seat2 = (Button) itemView.findViewById(R.id.L_seat2);
            this.L_seat3 = (Button) itemView.findViewById(R.id.L_seat3);
            this.R_seat1 = (Button) itemView.findViewById(R.id.R_seat1);
            this.R_seat2 = (Button) itemView.findViewById(R.id.R_seat2);
            this.R_seat3 = (Button) itemView.findViewById(R.id.R_seat3);
            this.L_seat4 = (Button) itemView.findViewById(R.id.L_seat4);
            this.L_seat5 = (Button) itemView.findViewById(R.id.L_seat5);
            this.L_seat6 = (Button) itemView.findViewById(R.id.L_seat6);
            this.R_seat4 = (Button) itemView.findViewById(R.id.R_seat4);
            this.R_seat5 = (Button) itemView.findViewById(R.id.R_seat5);
            this.R_seat6 = (Button) itemView.findViewById(R.id.R_seat6);
        }
    }

}