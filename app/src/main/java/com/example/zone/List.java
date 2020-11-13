package com.example.zone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zone.Adapter.Activity_Test;
import com.example.zone.Room.SeminarRoomSel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class List extends AppCompatActivity {
    ArrayList<String> midList;
    ArrayAdapter<String> adapter;
    LinearLayout QuietZoneLay,SeminarLay,DvdZoneLay,PCZoneLay;
    TextView quietTv,dvdZoneTv,PcZoneTv,SeminarTv;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();


    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
    Calendar cal = Calendar.getInstance();
    String Today = sdf.format(cal.getTime());

    SimpleDateFormat sdfTime = new SimpleDateFormat("HH");
    String Time = sdfTime.format(cal.getTime());
    int result=0;
    String RoomNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        setTitle("목록");
        QuietZoneLay = (LinearLayout)findViewById(R.id.QuietZoneLay);
        SeminarLay = (LinearLayout)findViewById(R.id.SeminarZoneLay);
        PCZoneLay = (LinearLayout)findViewById(R.id.PCZoneLay);
        quietTv = (TextView)findViewById(R.id.tvstatus);
        SeminarTv=(TextView)findViewById(R.id.SeminarTv);
        PcZoneTv = (TextView)findViewById(R.id.PcZoneTv);



        final Query query = myRef.child("Seat").child("Seminar");
        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(final DataSnapshot snapshot) {

                for(int i =1;i<=9;i++)
                {
                         RoomNum=Integer.toString(i)+"번 방";

                    if(snapshot.child(RoomNum).child("2020").child(Today).hasChild(Time))
                    {
                        result++;

                    }
                }
                SeminarTv.setText(result+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


         });




        QuietZoneLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Activity_Test.class);
                startActivity(intent);
            }
        });
        SeminarLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SeminarRoomSel.class);
                startActivity(intent);
            }
        });



        PCZoneLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), ReportActivity.class);
                startActivity(intent);


            }
        });


        final Query query2 = myRef.child("Seat");
        query2.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot datasnapshot) {
                int i=0;
                    for(int j=1;j<=84;j++) {
                        if(datasnapshot.child("QuietZone").child(Integer.toString(j)).child("status").getValue().equals(true)) {
                            i++;
                        }
                    }
                quietTv.setText(Integer.toString(i));


                i=0;
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("loadUser:onCancelled", databaseError.toException());
            }
        });

    }

}