package com.example.zone.Room;

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

import static com.example.zone.JoinLogin.LoginActivity.loginId;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zone.ReservationDialog;
import com.example.zone.R;
import com.example.zone.Vo.SeatVO;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DVDZone extends AppCompatActivity {

    private static final String TAG = "DVDZone";
    Button btns[] = new Button[3];
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    int a = 0;
    int b = 0;
    String SeatNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dvd);

        btns[0] = (Button) findViewById(R.id.btn1);
        btns[1] = (Button) findViewById(R.id.btn2);
        btns[2] = (Button) findViewById(R.id.btn3);







        Query query = myRef.child("reservation").child("DvdZone").child(loginId);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
          @Override
         public void onDataChange(@NonNull DataSnapshot snapshot) {
            if(snapshot.getValue()!=null) {
                SeatNum = snapshot.child("seatNum").getValue().toString();
            }
            else {
                SeatNum="null";
            }

              Query query = myRef.child("Seat").child("DvdZone");
              query.addListenerForSingleValueEvent(new ValueEventListener() {


                  @Override
                  public void onDataChange(DataSnapshot datasnapshot) {
                      for(int i=0;i<3;i++) {
                          if(datasnapshot.child(btns[i].getText().toString()).child("seatNum").getValue().equals(SeatNum))
                          {
                              btns[i].setBackgroundColor(Color.rgb(0,255,0));
                          }

                          else if (datasnapshot.child(btns[i].getText().toString()).child("status").getValue().equals(true)){

                              btns[i].setBackgroundColor(Color.rgb(255, 0, 0));
                              btns[i].setTextColor(Color.rgb(255,255,255));
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


                btns[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Check(btns[0]);
            }
        });

        btns[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Check(btns[1]);
            }
        });


        btns[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Check(btns[2]);
            }
        });


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
            Query query = myRef.child("reservation").child("DvdZone").child(loginId);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                    try {
                        SeatNum = datasnapshot.child("seatNum").getValue().toString();
                    } catch (Exception e) {
                        showToast("반납할 좌석이 없습니다.");
                    }


                    Query query = myRef.child("Seat").child("DvdZone").child(SeatNum);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {


                        @Override
                        public void onDataChange(DataSnapshot datasnapshot) {

                            try {
                                if (datasnapshot.child("id").getValue().equals(loginId)) {
                                    SeatVO seatVO = new SeatVO(null, datasnapshot.child("seatNum").getValue().toString(), false);

                                    myRef.child("Seat").child("DvdZone").child(SeatNum).setValue(seatVO);
                                    myRef.child("reservation").child("DvdZone").child(loginId).removeValue();

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


        }else
        {
            finish();
        }


        return true;

    }

    public void showToast(String msg) {
        Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        toast.show();
    }


    public void CreateDig(Button btn)
    {
        ReservationDialog reservationDialog = new ReservationDialog(DVDZone.this);

        // 커스텀 다이얼로그를 호출한다.

        //reservationDialog.callFunction("DvdZone", btn.getText().toString(), btn);
    }


    public void Check(final Button btn)
    {
        final Query query = myRef.child("Seat").child("DvdZone");
        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(final DataSnapshot datasnapshot) {
                Query query = myRef.child("reservation").child("DvdZone");
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    //이부분 음.. 회원 예약한 자리 알려주기 내가 예약한 자리 선택하면 내가 예약한 자리. 다른 자리 누르면 좌석이동.
                        if (snapshot.hasChild(loginId)) {
                            showToast("내가 이미 예약한 자리");
                    }
                        else if (datasnapshot.child(btn.getText().toString()).child("status").getValue().equals(true)) {
                                showToast("이미 예약된 좌석");
                        }

                        else
                        {
                            CreateDig(btn);

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

}




