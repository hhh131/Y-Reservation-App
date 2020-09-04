package com.example.zone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main extends AppCompatActivity {

    Button loginbtn,zone,my;
    TextView loginTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setTitle("상단바");
        loginbtn = (Button)findViewById(R.id.loginButton);
        my=(Button)findViewById(R.id.mainMyReadingRoomButton);
        zone = (Button)findViewById(R.id.mainReadingRoomSelectButton);
        loginTv = (TextView)findViewById(R.id.loginText);

    zone.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
             Intent intent = new Intent(getApplicationContext(),List.class);
             startActivity(intent);
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

                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }

        });
    }
}
