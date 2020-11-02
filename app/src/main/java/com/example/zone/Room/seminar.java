package com.example.zone.Room;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zone.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static java.util.Calendar.DATE;

public class seminar extends AppCompatActivity {

    private ListView biglist;
    private ListView middlelist;
    private ListView smalllist;
    private String[] hour = {"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00"};


    private Calendar c = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabmenu);

        Calendar cal = Calendar.getInstance();

        int time = cal.get(Calendar.HOUR_OF_DAY);

        SimpleDateFormat sdf = new SimpleDateFormat("MM월dd일");
        SimpleDateFormat sdfweek = new SimpleDateFormat("EE요일");
        SimpleDateFormat sdftime = new SimpleDateFormat("HH:00");
        String[] day = new String[7];
        String[] week = new String[7];

        biglist = (ListView)findViewById(R.id.biglist);
        middlelist = (ListView)findViewById(R.id.middlelist);
        smalllist = (ListView)findViewById(R.id.smalllist);

        /*if(biglist.getSelectedItem() == null){
            middlelist.setVisibility(View.INVISIBLE);
            smalllist.setVisibility(View.INVISIBLE);
        }*/

        List<String> bigdata = new ArrayList<>();
        List<String> middledata = new ArrayList<>();
        List<String> smalldata = new ArrayList<>();

        ArrayAdapter<String> bigadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bigdata);
        ArrayAdapter<String> middleadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, middledata);
        ArrayAdapter<String> smalladapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, smalldata);

        biglist.setAdapter(bigadapter);     middlelist.setAdapter(middleadapter);   smalllist.setAdapter(smalladapter);

        bigdata.add("Seminar 1");   bigdata.add("Seminar 2");   bigdata.add("Seminar 3");
        bigdata.add("Seminar 4");   bigdata.add("Seminar 5");   bigdata.add("Seminar 6");
        bigdata.add("Seminar 7");   bigdata.add("Seminar 8");   bigdata.add("Seminar 9");

        for (int i = 0; i < 7; i++){
            day[i] = sdf.format(cal.getTime());
            week[i] = sdfweek.format(cal.getTime());
            cal.add(DATE, 1);

            int j = cal.get(Calendar.DAY_OF_WEEK);

            if(j > 2) {
                middledata.add(day[i] + "\n" + week[i]);
            }
        }

        for (int i = 0; i < 14; i++){
            if(time < i + 9)
                smalldata.add(hour[i]);
        }



    }
}