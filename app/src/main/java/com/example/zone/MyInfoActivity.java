package com.example.zone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import static com.example.zone.JoinLogin.LoginActivity.loginId;

public class MyInfoActivity extends AppCompatActivity {
    TextView myId,mySeat,myTime;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        myId=(TextView)findViewById(R.id.myId);
        mySeat=(TextView)findViewById(R.id.mySeat);
        myTime = (TextView)findViewById(R.id.myZone);


       final  Query query = myRef.child("User");
        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot datasnapshot) {

                if (datasnapshot.hasChild(loginId)) {
                    String Id = datasnapshot.child(loginId).child("id").getValue().toString();

                    myId.setText(Id);

                    }
             else {
                    Log.e("loginCheck : ", "해당 아이디가 존재하지 않습니다.");
                    //showToast("해당아이디가 존재하지 않습니다.");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("loadUser:onCancelled", databaseError.toException());
            }
        });

         final Query query2 = myRef.child("reservation").child("QuietZone");
        query2.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot datasnapshot) {

                if (datasnapshot.hasChild(loginId)) {
                    String MySeatNum=datasnapshot.child(loginId).child("seatNum").getValue().toString();
                    String ReservationTime=datasnapshot.child(loginId).child("date").getValue().toString();
                    myTime.setText(ReservationTime);
                    mySeat.setText(MySeatNum);


                }
                else {

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("loadUser:onCancelled", databaseError.toException());
            }
        });









    }
}