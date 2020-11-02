package com.example.zone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import static com.example.zone.JoinLogin.LoginActivity.loginId;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zone.Vo.ReportVO;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ReportActivity extends AppCompatActivity {

    private static final String TAG = "ReportActivity";
    TextView id, date;
    EditText reportEditText;
    Button reportBtn;
    String reportText;
    int noCnt;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        id = (TextView) findViewById(R.id.id);
        date = (TextView) findViewById(R.id.date);
        reportEditText = (EditText) findViewById(R.id.report);

        reportBtn = (Button) findViewById(R.id.reportbtn);

        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");
        final String getTime = simpleDate.format(mDate);
        id.setText(loginId);
        date.setText(getTime);

        reportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                  myRef = database.getReference();


                 Query query = myRef.child("Report");
                query.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot datasnapshot) {

                  noCnt=(int)datasnapshot.getChildrenCount();

                        reportText = reportEditText.getText().toString();
                        ReportVO reportVO = new ReportVO(noCnt,loginId, getTime, reportText,false);
                        myRef.child("Report").push().setValue(reportVO)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.e(TAG, "신고 완료");
                                        showToast("신고 완료");
                                        finish();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.e(TAG, "신고 에러");
                                        showToast("신고하는 도중에 에러가 발생하였습니다.");

                                    }
                                });



                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.w("loadUser:onCancelled", databaseError.toException());
                    }
                });





            }
        });


    }


    public void showToast(String msg) {
        Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        toast.show();
    }


}

