package com.example.zone;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Activity_Test extends AppCompatActivity {

    private ArrayList<MainData> arrayList = new ArrayList<MainData>();
    private MyAdapter myAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private Button seat1, seat2, seat3, seat4, seat5, seat6, seat7, seat8, seat9, seat10, seat11, seat12;
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        context = this;


        seat1 = new Button(context);    seat2 = new Button(context);
        seat3 = new Button(context);    seat4 = new Button(context);
        seat5 = new Button(context);    seat6 = new Button(context);
        seat7 = new Button(context);    seat8 = new Button(context);
        seat9 = new Button(context);    seat10 = new Button(context);
        seat11 = new Button(context);   seat12 = new Button(context);

        seat1.setText("1");  seat2.setText("2");  seat3.setText("3");
        seat4.setText("4");  seat5.setText("5");  seat6.setText("6");
        seat7.setText("7");  seat8.setText("8");  seat9.setText("9");
        seat10.setText("10"); seat11.setText("11"); seat12.setText("12");

        arrayList.add(new MainData(seat1, seat2, seat3, seat4, seat5, seat6));
        arrayList.add(new MainData(seat7, seat8, seat9, seat10, seat11, seat12));

        myAdapter = new MyAdapter(arrayList);
        recyclerView.setAdapter(myAdapter);

        myAdapter.notifyDataSetChanged();


    }
}
