package com.example.zone.Room;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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
import androidx.core.content.ContextCompat;

import com.example.zone.CustomDialog;
import com.example.zone.R;
import com.example.zone.Vo.SeatVO;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static com.example.zone.LoginActivity.loginStatus;
import static com.example.zone.LoginActivity.loginId;

public class QuietZone extends AppCompatActivity {
    private static final String TAG = "QuietZone";
    int buttons[] = {R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5};
    String buttonIndex[] = new String[buttons.length];
    private Button[] ButtonArray = new Button[buttons.length];
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    String SeatNum;
    Button Sbutton;
    String Sbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiet);

        setTitle("Quiet Zone");

        for (int i = 0; i < 5; i++) {
            ButtonArray[i] = (Button) findViewById(buttons[i]);
            buttonIndex[i] = ButtonArray[i].getText().toString();


            Query query = myRef.child("reservation").child("QuietZone").child(loginId);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.getValue() != null) {
                        SeatNum = snapshot.child("seatNum").getValue().toString();
                    } else {
                        SeatNum = "null";
                    }

                    Query query = myRef.child("Seat").child("QuietZone");
                    query.addListenerForSingleValueEvent(new ValueEventListener() {


                        @Override
                        public void onDataChange(DataSnapshot datasnapshot) {
                            for (int i = 0; i < 5; i++) {
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


            ButtonArray[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Sbutton = (Button) view;
                    final Query query = myRef.child("Seat").child("QuietZone");
                    query.addListenerForSingleValueEvent(new ValueEventListener() {

                        @Override
                        public void onDataChange(DataSnapshot datasnapshot) {
                            if (datasnapshot.child(Sbutton.getText().toString()).child("status").getValue().equals(true)) {
                                showToast("이미 예약된 좌석");
                            }
                                //아직수정중


                            else {

                                // 커스텀 다이얼로그를 생성한다. 사용자가 만든 클래스이다.
                                CustomDialog customDialog = new CustomDialog(QuietZone.this);

                                // 커스텀 다이얼로그를 호출한다.
                                // 커스텀 다이얼로그의 결과를 출력할 TextView를 매개변수로 같이 넘겨준다.
                                customDialog.callFunction("QuietZone", Sbutton.getText().toString(), Sbutton);
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.w("loadUser:onCancelled", databaseError.toException());
                        }
                    });


                    //Toast.makeText(getApplicationContext(), Sbutton.getText() + "자리는 이미 예약되어있는 자리입니다.", Toast.LENGTH_SHORT).show();


                }
            });
        }

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
            Query query = myRef.child("reservation").child("QuietZone").child(loginId);
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

                    Query query = myRef.child("Seat").child("QuietZone").child(SeatNum);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {


                        @Override
                        public void onDataChange(DataSnapshot datasnapshot) {

                            try {
                                if (datasnapshot.child("id").getValue().equals(loginId)) {
                                    SeatVO seatVO = new SeatVO(null, datasnapshot.child("seatNum").getValue().toString(), false);

                                    myRef.child("Seat").child("QuietZone").child(SeatNum).setValue(seatVO);
                                    myRef.child("reservation").child("QuietZone").child(loginId).removeValue();

                                    Intent intent = getIntent();
                                    finish();
                                    startActivity(intent);
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
        }



        return true;
    }


    public void showToast(String msg) {
        Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        toast.show();
    }


    public void showMsg() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("안내");
        builder.setMessage("좌석을 반납하시겠습니까?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                 Query query = myRef.child("Seat").child("QuietZone").child(Sbtn);
                query.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot datasnapshot) {
                        if(datasnapshot.child("id").equals(loginId))
                        {

                            Toast.makeText(getApplicationContext(),"tqtq",Toast.LENGTH_SHORT).show();

                          /*  myRef.child("id").setValue("")
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                            public void onSuccess(Void aVoid) {
                            Log.e(TAG, "좌석예약 성공");
                            // ShowToast("회원가입 성공");
                            //finish();
                        }
                    })
                            .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e(TAG, "좌석예약 실패");
                            //ShowToast("회원가입 실패");

                        }
                    });*/



                }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"tqtq",Toast.LENGTH_SHORT).show();
                        }




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

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}

    /*private View.OnClickListener btnListener = new View.OnClickListener() {
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
    };*/


