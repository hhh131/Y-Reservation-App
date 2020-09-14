package com.example.zone;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toolbar;

import com.example.zone.Room.QuietZone;
import com.example.zone.Vo.UserVO;

import java.util.ArrayList;

public class List extends AppCompatActivity {
    ArrayList<String> midList;
    ArrayAdapter<String> adapter;
    LinearLayout QuietZoneLay,SeminarLay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        QuietZoneLay = (LinearLayout)findViewById(R.id.QuietZoneLay);
        SeminarLay = (LinearLayout)findViewById(R.id.seminarLay);



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


/*
       midList =
                new ArrayList<String>();
        ListView list = (ListView) findViewById(R.i);

        adapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, midList);
        list.setAdapter(adapter);


        midList.add("콰이어트존");
        midList.add("세미나실");

        adapter.notifyDataSetChanged();

*/



    }
}