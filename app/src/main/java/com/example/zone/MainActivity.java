package com.example.zone;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ShowableListMenu;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zone.Room.QuietZone;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import static com.example.zone.LoginActivity.loginStatus;
import static com.example.zone.LoginActivity.loginId;

public class MainActivity extends AppCompatActivity {

    Button loginbtn,zone,report;
    TextView loginTv,myZone,mySeatNum;
    Boolean BlackCheck=false;
    ImageView img;
    String reverInfo,SeatNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("도서관 시설 예약");
        loginbtn = (Button)findViewById(R.id.loginButton);
        report=(Button)findViewById(R.id.mainMyReadingRoomButton);
        zone = (Button)findViewById(R.id.mainReadingRoomSelectButton);
        loginTv = (TextView)findViewById(R.id.loginText);

        img = (ImageView)findViewById(R.id.imageView1);
         FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/hhh131/Zone"));
                startActivity(intent);
            }
        });










        if(loginStatus==true) {

            loginTv.setText("로그아웃");

            Query query = myRef.child("reservation").child("QuietZone");
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot datasnapshot) {
                        if(datasnapshot.hasChild(loginId))
                        {
                            reverInfo=datasnapshot.child(loginId).child("reservationType").getValue().toString();
                            SeatNum=datasnapshot.child(loginId).child("seatNum").getValue().toString();



                        }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.w("loadUser:onCancelled", databaseError.toException());
                }
            });











            Query query2 = myRef.child("User").child(loginId);
            query2.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot datasnapshot) {
                    long i = (long) datasnapshot.child("report").getValue();
                    if (i >= 10) {
                        BlackCheck = true;
                    }
                    else
                    {
                        BlackCheck = false;
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.w("loadUser:onCancelled", databaseError.toException());
                }
            });

        }
        else {
            loginTv.setText("로그인");

        }


    zone.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(loginStatus==true) {

                if(BlackCheck==false) {
                    Intent intent = new Intent(getApplicationContext(), List.class);
                    startActivity(intent);
                }
                else
                {
                    showToast("당신은 블랙리스트입니다.");
                }
                }
            else
            {
             showToast("로그인 해 주세요");
            }
        }
    });

        /*IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setBeepEnabled(false);//바코드 인식시 소리
        intentIntegrator.initiateScan();*/
    report.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          /*  if(loginStatus==true) {
                Intent intent = new Intent(getApplicationContext(), ReportActivity.class);
                startActivity(intent);
            }
                else
            {
                showToast("로그인 해 주세요");
            }
*/


                //LogcatLogger.d(TAG, "* * * * Camera");
                //Toast.makeText(this, "Camera", Toast.LENGTH_SHORT).show();








        }
    });
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(loginStatus==true)
                {
                    loginStatus=false;
                    loginId="";
                    showToast("로그아웃 되었습니다.");
                    loginTv.setText("로그인");

                }
                else {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
                }

        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }



    @Override
    public void onBackPressed() {
        // 뒤로가기 방지
    }



    public void showToast(String msg)
    {
        Toast toast = Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT);
        toast.show();
    }
}
