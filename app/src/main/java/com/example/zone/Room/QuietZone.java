package com.example.zone.Room;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.example.zone.ReservationDialog;
import com.example.zone.R;
import com.example.zone.Utill;
import com.example.zone.Vo.ReservationVO;
import com.example.zone.Vo.SeatVO;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static com.example.zone.LoginActivity.loginId;

public class QuietZone extends AppCompatActivity implements View.OnClickListener {
    public static final String NOTIFICATION_CHANNEL_ID = "10001";
    private static final String TAG = "QuietZone";
    int buttons[] = {R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5,R.id.btn6,
                     R.id.btn7, R.id.btn8, R.id.btn9, R.id.btn10, R.id.btn11,R.id.btn12};
    String buttonIndex[] = new String[buttons.length];
    private Button[] ButtonArray = new Button[buttons.length];

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    String SeatNum;
    Button Sbutton,ReturnBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiet);

        ReturnBtn=(Button)findViewById(R.id.ReturnSeat);


/*        NotificationCompat.Builder builder1 = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("My notification")
                .setContentText("Much longer text that cannot fit one line...")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Much longer text that cannot fit one line..."))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);*/


        setTitle("Quiet Zone");

        for (int i = 0; i < 12; i++) {
            ButtonArray[i] = (Button) findViewById(buttons[i]);
            buttonIndex[i] = ButtonArray[i].getText().toString();


            Query query = myRef.child("reservation").child(TAG).child(loginId);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.getValue() != null) {
                        SeatNum = snapshot.child("seatNum").getValue().toString();
                    } else {
                        SeatNum = "null";
                    }

                    Query query = myRef.child("Seat").child(TAG);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {


                        @Override
                        public void onDataChange(DataSnapshot datasnapshot) {
                            for (int i = 0; i < 12; i++) {
                                if (datasnapshot.child(ButtonArray[i].getText().toString()).child("seatNum").getValue().equals(SeatNum)) {
                                    ButtonArray[i].setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_bg_seat_my));
                            } else if (datasnapshot.child(ButtonArray[i].getText().toString()).child("status").getValue().equals(true)) {

                                    ButtonArray[i].setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_bg_seat_on));
                                }


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





















       /*     Query query = myRef.child("Seat").child("QuietZone").child(buttonIndex[i]);



                 query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot datasnapshot) {



                        if(datasnapshot.child("status").getValue().equals(true)) {
                            ButtonArray[0].setBackgroundColor(Color.rgb(200, 200, 0));
                            //ButtonArray[i].setTextColor(Color.RED);



                        }
                        else{
                            //ButtonArray[i].setBackgroundColor(Color.rgb(255,255,255));
                            //ButtonArray[i].setTextColor(Color.BLACK);
                        }

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.w("loadUser:onCancelled", databaseError.toException());
                    }
                });

*/

            ReturnBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Query query = myRef.child("reservation").child(TAG).child(loginId);
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

                            Query query = myRef.child("Seat").child(TAG).child(SeatNum);
                            query.addListenerForSingleValueEvent(new ValueEventListener() {


                                @Override
                                public void onDataChange(DataSnapshot datasnapshot) {

                                    try {
                                        if (datasnapshot.child("id").getValue().equals(loginId)) {
                                            SeatVO seatVO = new SeatVO(null, datasnapshot.child("seatNum").getValue().toString(), false);

                                            myRef.child("Seat").child(TAG).child(SeatNum).setValue(seatVO);
                                            myRef.child("reservation").child(TAG).child(loginId).removeValue();
                                            showToast("좌석 반납 완료");



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
            ButtonArray[i].setOnClickListener(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();




        for (int i = 0; i < 12; i++) {
            ButtonArray[i] = (Button) findViewById(buttons[i]);
            buttonIndex[i] = ButtonArray[i].getText().toString();


            Query query = myRef.child("reservation").child(TAG).child(loginId);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.getValue() != null) {
                        SeatNum = snapshot.child("seatNum").getValue().toString();
                    } else {
                        SeatNum = "null";
                    }

                    Query query = myRef.child("Seat").child(TAG);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {


                        @Override
                        public void onDataChange(DataSnapshot datasnapshot) {
                            for (int i = 0; i < 12; i++) {
                                if (datasnapshot.child(ButtonArray[i].getText().toString()).child("seatNum").getValue().equals(SeatNum)) {
                                    ButtonArray[i].setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_bg_seat_my));
                                } else if (datasnapshot.child(ButtonArray[i].getText().toString()).child("status").getValue().equals(true)) {

                                    ButtonArray[i].setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_bg_seat_on));
                                }


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



            ButtonArray[i].setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v)
    {


        //createNotificationChannel();
        Sbutton = (Button) v;
        final String SeatNumber=Sbutton.getText().toString();
        final Query query = myRef.child("Seat").child(TAG);

        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(final DataSnapshot datasnapshot) {

                Query query = myRef.child("reservation").child(TAG);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if (datasnapshot.child(SeatNumber).child("status").getValue().equals(true)) {


                        showToast("이미 예약된 좌석");
                    }
                   else if (snapshot.hasChild(loginId)&&snapshot.child(loginId).child("seatNum").getValue().equals(SeatNumber)) {
                        showToast("예약");
                        //if문 오류  만약 없으면 처리

                    } else if (snapshot.hasChild(loginId) && datasnapshot.child(SeatNumber).child("status").getValue().equals(false)) {
                        showMsg(Sbutton);

                        //showToast("내가 이미 예약한 자리가 있습니다.");
                    } else {
                        CreateDig(Sbutton);

                    }
                    //showToast("예약한 좌석이 있음");

                        /*
                        반납 처리 후 오류
                        좌석 변경후 reload
                        좌석 판단
                        동시 예약 제어
                        만약 예약한 좌석이 없을시 좌석 선택시 오류 처리
                        */


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


        //Toast.makeText(getApplicationContext(), Sbutton.getText() + "자리는 이미 예약되어있는 자리입니다.", Toast.LENGTH_SHORT).show();


    }



//노티피케이션

    private void createNotificationChannel() {

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

  /*      Intent notificationIntent = new Intent(this, QuietZone.class);
        notificationIntent.putExtra("notificationId", count); //전달할 값
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK) ;
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent,  PendingIntent.FLAG_UPDATE_CURRENT);
*/
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_foreground)) //BitMap 이미지 요구
                .setContentTitle("QuietZone 1번 좌석 예약중")
                .setContentText("퇴실 전 반드시 퇴실처리 해주세요")
                // 더 많은 내용이라서 일부만 보여줘야 하는 경우 아래 주석을 제거하면 setContentText에 있는 문자열 대신 아래 문자열을 보여줌
                //.setStyle(new NotificationCompat.BigTextStyle().bigText("더 많은 내용을 보여줘야 하는 경우..."))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                //.setContentIntent(pendingIntent) // 사용자가 노티피케이션을 탭시 ResultActivity로 이동하도록 설정
                .setAutoCancel(true);

        //OREO API 26 이상에서는 채널 필요
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            builder.setSmallIcon(R.drawable.ic_launcher_foreground); //mipmap 사용시 Oreo 이상에서 시스템 UI 에러남
            CharSequence channelName  = "노티페케이션 채널";
            String description = "오레오 이상을 위한 것임";
            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName , importance);
            channel.setDescription(description);

            // 노티피케이션 채널을 시스템에 등록
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);

        }else builder.setSmallIcon(R.mipmap.ic_launcher); // Oreo 이하에서 mipmap 사용하지 않으면 Couldn't create icon: StatusBarIcon 에러남

        assert notificationManager != null;
        notificationManager.notify(1234, builder.build()); // 고유숫자로 노티피케이션 동작시킴
        }




    public void CreateDig(Button btn)
    {
        ReservationDialog reservationDialog = new ReservationDialog(QuietZone.this);
        // 커스텀 다이얼로그를 호출한다.
        reservationDialog.callFunction(TAG, btn.getText().toString(), btn);
    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.menu1) {
           /* Query query = myRef.child("reservation").child(TAG).child(loginId);
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

                    Query query = myRef.child("Seat").child(TAG).child(SeatNum);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {


                        @Override
                        public void onDataChange(DataSnapshot datasnapshot) {

                            try {
                                if (datasnapshot.child("id").getValue().equals(loginId)) {
                                    SeatVO seatVO = new SeatVO(null, datasnapshot.child("seatNum").getValue().toString(), false);

                                    myRef.child("Seat").child(TAG).child(SeatNum).setValue(seatVO);
                                    myRef.child("reservation").child(TAG).child(loginId).removeValue();

                                    onResume();

                                    *//*Intent intent = getIntent();
                                    finish();
                                    startActivity(intent);*//*
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
        else
        {
            finish();
        }*/


        }
        return true;
    }


    public void showToast(String msg) {
        Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        toast.show();
    }


    public void showMsg(final Button btn) {
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

                       String BeseatNum = datasnapshot.child("seatNum").getValue().toString();
                        SeatVO seatVO = new SeatVO(null, BeseatNum, false);
                        myRef.child("Seat").child(TAG).child(BeseatNum).setValue(seatVO);

                        seatVO = new SeatVO(loginId, btn.getText().toString(), true);
                        myRef.child("Seat").child(TAG).child(btn.getText().toString()).setValue(seatVO)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {


                                        ReservationVO reservationVO = new ReservationVO(TAG,btn.getText().toString(),loginId,utill.getDate());
                                        myRef.child("reservation").child(TAG).child(loginId).setValue(reservationVO);
                                        Log.e(TAG, "좌석변경 성공");
                                        Toast.makeText(getApplicationContext(), "좌석 변경 완료", Toast.LENGTH_SHORT).show();

                                        Intent intent = getIntent();
                                        finish();
                                        startActivity(intent);


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
                Log.e(TAG, "좌석변경 성공");
                Toast.makeText(getApplicationContext(), "좌석 변경을 취소했습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
/*

   private View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button newButton = (Button) v;
            boolean check = false;
            int i = 0;

            while (check) {
                if (ButtonArray[i].getId() == newButton.getId()) {
                    check = true;
                    i++;
                }
                i++;

            }
            check = false;
            Toast toast = Toast.makeText(getApplicationContext(), buttonIndex[i] + "자리는 이미 예약되어있는 자리입니다.", Toast.LENGTH_SHORT);
            toast.show();
            i = 0;

        }
    };
*/



