package com.example.zone.Adapter;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zone.R;
import com.example.zone.ReservationDialog;
import com.example.zone.Utill;
import com.example.zone.Vo.ReservationVO;
import com.example.zone.Vo.SeatVO;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

import static com.example.zone.JoinLogin.LoginActivity.loginId;
import static com.example.zone.JoinLogin.LoginActivity.loginStatus;

public class
Activity_Test extends AppCompatActivity implements MyAdapter.MyRecyclerViewClickListener {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    IntentResult result;
    Utill utill;
    String ZONE="QuietZone";
    public static final String NOTIFICATION_CHANNEL_ID = "10001";
    private Activity activity = this;
    String SeatNum;
    private ArrayList<MainData> arrayList = new ArrayList<MainData>();
    private MyAdapter myAdapter;
    private RecyclerView recyclerView;
    private Button QrBtn,MySeatReturnBtn,input;
    private LinearLayoutManager linearLayoutManager;
    NotificationManager notificationManager;
    private Button seat1, seat2, seat3, seat4, seat5, seat6, seat7, seat8, seat9, seat10, seat11, seat12,
                   seat13, seat14, seat15, seat16, seat17, seat18, seat19, seat20, seat21, seat22,
                   seat23, seat24, seat25, seat26, seat27, seat28, seat29, seat30, seat31, seat32,
                   seat33, seat34, seat35, seat36, seat37, seat38, seat39, seat40, seat41, seat42,
                   seat43, seat44, seat45, seat46, seat47, seat48, seat49, seat50, seat51, seat52,
                   seat53, seat54, seat55, seat56, seat57, seat58, seat59, seat60, seat61, seat62,
                   seat63, seat64, seat65, seat66, seat67, seat68, seat69, seat70, seat71, seat72,
                   seat73, seat74, seat75, seat76, seat77, seat78, seat79, seat80, seat81, seat82, seat83, seat84;

    private Button[] btnarray = {seat1, seat2, seat3, seat4, seat5, seat6, seat7, seat8, seat9, seat10, seat11, seat12,
            seat13, seat14, seat15, seat16, seat17, seat18, seat19, seat20, seat21, seat22,
            seat23, seat24, seat25, seat26, seat27, seat28, seat29, seat30, seat31, seat32,
            seat33, seat34, seat35, seat36, seat37, seat38, seat39, seat40, seat41, seat42,
            seat43, seat44, seat45, seat46, seat47, seat48, seat49, seat50, seat51, seat52,
            seat53, seat54, seat55, seat56, seat57, seat58, seat59, seat60, seat61, seat62,
            seat63, seat64, seat65, seat66, seat67, seat68, seat69, seat70, seat71, seat72,
    seat73, seat74, seat75, seat76, seat77, seat78, seat79, seat80, seat81, seat82, seat83, seat84};

    private String[] btnName = {"seat1", "seat2", "seat3", "seat4", "seat5", "seat6", "seat7", "seat8", "seat9", "seat10", "seat11", "seat12",
            "seat13", "seat14", "seat15", "seat16", "seat17", "seat18", "seat19", "seat20", "seat21", "seat22",
            "seat23", "seat24", "seat25", "seat26", "seat27", "seat28", "seat29", "seat30", "seat31", "seat32",
            "seat33", "seat34", "seat35", "seat36", "seat37", "seat38", "seat39", "seat40", "seat41", "seat42",
            "seat43", "seat44", "seat45", "seat46", "seat47", "seat48", "seat49", "seat50", "seat51", "seat52",
            "seat53", "seat54", "seat55", "seat56", "seat57", "seat58", "seat59", "seat60", "seat61", "seat62",
            "seat63", "seat64", "seat65", "seat66", "seat67", "seat68", "seat69", "seat70", "seat71", "seat72",
            "seat73", "seat74", "seat75", "seat76", "seat77", "seat78", "seat79", "seat80", "seat81", "seat82", "seat83", "seat84"};    //버튼 이벤트는 이걸로 활용하면 될듯

    private int check = 0, i;

    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        setTitle("QuietZone");

















        if(loginStatus==false)
        {
            showToast("로그인이 필요합니다.");
            finish();
        }


        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        QrBtn=(Button)findViewById(R.id.QrBtn) ;
        input=(Button)findViewById(R.id.input) ;
        MySeatReturnBtn=(Button)findViewById(R.id.MySeatReturnBtn);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        context = this;

        seat1 = new Button(context);    seat2 = new Button(context);    seat3 = new Button(context);    seat4 = new Button(context);
        seat5 = new Button(context);    seat6 = new Button(context);    seat7 = new Button(context);    seat8 = new Button(context);
        seat9 = new Button(context);    seat10 = new Button(context);   seat11 = new Button(context);   seat12 = new Button(context);
        seat13 = new Button(context);   seat14 = new Button(context);   seat15 = new Button(context);   seat16 = new Button(context);
        seat17 = new Button(context);   seat18 = new Button(context);   seat19 = new Button(context);   seat20 = new Button(context);
        seat21 = new Button(context);   seat22 = new Button(context);   seat23 = new Button(context);   seat24 = new Button(context);
        seat25 = new Button(context);   seat26 = new Button(context);   seat27 = new Button(context);   seat28 = new Button(context);
        seat29 = new Button(context);   seat30 = new Button(context);   seat31 = new Button(context);   seat32 = new Button(context);
        seat33 = new Button(context);   seat34 = new Button(context);   seat35 = new Button(context);   seat36 = new Button(context);
        seat37 = new Button(context);   seat38 = new Button(context);   seat39 = new Button(context);   seat40 = new Button(context);
        seat41 = new Button(context);   seat42 = new Button(context);   seat43 = new Button(context);   seat44 = new Button(context);
        seat45 = new Button(context);   seat46 = new Button(context);   seat47 = new Button(context);   seat48 = new Button(context);
        seat49 = new Button(context);   seat50 = new Button(context);   seat51 = new Button(context);   seat52 = new Button(context);
        seat53 = new Button(context);   seat54 = new Button(context);   seat55 = new Button(context);   seat56 = new Button(context);
        seat57 = new Button(context);   seat58 = new Button(context);   seat59 = new Button(context);   seat60 = new Button(context);
        seat61 = new Button(context);   seat62 = new Button(context);   seat63 = new Button(context);   seat64 = new Button(context);
        seat65 = new Button(context);   seat66 = new Button(context);   seat67 = new Button(context);   seat68 = new Button(context);
        seat69 = new Button(context);   seat70 = new Button(context);   seat71 = new Button(context);   seat72 = new Button(context);
        seat73 = new Button(context);   seat74 = new Button(context);   seat75 = new Button(context);   seat76 = new Button(context);
        seat77 = new Button(context);   seat78 = new Button(context);   seat79 = new Button(context);   seat80 = new Button(context);
        seat81 = new Button(context);   seat82 = new Button(context);   seat83 = new Button(context);   seat84 = new Button(context);

        seat1.setText("1");     seat2.setText("2");     seat3.setText("3");     seat4.setText("4");     seat5.setText("5");     seat6.setText("6");
        seat7.setText("7");     seat8.setText("8");     seat9.setText("9");     seat10.setText("10");   seat11.setText("11");   seat12.setText("12");
        seat13.setText("13");   seat14.setText("14");   seat15.setText("15");   seat16.setText("16");   seat17.setText("17");   seat18.setText("18");
        seat19.setText("19");   seat20.setText("20");   seat21.setText("21");   seat22.setText("22");   seat23.setText("23");   seat24.setText("24");
        seat25.setText("25");   seat26.setText("26");   seat27.setText("27");   seat28.setText("28");   seat29.setText("29");   seat30.setText("30");
        seat31.setText("31");   seat32.setText("32");   seat33.setText("33");   seat34.setText("34");   seat35.setText("35");   seat36.setText("36");
        seat37.setText("37");   seat38.setText("38");   seat39.setText("39");   seat40.setText("40");   seat41.setText("41");   seat42.setText("42");
        seat43.setText("43");   seat44.setText("44");   seat45.setText("45");   seat46.setText("46");   seat47.setText("47");   seat48.setText("48");
        seat49.setText("49");   seat50.setText("50");   seat51.setText("51");   seat52.setText("52");   seat53.setText("53");   seat54.setText("54");
        seat55.setText("55");   seat56.setText("56");   seat57.setText("57");   seat58.setText("58");   seat59.setText("59");   seat60.setText("60");
        seat61.setText("61");   seat62.setText("62");   seat63.setText("63");   seat64.setText("64");   seat65.setText("65");   seat66.setText("66");
        seat67.setText("67");   seat68.setText("68");   seat69.setText("69");   seat70.setText("70");   seat71.setText("71");   seat72.setText("72");
        seat73.setText("73");   seat74.setText("74");   seat75.setText("75");   seat76.setText("76");   seat77.setText("77");   seat78.setText("78");
        seat79.setText("79");   seat80.setText("80");   seat81.setText("81");   seat82.setText("82");   seat83.setText("83");   seat84.setText("84");

        arrayList.add(new MainData(seat1, seat2, seat3, seat4, seat5, seat6, seat7, seat8, seat9, seat10, seat11, seat12));
        arrayList.add(new MainData(seat13, seat14, seat15, seat16, seat17, seat18, seat19, seat20, seat21, seat22, seat23, seat24));
        arrayList.add(new MainData(seat25, seat26, seat27, seat28, seat29, seat30, seat31, seat32, seat33, seat34, seat35, seat36));
        arrayList.add(new MainData(seat37, seat38, seat39, seat40, seat41, seat42, seat43, seat44, seat45, seat46, seat47, seat48));
        arrayList.add(new MainData(seat49, seat50, seat51, seat52, seat53, seat54, seat55, seat56, seat57, seat58, seat59, seat60));
        arrayList.add(new MainData(seat61, seat62, seat63, seat64, seat65, seat66, seat67, seat68, seat69, seat70, seat71, seat72));
        arrayList.add(new MainData(seat73, seat74, seat75, seat76, seat77, seat78, seat79, seat80, seat81, seat82, seat83, seat84));

        myAdapter = new MyAdapter(arrayList);
        recyclerView.setAdapter(myAdapter);

        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(recyclerView.getScrollY() != 0){
                    input.setTextColor(Color.WHITE);
                } else if (recyclerView.getScrollY() == 0) {
                    input.setTextColor(Color.BLACK);
                }

                return false;
            }
        });

        myAdapter.notifyDataSetChanged();



        /*for ( i = 0; i < btnarray.length; i++){
            btnarray[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (check == 0){
                        check = i + 1;

                    }
                    else{
                       showToast("신고는 하나의 좌석씩 가능합니다.");
                    }
                }
            });
        }*/





        myAdapter.setOnClickListener(this);
        QrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(activity);
                intentIntegrator.setOrientationLocked(false);
                intentIntegrator.setBeepEnabled(false);//바코드 인식시 소리
                intentIntegrator.initiateScan();
            }
        });

        MySeatReturnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 Query query = myRef.child("reservation").child(ZONE).child(loginId);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                            try {

                                if(datasnapshot.getValue()!=null) {
                                    SeatNum = datasnapshot.child("seatNum").getValue().toString();
                                }
                                else
                                {
                                    showToast("반납 할 좌석이 없습니다.");
                                }
                            } catch (Exception e) {

                            }

                            Query query = myRef.child("Seat").child(ZONE).child(SeatNum);
                            query.addListenerForSingleValueEvent(new ValueEventListener() {


                                @Override
                                public void onDataChange(DataSnapshot datasnapshot) {

                                    try {
                                        if (datasnapshot.child("id").getValue().equals(loginId)) {
                                            SeatVO seatVO = new SeatVO("0", datasnapshot.child("seatNum").getValue().toString(),"0" ,false);

                                            myRef.child("Seat").child(ZONE).child(SeatNum).setValue(seatVO);
                                            myRef.child("reservation").child(ZONE).child(loginId).removeValue();
                                            showToast("좌석 반납 완료");
                                            cancleNotifi();
                                            //myAdapter.notifyDataSetChanged();
                                                finish();


                                        }
                                    } catch (Exception e) {
                                        showToast("반납할 좌석이 없습니다.");
                                    }
                                }


                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    Log.w("loadUser:onCancelled", databaseError.toException());
                                }
                            });


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
            }
        });


    }


    public void showToast(String msg) {
        Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        toast.show();
    }


    @Override
    public void onItemClicked_1(int position,Button btn) {
       CreateDig(position,btn);
    }

    @Override
    public void onItemClicked_2(int position,Button btn) {
        CreateDig(position,btn);
    }

    @Override
    public void onItemClicked_3(int position,Button btn) {
        CreateDig(position,btn);
    }

    @Override
    public void onItemClicked_4(int position,Button btn) {
        CreateDig(position,btn);
    }

    @Override
    public void onItemClicked_5(int position,Button btn) {
        CreateDig(position,btn);
    }

    @Override
    public void onItemClicked_6(int position,Button btn) {
        CreateDig(position,btn);
    }

    @Override
    public void onItemClicked_7(int position,Button btn) {
        CreateDig(position,btn);
    }

    @Override
    public void onItemClicked_8(int position,Button btn) {
        CreateDig(position,btn);
    }

    @Override
    public void onItemClicked_9(int position,Button btn) {
        CreateDig(position,btn);
    }

    @Override
    public void onItemClicked_10(int position,Button btn) {
        CreateDig(position,btn);
    }

    @Override
    public void onItemClicked_11(int position,Button btn) {
        CreateDig(position,btn);
    }

    @Override
    public void onItemClicked_12(int position,Button btn) {
        CreateDig(position,btn);
    }





    public void CreateDig(final int position,final Button btn)

    {

        /*GpsTracker gpsTracker = new GpsTracker(Activity_Test.this);
        double latitude = gpsTracker.getLatitude();
        double longitude = gpsTracker.getLongitude();

        if(latitude>(37.487712)||latitude<(37.486998))
        {
            Toast.makeText(Activity_Test.this, "학교 내에서만 예약이 가능합니다." + latitude + "\n벗어난 경도 " + longitude, Toast.LENGTH_LONG).show();
        }
        else {
        }*/
           // Toast.makeText(Activity_Test.this, "현재위치 \n위도 " + latitude + "\n경도 " + longitude, Toast.LENGTH_LONG).show();

        final Query query = myRef.child("Seat").child(ZONE);
        final String SeatNumber=Integer.toString(position);
        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(final DataSnapshot datasnapshot) {

                Query query = myRef.child("reservation").child(ZONE);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (datasnapshot.child(SeatNumber).child("status").getValue().equals(true)) {
                           /* if(snapshot.child(loginId).child("seatNum").getValue().equals(SeatNumber))
                            {
                                showToast("좌석반납");
                            }
                            else
                            {*/
                            showToast("이미 사용중인 좌석입니다.");

                        } else if (datasnapshot.child(SeatNumber).child("status").getValue().equals(false)) {

                            if (snapshot.hasChild(loginId)) {

                                showMsg(SeatNumber);
                            } else {
                                ReservationDialog reservationDialog = new ReservationDialog(context);
                                // 커스텀 다이얼로그를 호출한다.
                                reservationDialog.callFunction("QuietZone", SeatNumber,btn);
                            }
                        }

                        //선택된 좌석 선택시 오류


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("loadUser:onCancelled", databaseError.toException());
            }
        });


    }

    public void showMsg(final String SeatNumber) {
        final Utill utill = new Utill();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("안내");
        builder.setMessage("좌석을 변경하시겠습니까?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Query query = myRef.child("reservation").child("QuietZone").child(loginId);
                query.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot datasnapshot) {

                        String seatNum = datasnapshot.child("seatNum").getValue().toString();
                        SeatVO seatVO = new SeatVO("0", seatNum, "0",false);

                        myRef.child("Seat").child(ZONE).child(seatNum).setValue(seatVO);

                        seatVO = new SeatVO(loginId,SeatNumber, utill.getDate(),true);
                        myRef.child("Seat").child(ZONE).child(SeatNumber).setValue(seatVO)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {


                                        ReservationVO reservationVO = new ReservationVO(ZONE,SeatNumber, loginId, utill.getDate());
                                        myRef.child("reservation").child(ZONE).child(loginId).setValue(reservationVO);
                                        Log.e(ZONE, "좌석변경 성공");
                                        Toast.makeText(getApplicationContext(), "좌석 변경 완료", Toast.LENGTH_SHORT).show();
                                        createNotificationChannel(SeatNumber);
                                        //myAdapter.notifyDataSetChanged();
                                        finish();

                                    }
                                });


                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.w("loadUser:onCancelled", databaseError.toException());
                    }
                });


            }
        });

        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.e(ZONE, "좌석변경 취소");
                Toast.makeText(getApplicationContext(), "좌석 변경을 취소했습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "취소되었습니다.", Toast.LENGTH_LONG).show();
            } else if (Integer.parseInt(result.getContents()) <= 84) {

                final String SeatNumber = result.getContents();
                final Query query = myRef.child("Seat").child(ZONE);

                query.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(final DataSnapshot datasnapshot) {

                        Query query = myRef.child("reservation").child(ZONE);
                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                if (datasnapshot.child(SeatNumber).child("status").getValue().equals(true)) {

                                    showToast("이미 사용중인 좌석입니다.");

                                } else if (datasnapshot.child(SeatNumber).child("status").getValue().equals(false)) {

                                    if (snapshot.hasChild(loginId)) {

                                        showMsg(SeatNumber);
                                    } else {

                                      /*  SeatVO seatVO = new SeatVO(loginId, SeatNumber, true);
                                        myRef.child("Seat").child(ZONE).child(SeatNumber).setValue(seatVO)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {

                                                        ReservationVO reservationVO = new ReservationVO(ZONE, SeatNumber, loginId, utill.getDate());

                                                        myRef.child("reservation").child(ZONE).child(loginId).setValue(reservationVO);
                                                        Log.e(ZONE, "좌석예약 성공");
                                                        Toast.makeText(getApplicationContext(), "예약 완료", Toast.LENGTH_SHORT).show();
                                                        // btn.setBackground(ContextCompat.getDrawable(dlg.getContext(),R.drawable.round_bg_seat_my));

                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(getApplicationContext(), "예약 실패", Toast.LENGTH_SHORT).show();
                                                        Log.e(ZONE, "좌석예약 실패");


                                                    }
                                                });*/

                                        ReservationDialog reservationDialog = new ReservationDialog(Activity_Test.this);
                                        //커스텀 다이얼로그를 호출한다.
                                        reservationDialog.callFunctionPos("QuietZone", result.getContents());

                                        //Toast.makeText(getApplicationContext(), "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();


                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


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
    private void cancleNotifi()
    {
        notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }

    private void createNotificationChannel(String num) {

        notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

  /*      Intent notificationIntent = new Intent(this, QuietZone.class);
        notificationIntent.putExtra("notificationId", count); //전달할 값
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK) ;
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent,  PendingIntent.FLAG_UPDATE_CURRENT);
*/
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.logo)) //BitMap 이미지 요구
                .setContentTitle("QuietZone "+num+"번 좌석 사용 중")
                .setContentText("퇴실 전 반드시 좌석 반납처리 해주세요")
                .setDefaults(Notification.FLAG_FOREGROUND_SERVICE)
                // 더 많은 내용이라서 일부만 보여줘야 하는 경우 아래 주석을 제거하면 setContentText에 있는 문자열 대신 아래 문자열을 보여줌
                //.setStyle(new NotificationCompat.BigTextStyle().bigText("더 많은 내용을 보여줘야 하는 경우..."))
                .setPriority(NotificationCompat.PRIORITY_LOW)
                //.setContentIntent(pendingIntent) // 사용자가 노티피케이션을 탭시 ResultActivity로 이동하도록 설정
                .setAutoCancel(true)
                 .setOngoing(true);
        //OREO API 26 이상에서는 채널 필요
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            builder.setSmallIcon(R.drawable.logo); //mipmap 사용시 Oreo 이상에서 시스템 UI 에러남
            CharSequence channelName  = "노티페케이션 채널";
            String description = "오레오 이상을 위한 것임";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName , importance);
            channel.setDescription(description);

            // 노티피케이션 채널을 시스템에 등록
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);

        }else builder.setSmallIcon(R.mipmap.ic_main); // Oreo 이하에서 mipmap 사용하지 않으면 Couldn't create icon: StatusBarIcon 에러남

        assert notificationManager != null;
        notificationManager.notify(1234, builder.build()); // 고유숫자로 노티피케이션 동작시킴
    }


}
