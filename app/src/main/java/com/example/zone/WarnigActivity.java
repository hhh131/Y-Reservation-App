package com.example.zone;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zone.JoinLogin.LoginActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static com.example.zone.JoinLogin.LoginActivity.loginId;
import static com.example.zone.JoinLogin.LoginActivity.loginStatus;

public class WarnigActivity extends AppCompatActivity {

    TextView WaringMsg;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    String waringMsg="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warnig);
        WaringMsg = (TextView)findViewById(R.id.waringMsg);
        if(loginStatus==false)
        {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.putExtra("loginInfo","0");
            startActivity(intent);

            finish();
            Toast.makeText(getApplicationContext(),"로그인이 필요합니다.",Toast.LENGTH_SHORT);
        }

        final Query query = myRef.child("reportList");
        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot datasnapshot) {
                    for (int i =0;i<datasnapshot.getChildrenCount();i++)
                    {
                            if(datasnapshot.child(Integer.toString(i)).child("id").getValue().toString().equals(loginId))
                            {
                                waringMsg+=""
                                         +datasnapshot.child(Integer.toString(i)).child("message").getValue().toString()+"\n\n";
                                WaringMsg.setText(waringMsg);
                            }

                    }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("loadUser:onCancelled", databaseError.toException());
            }
        });






    }
}