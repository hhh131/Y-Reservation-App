package com.example.zone;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toolbar;

import java.util.ArrayList;

public class List extends AppCompatActivity {
    ArrayList<String> midList;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
       midList =
                new ArrayList<String>();
        ListView list = (ListView) findViewById(R.id.listView1);

        adapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, midList);
        list.setAdapter(adapter);


        midList.add("콰이어트존");
        midList.add("세미나실");

        adapter.notifyDataSetChanged();




    }
}