package com.example.zone;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import static com.example.zone.JoinLogin.LoginActivity.loginId;
public class WarnigActivity extends AppCompatActivity {

    TextView WaringMsg;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warnig);
        WaringMsg = (TextView)findViewById(R.id.waringMsg);


        final Query query = myRef.child("userwaring");
        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot datasnapshot) {

                if (datasnapshot.hasChild(loginId)) {
                    String waringMsg=datasnapshot.child(loginId).child("msg").getValue().toString();
                    WaringMsg.setText(waringMsg);

                } else {

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("loadUser:onCancelled", databaseError.toException());
            }
        });






    }
}