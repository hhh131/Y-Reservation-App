package com.example.zone.Room;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import static com.example.zone.LoginActivity.loginStatus;
import static com.example.zone.LoginActivity.loginId;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zone.CustomDialog;
import com.example.zone.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DVDZone extends AppCompatActivity {


    Button btns[] = new Button[3];
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    int a = 0;
    int b = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dvd);

        btns[0] = (Button) findViewById(R.id.btn1);
        btns[1] = (Button) findViewById(R.id.btn2);
        btns[2] = (Button) findViewById(R.id.btn3);





        Query query = myRef.child("Seat").child("DvdZone");
        query.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot datasnapshot) {
                for(int i=0;i<3;i++) {
                    if (datasnapshot.child(btns[i].getText().toString()).child("status").getValue().equals(true)){

                        btns[i].setBackgroundColor(Color.rgb(255, 0, 0));
                        btns[i].setTextColor(Color.rgb(255,255,255));
                    }
                }
            }




            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("loadUser:onCancelled", databaseError.toException());
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


        Query query = myRef.child("Seat").child("DvdZone").child(btns[0].getText().toString());
        query.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot datasnapshot) {

                    if (datasnapshot.child("id").getValue().equals(loginId)){
                        myRef.child("Seat").child("DvdZone").child(btns[0].getText().toString()).child("id").setValue("null");
                        showToast("있네아이이닥");

                    }

            }




            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("loadUser:onCancelled", databaseError.toException());
            }
        });

        return true;

    }

    public void showToast(String msg) {
        Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        toast.show();
    }


    public void CreateDig(Button btn)
    {
        CustomDialog customDialog = new CustomDialog(DVDZone.this);

        // 커스텀 다이얼로그를 호출한다.

        customDialog.callFunction("DvdZone", btn.getText().toString(), btn);
    }


    public void Check(final Button btn)
    {
        Query query = myRef.child("Seat").child("DvdZone");
        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot datasnapshot) {

                    if (datasnapshot.child(btn.getText().toString()).child("status").getValue().equals(true))
                    {
                        showToast("이미 예약된 좌석");
                    }
                    else {
                        CreateDig(btn);
                    }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("loadUser:onCancelled", databaseError.toException());
            }
        });
    }

}




