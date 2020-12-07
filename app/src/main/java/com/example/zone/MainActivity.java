package com.example.zone;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zone.JoinLogin.LoginActivity;
import com.example.zone.PCZone.PC;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static com.example.zone.JoinLogin.LoginActivity.loginId;
import static com.example.zone.JoinLogin.LoginActivity.loginStatus;

public class MainActivity extends AppCompatActivity {
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    String[] REQUIRED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

    Button loginbtn, zone, report;
    TextView loginTv, myZone, mySeatNum;
    Boolean BlackCheck = false;
    ImageView img;
    String reverInfo, SeatNum;
    private long lastTimeBackPressed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("도서관 시설 예약");
        setTheme(android.R.style.Theme_NoTitleBar);

        report = (Button) findViewById(R.id.reportbtn);
        zone = (Button) findViewById(R.id.mainReadingRoomSelectButton);

       // img = (ImageView) findViewById(R.id.imageView1);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();




   /*     final Geocoder geocoder = new Geocoder(getApplicationContext());
        String value = "특정지역의 명칭"; //EX) xx역, 63빌딩, 롯데월드 등등
       List<Address> list=null;
        String str = value;
        try {
            list = geocoder.getFromLocationName(str, 10);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (list != null) {
            if (list.size() == 0) {
                Toast.makeText(getApplicationContext(), "해당되는 주소 정보를 찾지 못했습니다.", Toast.LENGTH_LONG).show();
            } else {
                Address addr = list.get(0);
                addr.getLatitude(); // String value에 대한 위도값
                addr.getLongitude();   // String value에 대한 경도값
            }
        }
*/









        if(loginStatus==true) {







            Query query2 = myRef.child("blackList");
            query2.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot datasnapshot) {
                    int ListCnt=(int)datasnapshot.getChildrenCount();
                    for(int i=1;i<=ListCnt;i++)
                    {
                        if(datasnapshot.child(Integer.toString(i)).child("id").getValue().toString().equals(loginId))
                        {
                            BlackCheck=true;
                        }
                        else
                        {
                            BlackCheck=false;
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.w("loadUser:onCancelled", databaseError.toException());
                }
            });

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
                    showToast("블랙 리스트에 등록되어 3개월간 이용하실 수 없습니다.");
                }
                }
            else
            {
             showToast("로그인 해 주세요");
            }
        }
    });



        report.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {










            if(loginStatus==true) {
                if (BlackCheck == false) {
                    Intent intent = new Intent(getApplicationContext(), ReportActivity.class);
                    startActivity(intent);
                } else {
                    showToast("블랙 리스트에 등록되어 3개월간 이용하실 수 없습니다.");
                }
            }
            else
            {
                showToast("로그인 해 주세요");
            }



                //LogcatLogger.d(TAG, "* * * * Camera");
                //Toast.makeText(this, "Camera", Toast.LENGTH_SHORT).show();








        }
    });

    }
/*
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
*/



    @Override
    public void onBackPressed() {
        //2초 이내에 뒤로가기 버튼을 재 클릭 시 앱 종료
        if (System.currentTimeMillis() - lastTimeBackPressed< 2000)
        {
            finish();
            return;
        }
        //'뒤로' 버튼 한번 클릭 시 메시지
        Toast.makeText(this, "'뒤로' 버튼을 한번 더 누르시면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show();
        //lastTimeBackPressed에 '뒤로'버튼이 눌린 시간을 기록
        lastTimeBackPressed = System.currentTimeMillis();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        if(loginStatus==true)
        {
            loginStatus=false;
            loginId="";
            showToast("로그아웃 되었습니다.");
            // loginTv.setText("로그인");
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.putExtra("login",1);
            startActivity(intent);
            finish();
        }
        else
        {
            loginStatus=false;
            loginId="";
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.putExtra("login",1);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }





    public void showToast(String msg)
    {
        Toast toast = Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT);
        toast.show();
    }






}
