package com.example.zone.Room;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.zone.ReservationDialog;
import com.example.zone.R;
import com.example.zone.Vo.SeatVO;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static com.example.zone.JoinLogin.LoginActivity.loginId;
public class PcZoneActivity extends AppCompatActivity {
    int buttons[] = {R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5};
    String buttonIndex[] = new String[buttons.length];
    private Button[] ButtonArray = new Button[buttons.length];
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    private static final String TAG = "PcZoneActivity";
    String SeatNum;
    Button Sbutton;
    String Sbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pc_zone);
        for (int i = 0; i < 5; i++) {
            ButtonArray[i] = (Button) findViewById(buttons[i]);
            buttonIndex[i] = ButtonArray[i].getText().toString();


            Query query = myRef.child("reservation").child("PcZone").child(loginId);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.getValue() != null) {
                        SeatNum = snapshot.child("seatNum").getValue().toString();
                    } else {
                        SeatNum = "null";
                    }

                    Query query = myRef.child("Seat").child("PcZone");
                    query.addListenerForSingleValueEvent(new ValueEventListener() {


                        @Override
                        public void onDataChange(DataSnapshot datasnapshot) {
                            for (int i = 0; i < 5; i++) {
                                if (datasnapshot.child(ButtonArray[i].getText().toString()).child("seatNum").getValue().equals(SeatNum)) {
                                    ButtonArray[i].setBackgroundColor(Color.rgb(0, 255, 0));
                                } else if (datasnapshot.child(ButtonArray[i].getText().toString()).child("status").getValue().equals(true)) {

                                    ButtonArray[i].setBackgroundColor(Color.rgb(255, 0, 0));
                                    ButtonArray[i].setTextColor(Color.rgb(255, 255, 255));
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






















       /*     Query query = myRef.child("Seat").child("PcZone").child(buttonIndex[i]);



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
                    final Query query = myRef.child("Seat").child("PcZone");
                    query.addListenerForSingleValueEvent(new ValueEventListener() {

                        @Override
                        public void onDataChange(DataSnapshot datasnapshot) {
                            if (datasnapshot.child(Sbutton.getText().toString()).child("status").getValue().equals(true)) {
                                showToast("이미 예약된 좌석");
                            }
                            //아직수정중


                            else {

                                CreateDig(Sbutton);
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
            Query query = myRef.child("reservation").child("PcZone").child(loginId);
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

                    Query query = myRef.child("Seat").child("PcZone").child(SeatNum);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {


                        @Override
                        public void onDataChange(DataSnapshot datasnapshot) {

                            try {
                                if (datasnapshot.child("id").getValue().equals(loginId)) {
                                    SeatVO seatVO = new SeatVO(null, datasnapshot.child("seatNum").getValue().toString(), false);

                                    myRef.child("Seat").child("PcZone").child(SeatNum).setValue(seatVO);
                                    myRef.child("reservation").child("PcZone").child(loginId).removeValue();

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
    public void CreateDig(Button btn)
    {
        ReservationDialog reservationDialog = new ReservationDialog(PcZoneActivity.this);

        // 커스텀 다이얼로그를 호출한다.

        //reservationDialog.callFunction("PcZone", btn.getText().toString(), btn);
    }


    public void showToast(String msg) {
        Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        toast.show();
    }

}
