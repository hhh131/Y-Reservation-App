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
import android.widget.Toolbar;

import com.example.zone.Room.DVDZone;
import com.example.zone.Room.PcZoneActivity;
import com.example.zone.Room.QuietZone;
import com.example.zone.Room.Willow;
import com.example.zone.Room.seminar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class List extends AppCompatActivity {
    ArrayList<String> midList;
    ArrayAdapter<String> adapter;
    LinearLayout QuietZoneLay,SeminarLay,DvdZoneLay,PCZoneLay,WillowLay;
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
        WillowLay = (LinearLayout)findViewById(R.id.WillowLay);
        PCZoneLay = (LinearLayout)findViewById(R.id.PCZoneLay);
        quietTv = (TextView)findViewById(R.id.tvstatus);
        DvdZoneLay = (LinearLayout)findViewById(R.id.DvdZoneLay);
        dvdZoneTv = (TextView)findViewById(R.id.DvdZoneTv);
        PcZoneTv = (TextView)findViewById(R.id.PcZoneTv);














        QuietZoneLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QuietZone.class);
                startActivity(intent);
            }
        });
        SeminarLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), seminar.class);
                startActivity(intent);
            }
        });

        DvdZoneLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DVDZone.class);
                startActivity(intent);
            }
        });
        WillowLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Willow.class);
                startActivity(intent);
            }
        });
        PCZoneLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PcZoneActivity.class);
                startActivity(intent);
            }
        });


        final Query query = myRef.child("Seat");
        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot datasnapshot) {
                int i=0;
                    for(int j=1;j<=5;j++) {
                        if(datasnapshot.child("QuietZone").child(Integer.toString(j)).child("status").getValue().equals(true)) {
                            i++;
                        }
                    }
                quietTv.setText(Integer.toString(i));
                i=0;

               for(int j=1;j<=3;j++) {

                    if(datasnapshot.child("DvdZone").child(Integer.toString(j)).child("status").getValue().equals(true)) {
                        i++;
                    }
                }
                dvdZoneTv.setText(Integer.toString(i));

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