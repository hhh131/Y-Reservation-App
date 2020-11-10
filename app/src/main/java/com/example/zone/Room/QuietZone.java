package com.example.zone.Room;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.zone.R;
import com.example.zone.ReservationDialog;
import com.example.zone.Utill;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import static com.example.zone.JoinLogin.LoginActivity.loginId;

public class QuietZone extends AppCompatActivity implements View.OnClickListener {
    public static final String NOTIFICATION_CHANNEL_ID = "10001";
    private static final String TAG = "QuietZone";
    int buttons[] = {R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6,
                     R.id.btn7, R.id.btn8, R.id.btn9, R.id.btn10, R.id.btn11, R.id.btn12};
    String buttonIndex[] = new String[buttons.length];
    private Button[] ButtonArray = new Button[buttons.length];
     IntentResult result;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    String MySeatNum,SeatNum;
    Button Sbutton,ReturnBtn;
    private Activity activity = this;

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
                        MySeatNum = snapshot.child("seatNum").getValue().toString();
                    } else {
                        MySeatNum = "null";
                    }

                    Query query = myRef.child("Seat").child(TAG);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {


                        @Override
                        public void onDataChange(DataSnapshot datasnapshot) {
                            for (int i = 0; i < 12; i++) {
                                if (datasnapshot.child(ButtonArray[i].getText().toString()).child("seatNum").getValue().equals(MySeatNum)) {
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


                    IntentIntegrator intentIntegrator = new IntentIntegrator(activity);
                    intentIntegrator.setOrientationLocked(false);
                    intentIntegrator.setBeepEnabled(false);//바코드 인식시 소리
                    intentIntegrator.initiateScan();







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
                    });*/

                }

            });
            ButtonArray[i].setOnClickListener(this);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        final Utill utill = new Utill();
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "취소되었습니다.", Toast.LENGTH_LONG).show();
            } else if (Integer.parseInt(result.getContents()) <= 84) {

                final String SeatNumber = result.getContents();
                final Query query = myRef.child("Seat").child(TAG);

                query.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(final DataSnapshot datasnapshot) {

                        Query query = myRef.child("reservation").child(TAG);
                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                if (datasnapshot.child(SeatNumber).child("status").getValue().equals(true)) {

                                    showToast("이미 사용중인 좌석입니다.");

                                } else if (datasnapshot.child(SeatNumber).child("status").getValue().equals(false)) {

                                    if (snapshot.hasChild(loginId)) {

                                        showMsg(Sbutton);
                                    } else {

                                      /*  SeatVO seatVO = new SeatVO(loginId, SeatNumber, true);
                                        myRef.child("Seat").child(TAG).child(SeatNumber).setValue(seatVO)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {

                                                        ReservationVO reservationVO = new ReservationVO(TAG, SeatNumber, loginId, utill.getDate());

                                                        myRef.child("reservation").child(TAG).child(loginId).setValue(reservationVO);
                                                        Log.e(TAG, "좌석예약 성공");
                                                        Toast.makeText(getApplicationContext(), "예약 완료", Toast.LENGTH_SHORT).show();
                                                        // btn.setBackground(ContextCompat.getDrawable(dlg.getContext(),R.drawable.round_bg_seat_my));

                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(getApplicationContext(), "예약 실패", Toast.LENGTH_SHORT).show();
                                                        Log.e(TAG, "좌석예약 실패");


                                                    }
                                                });*/

                                        ReservationDialog reservationDialog = new ReservationDialog(QuietZone.this);
                                        //커스텀 다이얼로그를 호출한다.
                                        reservationDialog.callFunction("QuietZone", result.getContents());

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

                        if(datasnapshot.child(SeatNumber).child("status").getValue().equals(true))
                        {
                            if(snapshot.child(loginId).child("seatNum").getValue().equals(SeatNumber))
                            {
                                showToast("좌석반납");
                            }
                            else
                            {
                            showToast("이미 사용중인 좌석입니다.");
                            }
                        }
                        else if(datasnapshot.child(SeatNumber).child("status").getValue().equals(false)) {

                            if (snapshot.hasChild(loginId)) {

                                showMsg(Sbutton);
                            }
                            else
                            {
                                CreateDig(Sbutton);
                            }
                        }









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




    public void CreateDig(Button btn)
    {
        ReservationDialog reservationDialog = new ReservationDialog(QuietZone.this);
        // 커스텀 다이얼로그를 호출한다.
        //reservationDialog.callFunction(TAG, btn.getText().toString(), btn);
    }







   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.menu1) {
           *//* Query query = myRef.child("reservation").child(TAG).child(loginId);
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

                                    *//**//*Intent intent = getIntent();
                                    finish();
                                    startActivity(intent);*//**//*
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
        }*//*


        }
        return true;
    }*/


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

           /*             String BeseatNum = datasnapshot.child("seatNum").getValue().toString();
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
                                });*/





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



    public void QrChange(final String newSeatNum) {
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

                     /*  String BefoseatNum = datasnapshot.child("seatNum").getValue().toString();
                        SeatVO seatVO = new SeatVO(null, BefoseatNum, false);
                        myRef.child("Seat").child(TAG).child(BefoseatNum).setValue(seatVO);

                        seatVO = new SeatVO(loginId, newSeatNum, true);
                        myRef.child("Seat").child(TAG).child(newSeatNum).setValue(seatVO)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {


                                        ReservationVO reservationVO = new ReservationVO(TAG,newSeatNum,loginId,utill.getDate());
                                        myRef.child("reservation").child(TAG).child(loginId).setValue(reservationVO);
                                        Log.e(TAG, "좌석변경 성공");
                                        Toast.makeText(getApplicationContext(), "좌석 변경 완료", Toast.LENGTH_SHORT).show();

                                        Intent intent = getIntent();
                                        finish();
                                        startActivity(intent);*/






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



