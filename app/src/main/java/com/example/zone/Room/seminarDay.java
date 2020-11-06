package com.example.zone.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zone.R;
import com.example.zone.Vo.SeminarVO;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import static com.example.zone.JoinLogin.LoginActivity.loginId;

public class seminarDay extends AppCompatActivity {
    ArrayList<ListData> middledata = new ArrayList<>();
    ArrayList<ListData> smalldata = new ArrayList<>();
    ArrayList<ListData> untildata = new ArrayList<>();
    MyListAdapter middleadapter = new MyListAdapter(middledata);
    MysmallAdapter smalladapter = new MysmallAdapter(smalldata);
    MyuntilAdapter untiladapter = new MyuntilAdapter(untildata);

    private RecyclerView recyclerday, recyclertime, recycleruntil;
    private LinearLayoutManager linearLayoutManager;

    private TextView dayinfo1, dayinfo2, dayinfo3, dayinfo4, dayinfo5;
    private TextView timeinfo1, timeinfo2, timeinfo3, timeinfo4, timeinfo5, timeinfo6, timeinfo7, timeinfo8, timeinfo9, timeinfo10, timeinfo11, timeinfo12, timeinfo13;
    private TextView untilinfo1, untilinfo2, untilinfo3;
    private Button btncheckin;
    private TextView status;
    private String[] hour = {"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00"};
    private String[] dbhour = new String[13];
    private String selday = "";
    private String seltime = "";
    private String seluntil = "";
    private String Zone = "Seminar";

    private int checkday = 0, checktime = 0, checkuntil = 0, checktotal = 0;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    TextView tb;
    String RoomNum;
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
    SimpleDateFormat sdfweek = new SimpleDateFormat("EE", Locale.KOREAN);
    SimpleDateFormat sdftime = new SimpleDateFormat("HH");

    Calendar cal = Calendar.getInstance();

    String time = sdftime.format(cal.getTime());

    String[] day = new String[5];
    String[] dbday = new String[5];
    String[] week = new String[5];
    String[] until = {"1", "2", "3"};

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabmenu);

        Intent intent=getIntent();
        RoomNum=intent.getStringExtra("RoomNum");
        btncheckin = (Button)findViewById(R.id.btncheckin);
        btncheckin.setEnabled(false);

        recyclerday = (RecyclerView)findViewById(R.id.recyclerday);
        recyclertime = (RecyclerView)findViewById(R.id.recyclertime);
        recycleruntil = (RecyclerView)findViewById(R.id.recycleruntil);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerday.setLayoutManager(linearLayoutManager);
        recyclertime.setLayoutManager(linearLayoutManager);
        recycleruntil.setLayoutManager(linearLayoutManager);

        context = this;

        dayinfo1 = new TextView(context);   dayinfo2 = new TextView(context);   dayinfo3 = new TextView(context);   dayinfo4 = new TextView(context);   dayinfo5 = new TextView(context);
        timeinfo1 = new TextView(context);  timeinfo2 = new TextView(context);  timeinfo3 = new TextView(context);  timeinfo4 = new TextView(context);  timeinfo5 = new TextView(context);
        timeinfo6 = new TextView(context);  timeinfo7 = new TextView(context);  timeinfo8 = new TextView(context);  timeinfo9 = new TextView(context);  timeinfo10 = new TextView(context);
        timeinfo11 = new TextView(context); timeinfo12 = new TextView(context); timeinfo13 = new TextView(context);
        untilinfo1 = new TextView(context); untilinfo2 = new TextView(context); untilinfo3 = new TextView(context);

        if(cal.get(Calendar.DAY_OF_WEEK) < 2){

        }

        day[0] = sdf.format(cal.getTime());
        week[0] = sdfweek.format(cal.getTime());

        for (int i = 1; i < 5; i++) {
            cal.add(Calendar.DATE, 1);

            int j = cal.get(Calendar.DAY_OF_WEEK);

            if (j > 2) {
                day[i] = sdf.format(cal.getTime());
                week[i] = sdfweek.format(cal.getTime());
            }
        }
        middledata.add(new ListData(day[i] + "(" + week[i] + ")"));

        dayinfo1.setText(day[] + "(" + week[i] + ")"));

        dayinfo1.setText();
        status = (TextView)findViewById(R.id.status);
        tb = (TextView)findViewById(R.id.tb);
        tb.setText(RoomNum + "Seminar");


        for (int i = 0; i < 13; i++) {
            if (Integer.parseInt(time) <= i + 9) {
                smalldata.add(new ListData(hour[i]));
            }
        }

        status.setText(time + "");

        /*if(biglist.getSelectedItem() == null){
            middlelist.setVisibility(View.INVISIBLE);
            smalllist.setVisibility(View.INVISIBLE);
        }*/


        middleList.setAdapter(middleadapter);
        smallList.setAdapter(smalladapter);
        untilList.setAdapter(untiladapter);

        for (int i = 0; i < 5; i++){
            day[i] = middledata.get(i).getSubject();
            dbday[i] = middledata.get(i).getSubject().substring(0, 2) + middledata.get(i).getSubject().substring(3, 5);
        }

        smallList.getChildAt(1).setEnabled(false);

        middleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long id) {
                int k = 0;
                smalldata.clear();
                checkday = 1; checktime = 0; checkuntil = 0;
                btncheckin.setEnabled(false);

                selday = dbday[position];

                if (position == 0){
                    for (int i = 0; i < 13; i++){
                        if(Integer.parseInt(time) <= i + 9) {
                            smalldata.add(new ListData(hour[i]));
                            dbhour[k] = hour[i].substring(0,2);
                            k++;
                        }
                    }
                }else{
                    for (int i = 0; i < 13; i++) {
                        smalldata.add(new ListData(hour[i]));
                        dbhour[k] = hour[i].substring(0, 2);
                        k++;
                    }
                }
                smallList.setAdapter(smalladapter);

                status.setText(selday);

                final Query query = myRef.child("Seat").child(Zone).child(RoomNum);
                query.addListenerForSingleValueEvent(new ValueEventListener() {


                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //Toast.makeText(getApplicationContext(),snapshot.child(selday).child(seltime).getValue().toString(),Toast.LENGTH_SHORT).show();

                        /*if(snapshot.child(selday).child(seltime).child("status").getValue().equals(true))
                        {
                            //smallList.getChildAt(position).setEnabled(false);

                        }*/
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        smallList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {
                untildata.clear();

                seltime = dbhour[position];
                checktime = 1; checkuntil = 0;
                btncheckin.setEnabled(false);

                if (seltime.equals("20")){
                    untildata.add(new ListData("1시간"));
                    untildata.add(new ListData("2시간"));
                }else if(seltime.equals("21")){
                    untildata.add(new ListData("1시간"));
                }else {
                    untildata.add(new ListData("1시간")); untildata.add(new ListData("2시간")); untildata.add(new ListData("3시간"));
                }
                untilList.setAdapter(untiladapter);

                status.setText(seltime);

            }
        });

        untilList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                seluntil = until[position];
                checkuntil = 1;

                checktotal = checkday + checktime + checkuntil;

                if (checktotal == 3) {
                    btncheckin.setEnabled(true);
                }

                status.setText(selday);
            }
        });

        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Query query = myRef.child("Seat").child(Zone).child(RoomNum);
                query.addListenerForSingleValueEvent(new ValueEventListener() {


                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //Toast.makeText(getApplicationContext(),snapshot.child(selday).child(seltime).getValue().toString(),Toast.LENGTH_SHORT).show();

                        if(snapshot.child(selday).child(seltime).child("status").getValue().equals(true))
                        {
                            Toast.makeText(getApplicationContext(),"예약되어있음",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                SeminarVO seminarVO = new SeminarVO(loginId, true, false);

                myRef.child("Seat").child(Zone).child(RoomNum).child(selday).child(seltime).setValue(seminarVO)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.e(Zone, "예약 성공");
                                //ShowToast("회원가입 성공");
                                // finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e(Zone, "예약 실패");
                                //howToast("회원가입 실패");

                            }
                        });


            }
        });

        //ArrayAdapter<String> middleadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, middledata);
        //ArrayAdapter<String> smalladapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, smalldata);

        //middlelist.setAdapter(middleadapter);   smalllist.setAdapter(smalladapter);

        /*for (int i = 0; i < 7; i++){
            day[i] = sdf.format(cal.getTime());
            week[i] = sdfweek.format(cal.getTime());
            cal.add(DATE, 1);

            int j = cal.get(Calendar.DAY_OF_WEEK);

            if(j > 2) {
                middledata.add(new ListData(day[i] + "\n" + week[i]));
            }
        }*/

        /*middlelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(middlelist.getSelectedItem() != null){
                    selday = place + "  날짜:" + middlelist.getSelectedItem().toString();
                    smalllist.setVisibility(View.VISIBLE);

                    status.setText(selday);

                    for (int i = 0; i < 13; i++){
                        if(time <= i + 9)
                            smalldata.add(hour[i]);
                    }
                }
            }
        });

        smalllist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(smalllist.getSelectedItem().toString() != null){
                    seltime = selday + "  시간:" + smalllist.getSelectedItem().toString();

                    status.setText(seltime);
                }
            }
        });*/


    }
}