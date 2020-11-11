package com.example.zone.Room;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zone.R;
import com.example.zone.SeminarReservationDialog;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import static com.example.zone.JoinLogin.LoginActivity.loginId;

public class seminarDay extends AppCompatActivity implements MyListAdapter.MymiddleViewClickListener, MysmallAdapter.MysmallViewClickListener, MyuntilAdapter.MyuntilViewClickListener {
    ArrayList<ListData> middledata = new ArrayList<>();
    ArrayList<ListData> smalldata = new ArrayList<>();
    ArrayList<ListData> untildata = new ArrayList<>();
    MyListAdapter middleadapter;
    MysmallAdapter smalladapter;
    MyuntilAdapter untiladapter;
    public static String dayString="",timeString="",untillString="";
    private RecyclerView recyclerday, recyclertime, recycleruntil;
    private LinearLayoutManager linearLayoutManager1, linearLayoutManager2, linearLayoutManager3;

    private TextView dayinfo1, dayinfo2, dayinfo3, dayinfo4, dayinfo5;
    private TextView timeinfo1, timeinfo2, timeinfo3, timeinfo4, timeinfo5, timeinfo6, timeinfo7, timeinfo8, timeinfo9, timeinfo10, timeinfo11, timeinfo12, timeinfo13;
    private TextView untilinfo1, untilinfo2, untilinfo3;
    private TextView checkdayinfo, checktimeinfo, checktimeRinfo, checkuntilinfo;
    private TextView[] arraydayinfo = {dayinfo1, dayinfo2, dayinfo3, dayinfo4, dayinfo5};
    private TextView[] arraytimeinfo = {timeinfo1, timeinfo2, timeinfo3, timeinfo4, timeinfo5, timeinfo6, timeinfo7, timeinfo8, timeinfo9, timeinfo10, timeinfo11, timeinfo12, timeinfo13};
    private TextView[] arrayuntilinfo = {untilinfo1, untilinfo2, untilinfo3};
    private Button btncheckin,btnCancel;
    private TextView status;
    TextView tempview;
    private String[] hour = {"09:00 \n~ 10:00", "10:00\n~ 11:00", "11:00\n~ 12:00", "12:00\n~ 13:00", "13:00\n~ 14:00", "14:00\n~ 15:00",
                    "15:00\n~ 16:00", "16:00\n~ 17:00", "17:00\n~ 18:00", "18:00\n~ 19:00", "19:00\n~ 20:00", "20:00\n~ 21:00", "21:00\n~ 22:00"};
    private String[] dbhour = new String[13];
    private String selday = "";
    private String seltime = "";
    private String seluntil = "";
    private String Zone = "Seminar";

    private int checkday = 0, checktime = 0, checkuntil = 0, checktotal = 0, checktimeR = 0, checkuntilk = 0;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    TextView tb;
    public static String RoomNum;
    String YearString ="2020";
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
    SimpleDateFormat sdfweek = new SimpleDateFormat("EE", Locale.KOREAN);
    SimpleDateFormat sdftime = new SimpleDateFormat("HH");
    Activity activity = this;
    Calendar cal = Calendar.getInstance();
    IntentResult result;
    String time = sdftime.format(cal.getTime());        //현재시간

    String[] day = new String[5];
    String[] dbday = new String[5];
    String[] week = new String[5];
    String[] until = {"1", "2", "3"};

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabmenu);

        status = (TextView) findViewById(R.id.status);
        tb = (TextView) findViewById(R.id.tb);
        btncheckin = (Button) findViewById(R.id.btncheckin);
        btnCancel = (Button) findViewById(R.id.btnCancle);
        tb.setText(RoomNum + "Seminar");

        Intent intent = getIntent();
        RoomNum = intent.getStringExtra("RoomNum");
        RoomNum = RoomNum + "번 방";
        setTitle(RoomNum);
            btnCancel.setVisibility(View.GONE);

        recyclerday = (RecyclerView) findViewById(R.id.recyclerday);
        recyclertime = (RecyclerView) findViewById(R.id.recyclertime);
        recycleruntil = (RecyclerView) findViewById(R.id.recycleruntil);
        linearLayoutManager1 = new LinearLayoutManager(this);
        linearLayoutManager2 = new LinearLayoutManager(this);
        linearLayoutManager3 = new LinearLayoutManager(this);
        recyclerday.setLayoutManager(linearLayoutManager1);
        recyclertime.setLayoutManager(linearLayoutManager2);
        recycleruntil.setLayoutManager(linearLayoutManager3);
        recyclertime.setVisibility(View.GONE);
        recycleruntil.setVisibility(View.GONE);
        context = this;

        tempview = new TextView(context);

        dayinfo1 = new TextView(context);
        dayinfo2 = new TextView(context);
        dayinfo3 = new TextView(context);
        dayinfo4 = new TextView(context);
        dayinfo5 = new TextView(context);

        timeinfo1 = new TextView(context);
        timeinfo2 = new TextView(context);
        timeinfo3 = new TextView(context);
        timeinfo4 = new TextView(context);
        timeinfo5 = new TextView(context);
        timeinfo6 = new TextView(context);
        timeinfo7 = new TextView(context);
        timeinfo8 = new TextView(context);
        timeinfo9 = new TextView(context);
        timeinfo10 = new TextView(context);
        timeinfo11 = new TextView(context);
        timeinfo12 = new TextView(context);
        timeinfo13 = new TextView(context);
        untilinfo1 = new TextView(context);
        untilinfo2 = new TextView(context);
        untilinfo3 = new TextView(context);

        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {  //일요일인지 확인
            cal.add(Calendar.DATE, 1);      //일요일이면 다음날로
        } else if (cal.get(Calendar.DAY_OF_WEEK) == 7) {    //토요일인지 확인
            cal.add(Calendar.DATE, 1);              //토요일이면 다다음날로
            cal.add(Calendar.DATE, 1);
        }

        day[0] = sdf.format(cal.getTime());         //첫날이 유동적이라 첫날 먼저 입력
        week[0] = sdfweek.format(cal.getTime());

        for (int i = 1; i < 5; i++) {
            cal.add(Calendar.DATE, 1);

            int j = cal.get(Calendar.DAY_OF_WEEK);

            if (j != 1 && j != 7) {    //주말이 아닐때만 배열에 입력
                day[i] = sdf.format(cal.getTime());
                week[i] = sdfweek.format(cal.getTime());
            } else {          //주말이면 배열에 널값을 방지하기 위해 i값 감소
                i--;
            }
        }

        dayinfo1.setText(day[0] + "(" + week[0] + ")");
        dayinfo2.setText(day[1] + "(" + week[1] + ")");
        dayinfo3.setText(day[2] + "(" + week[2] + ")");
        dayinfo4.setText(day[3] + "(" + week[3] + ")");
        dayinfo5.setText(day[4] + "(" + week[4] + ")");

        middledata.add(new ListData(dayinfo1));
        middledata.add(new ListData(dayinfo2));
        middledata.add(new ListData(dayinfo3));
        middledata.add(new ListData(dayinfo4));
        middledata.add(new ListData(dayinfo5));

        timeinfo1.setText(hour[0]);
        timeinfo2.setText(hour[1]);
        timeinfo3.setText(hour[2]);
        timeinfo4.setText(hour[3]);
        timeinfo5.setText(hour[4]);
        timeinfo6.setText(hour[5]);
        timeinfo7.setText(hour[6]);
        timeinfo8.setText(hour[7]);
        timeinfo9.setText(hour[8]);
        timeinfo10.setText(hour[9]);
        timeinfo11.setText(hour[10]);
        timeinfo12.setText(hour[11]);
        timeinfo13.setText(hour[12]);

        smalldata.add(new ListData(timeinfo1));
        smalldata.add(new ListData(timeinfo2));
        smalldata.add(new ListData(timeinfo3));
        smalldata.add(new ListData(timeinfo4));
        smalldata.add(new ListData(timeinfo5));
        smalldata.add(new ListData(timeinfo6));
        smalldata.add(new ListData(timeinfo7));
        smalldata.add(new ListData(timeinfo8));
        smalldata.add(new ListData(timeinfo9));
        smalldata.add(new ListData(timeinfo10));
        smalldata.add(new ListData(timeinfo11));
        smalldata.add(new ListData(timeinfo12));
        smalldata.add(new ListData(timeinfo13));

        untilinfo1.setText("1시간");
        untilinfo2.setText("2시간");
        untilinfo3.setText("3시간");

        untildata.add(new ListData(untilinfo1));
        untildata.add(new ListData(untilinfo2));
        untildata.add(new ListData(untilinfo3));

        status.setText(time + "");

        /*if(biglist.getSelectedItem() == null){
            middlelist.setVisibility(View.INVISIBLE);
            smalllist.setVisibility(View.INVISIBLE);
        }*/


        middleadapter = new MyListAdapter(middledata);
        recyclerday.setAdapter(middleadapter);

        smalladapter = new MysmallAdapter(smalldata);
        recyclertime.setAdapter(smalladapter);

        untiladapter = new MyuntilAdapter(untildata);
        recycleruntil.setAdapter(untiladapter);

        //dayinfo1.setBackgroundColor(Color.RED);
        dayString = dayinfo1.getText().toString();
        timeString = timeinfo1.getText().toString();


        checkdayinfo = dayinfo1;
        dayinfo1.setBackgroundColor(Color.RED);


//        for (int i = 0; i < 5; i++){
//            day[i] = middledata.get(i).getSubject();
//            dbday[i] = middledata.get(i).getSubject().substring(0, 2) + middledata.get(i).getSubject().substring(3, 5);
//        }
//
//
//        middleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long id) {
//                int k = 0;
//                smalldata.clear();
//                checkday = 1; checktime = 0; checkuntil = 0;
//                btncheckin.setEnabled(false);
//
//                selday = dbday[position];
//
//                if (position == 0){
//                    for (int i = 0; i < 13; i++){
//                        if(Integer.parseInt(time) <= i + 9) {
//                            smalldata.add(new ListData(hour[i]));
//                            dbhour[k] = hour[i].substring(0,2);00000000
//                            k++;
//                        }
//                    }
//                }else{
//                    for (int i = 0; i < 13; i++) {
//                        smalldata.add(new ListData(hour[i]));
//                        dbhour[k] = hour[i].substring(0, 2);
//                        k++;
//                    }
//                }
//                smallList.setAdapter(smalladapter);
//
//                status.setText(selday);
//
//                final Query query = myRef.child("Seat").child(Zone).child(RoomNum);
//                query.addListenerForSingleValueEvent(new ValueEventListener() {
//
//
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        //Toast.makeText(getApplicationContext(),snapshot.child(selday).child(seltime).getValue().toString(),Toast.LENGTH_SHORT).show();
//
//                        /*if(snapshot.child(selday).child(seltime).child("status").getValue().equals(true))
//                        {
//                            //smallList.getChildAt(position).setEnabled(false);
//
//                        }*/
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//
//            }
//        });
//
//        smallList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {
//                untildata.clear();
//
//                seltime = dbhour[position];
//                checktime = 1; checkuntil = 0;
//                btncheckin.setEnabled(false);
//
//                if (seltime.equals("20")){
//                    untildata.add(new ListData("1시간"));
//                    untildata.add(new ListData("2시간"));
//                }else if(seltime.equals("21")){
//                    untildata.add(new ListData("1시간"));
//                }else {
//                    untildata.add(new ListData("1시간")); untildata.add(new ListData("2시간")); untildata.add(new ListData("3시간"));
//                }
//                untilList.setAdapter(untiladapter);
//
//                status.setText(seltime);
//
//            }
//        });
//
//        untilList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                seluntil = until[position];
//                checkuntil = 1;
//
//                checktotal = checkday + checktime + checkuntil;
//
//                if (checktotal == 3) {
//                    btncheckin.setEnabled(true);
//                }
//
//                status.setText(selday);
//            }
//        });

        middleadapter.setOnClickListener(this);
        smalladapter.setOnClickListener(this);
        untiladapter.setOnClickListener(this);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                myRef.child("Seat").child(Zone).child(RoomNum).child(dayString).child(timeString).removeValue()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.e(Zone, "취소 성공");
                                Toast.makeText(getApplicationContext(),"예약 성공",Toast.LENGTH_SHORT).show();
                                // finish();
                                smalladapter.notifyDataSetChanged();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e(Zone, "예약 실패");
                                //howToast("회원가입 실패");

                            }
                        });

               /* final Query query = myRef.child("Seat").child(Zone).child(RoomNum);
                query.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot datasnapshot) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });*/
            }
                                     });
        btncheckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SeminarReservationDialog reservationDialog = new SeminarReservationDialog(seminarDay.this);
                //커스텀 다이얼로그를 호출한다.
                reservationDialog.callFunction("Seminar", RoomNum,dayString,timeString,checkuntilk,checktime);






            }
        });
        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(activity);
                intentIntegrator.setOrientationLocked(false);
                intentIntegrator.setBeepEnabled(false);//바코드 인식시 소리
                intentIntegrator.initiateScan();
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
    void testMethod(int position)
    {
        final Query query = myRef.child("Seat").child(Zone).child(RoomNum);
        query.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Toast.makeText(getApplicationContext(),snapshot.child(selday).child(seltime).getValue().toString(),Toast.LENGTH_SHORT).show();
                try {
                    if (snapshot.child(dayString).child(timeString).child("status").getValue().equals(true)) {


                    } else {

                    }


                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override

    public void onMiddleItemClicked(int position,TextView textView) {
        smalladapter.notifyDataSetChanged();
        middleadapter.notifyDataSetChanged();

        if (checkday == 0){
            checkday = 1;
            checkdayinfo = textView;
            checkdayinfo.setBackground(ContextCompat.getDrawable(this, R.drawable.round_textview_check));
        }else if (checkdayinfo.getText() != textView.getText()){
            checkdayinfo.setBackground(ContextCompat.getDrawable(this, R.drawable.round_textview_list));
            checkdayinfo = textView;
            textView.setBackground(ContextCompat.getDrawable(this, R.drawable.round_textview_check));
        }

        //textView.setBackgroundColor(Color.RED);
        recyclertime.setVisibility(View.VISIBLE);
        recycleruntil.setVisibility(View.GONE);
        dayString= textView.getText().toString();

       // Toast.makeText(getApplicationContext(), position + dayString, Toast.LENGTH_SHORT).show();
        //textView.setBackgroundColor(Color.rgb(255,0,0));


      /*  if(position == 1){
            for (int i = 0; i < 13; i++){
                if(Integer.parseInt(time) <= Integer.parseInt(smalldata.get(i).getSubject().getText().toString().substring(0, 2))){
                    //arraytimeinfo[position - 1].setEnabled(true);
                }

            }
        }*/

    }

    @Override
    public void onSmallItemClicked(final int position, TextView textView, int alpha) {
        timeString= textView.getText().toString();
        timeString=timeString.substring(0,2);

        tempview.setBackground(ContextCompat.getDrawable(tempview.getContext(), R.drawable.round_textview_reser));

        if (checktime == 0){
            checktime = 1;
            checktimeinfo = textView;
            checktimeinfo.setBackground(ContextCompat.getDrawable(this, R.drawable.round_textview_check));
        } else if (alpha == 1){
            checktimeinfo.setBackground(ContextCompat.getDrawable(this, R.drawable.round_textview_reser));
            checktimeinfo = textView;
            textView.setBackground(ContextCompat.getDrawable(this, R.drawable.round_textview_check));
        } else if(alpha == 0){
            checktimeinfo.setBackground(ContextCompat.getDrawable(this, R.drawable.round_textview_list));
            checktimeinfo = textView;
            textView.setBackground(ContextCompat.getDrawable(this, R.drawable.round_textview_check));
        }


        //Toast.makeText(getApplicationContext(), timeString, Toast.LENGTH_SHORT).show();

       // smalladapter.notifyDataSetChanged();
        final Query query = myRef.child("Seat").child(Zone).child(RoomNum);
        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Toast.makeText(getApplicationContext(),snapshot.child(selday).child(seltime).getValue().toString(),Toast.LENGTH_SHORT).show();
                if(snapshot.child(dayString).hasChild(timeString)) {
                    if (snapshot.child(dayString).child(timeString).child("id").getValue().toString().equals(loginId)) {
                            btnCancel.setVisibility(View.VISIBLE);


                    }
                    else
                    {

                        btnCancel.setVisibility(View.GONE);
                    }
                }
                else {
                 /*   if (position == 13){
                        untildata.clear();
                        untildata.add(new ListData(untilinfo1));
                    }else if(position == 12){
                        untildata.clear();
                        untildata.add(new ListData(untilinfo1));
                        untildata.add(new ListData(untilinfo2));
                    }else {
                        untildata.clear();
                        untildata.add(new ListData(untilinfo1));
                        untildata.add(new ListData(untilinfo2));
                        untildata.add(new ListData(untilinfo3));
                    }

                    untiladapter = new MyuntilAdapter(untildata);
                    recycleruntil.setAdapter(untiladapter);
                    //untiladapter.notifyDataSetChanged();*/

                    recycleruntil.setVisibility(View.VISIBLE);
                    btnCancel.setVisibility(View.GONE);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Override
    public void onUntilItemClicked(int position,TextView textView) {
        untillString = textView.getText().toString();
        checkuntilk = Integer.parseInt(untillString.substring(0,1));

        if (checkuntil == 0 && checkuntil == 2){
            checkuntil = 1;
            checkuntilinfo = textView;
           // checkuntilinfo.setBackground(ContextCompat.getDrawable(this, R.drawable.round_textview_check));
        }else{
           // checkuntilinfo.setBackground(ContextCompat.getDrawable(this, R.drawable.round_textview_list));
            checkuntilinfo = textView;
          //  textView.setBackground(ContextCompat.getDrawable(this, R.drawable.round_textview_check));
        }

        //Toast.makeText(getApplicationContext(), untillString, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "취소되었습니다.", Toast.LENGTH_LONG).show();
            } else if (result.getContents().equals("1")) {

                final String SeatNumber = result.getContents();
                final Query query = myRef.child("Seat").child(Zone).child(result.getContents());

                query.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(final DataSnapshot datasnapshot) {


                        //datasnapshot.child("2020").child("11").child("12").child(09).getValue().equals()





                /*        Query query = myRef.child("reservation").child(Zone);
                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {



                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {


                            }
                        });*/


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.w("loadUser:onCancelled", databaseError.toException());
                    }
                });
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
            Toast.makeText(getApplicationContext(), "잘못된 QR코드 입니다.", Toast.LENGTH_SHORT).show();
        }
    }
}