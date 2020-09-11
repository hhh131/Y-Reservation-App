package com.example.zone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static com.example.zone.LoginActivity.loginStatus;
import static com.example.zone.LoginActivity.loginId;
import org.w3c.dom.Text;

public class MyInfoActivity extends AppCompatActivity {
TextView idTv,nameTv;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        setTitle("상단바");




        idTv=(TextView)findViewById(R.id.idTv);
        nameTv=(TextView)findViewById(R.id.nameTv);
        idTv.setText(loginId);
        info(loginId);
        nameTv.setText("asd");

    }

    public void info(final String id) {
        final Query query = myRef.child("User");
        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot datasnapshot) {
                if (datasnapshot.hasChild(id)) {


                 nameTv.setText(datasnapshot.child(id).child("name").getValue().toString());


                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("loadUser:onCancelled", databaseError.toException());
            }
        });
    }
}