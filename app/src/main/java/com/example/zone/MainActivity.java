package com.example.zone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.zone.LoginActivity.loginStatus;
import static com.example.zone.LoginActivity.loginId;

public class MainActivity extends AppCompatActivity {

    Button loginbtn,zone,my;
    TextView loginTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setTitle("Main");
        loginbtn = (Button)findViewById(R.id.loginButton);
        my=(Button)findViewById(R.id.mainMyReadingRoomButton);
        zone = (Button)findViewById(R.id.mainReadingRoomSelectButton);
        loginTv = (TextView)findViewById(R.id.loginText);


        if(loginStatus==true)
        {
            loginTv.setText("로그아웃");

        }
        else{
            loginTv.setText("로그인");
        }


    zone.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(loginStatus==true) {
                Intent intent = new Intent(getApplicationContext(), SeatActivity.class);
                startActivity(intent);
            }
            else
            {
             showToast("로그인 해 주세요");
            }
        }
    });


    my.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           Intent intent = new Intent(getApplicationContext(), MyInfoActivity.class);
            startActivity(intent);





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

    public void showToast(String msg)
    {
        Toast toast = Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT);
        toast.show();
    }
}
