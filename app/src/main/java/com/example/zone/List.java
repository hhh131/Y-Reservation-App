package com.example.zone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zone.Adapter.Activity_Test;
import com.example.zone.Room.SeminarZone;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class List extends AppCompatActivity {
    ArrayList<String> midList;
    ArrayAdapter<String> adapter;
    LinearLayout QuietZoneLay,SeminarLay,DvdZoneLay,PCZoneLay;
    TextView quietTv,dvdZoneTv,PcZoneTv;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        setTitle("목록");
        QuietZoneLay = (LinearLayout)findViewById(R.id.QuietZoneLay);
        SeminarLay = (LinearLayout)findViewById(R.id.SeminarZoneLay);
        PCZoneLay = (LinearLayout)findViewById(R.id.PCZoneLay);
        quietTv = (TextView)findViewById(R.id.tvstatus);

        PcZoneTv = (TextView)findViewById(R.id.PcZoneTv);














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
                Intent intent = new Intent(getApplicationContext(), SeminarZone.class);
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


        final Query query = myRef.child("Seat");
        query.addListenerForSingleValueEvent(new ValueEventListener() {

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

                for(int j=1;j<=5;j++) {

                    if(datasnapshot.child("PcZone").child(Integer.toString(j)).child("status").getValue().equals(true)) {
                        i++;
                    }
                }
                PcZoneTv.setText(Integer.toString(i));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("loadUser:onCancelled", databaseError.toException());
            }
        });







    }

}