package com.example.zone.Room;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import static com.example.zone.Room.seminarDay.RoomNum;
import static com.example.zone.Room.seminarDay.dayString;

public class MysmallAdapter extends RecyclerView.Adapter<MysmallAdapter.CustomViewHolder> {
    private ArrayList<ListData> smalldata;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    String Zone ="Seminar";
    private Context context;
    private Intent intent;
 /*   MysmallAdapter(Context context)
    {
        this.context = context;
    }

    MysmallAdapter(Context context,Intent intent)
    {
        this(context);
        this.intent = intent; // use this intent
    }*/




    public interface MysmallViewClickListener {
        void onSmallItemClicked(int position, TextView textView, int alpha);
    }

    private MysmallViewClickListener sListener;

    public void setOnClickListener(MysmallViewClickListener listener) { sListener = listener; }

    public MysmallAdapter(ArrayList<ListData> smalldata){
        this.smalldata = smalldata;
    }

    public MysmallAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_middlelist, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }


    void testMethod(DataSnapshot dataSnapshot)
    {

    }

    public void onBindViewHolder(@NonNull final MysmallAdapter.CustomViewHolder holder, int position){
        holder.subject.setText(smalldata.get(position).getSubject().getText());

        final Query query = myRef.child("Seat").child(Zone).child(RoomNum);
        query.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                try {
                        dayString = dayString.substring(0,5);
                        String TimeString=holder.subject.getText().toString().substring(0,2);

                    if(datasnapshot.child("2020").child(dayString).hasChild(TimeString))
                    {
                        if(datasnapshot.child("2020").child(dayString).child(TimeString).child("id").getValue().toString().equals(loginId)) {
                            holder.subject.setBackground(ContextCompat.getDrawable(holder.subject.getContext(), R.drawable.round_textview_reser));
                            holder.alpha = 1;
                        }
                        else
                        {
                            holder.subject.setBackground(ContextCompat.getDrawable(holder.subject.getContext(), R.drawable.round_textview_list));
                            holder.alpha = 0;
                        }
                    }else{
                        holder.alpha = 0;
                    }

                }catch (Exception e)
                {
                    e.printStackTrace();
                }
                //Toast.makeText(getApplicationContext(),snapshot.child(selday).child(seltime).getValue().toString(),Toast.LENGTH_SHORT).show();


             /*   try {
                    if (snapshot.child( ).child(timeString).child("status").getValue().equals(true)) {
                        textView.setBackgroundColor(Color.RED);
                    } else {

                    }


                } catch (NullPointerException e) {
                    e.printStackTrace();
                }*/
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        if(sListener != null){
            final int pos = position + 1;

            holder.subject.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    sListener.onSmallItemClicked(pos,holder.subject, holder.alpha);
                }
            });
        }

    }

    public int getItemCount() {
        return (null != smalldata ? smalldata.size() : 0);
        //리스트 뷰 생성 다하면 그때 0 반환
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView subject;
        protected int alpha;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            this.subject = (TextView) itemView.findViewById(R.id.btnday);
            alpha = 0;

        }
    }

}
