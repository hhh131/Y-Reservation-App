package com.example.zone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.example.zone.JoinLogin.LoginActivity.loginId;

public class ReportActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    private static final String TAG = "ReportActivity";
    private Spinner crimer,Zone;
    List<String> uselist = new ArrayList<String>();
    List<String> useZone = new ArrayList<String>();
    TextView id, date;
    EditText reportEditText;
    Button reportBtn,waring;
    String reportText;
    int noCnt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        id = (TextView) findViewById(R.id.id);
        date = (TextView) findViewById(R.id.date);
        reportEditText = (EditText) findViewById(R.id.report);
        crimer = (Spinner)findViewById(R.id.crimer);
        waring=(Button)findViewById(R.id.waring);
        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");
        final String getTime = simpleDate.format(mDate);
        uselist.add("--선택--");
                    //uselist.add("12");
       final Query query = myRef.child("Seat").child("QuietZone");
        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for(int i =1;i<85;i++) {

                    if(datasnapshot.child(Integer.toString(i)).child("status").getValue().equals(true)){
                        uselist.add(Integer.toString(i));
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        waring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WarnigActivity.class);
                startActivity(intent);
            }
        });

     /*   for (int i =1;i<85;i++) {
            uselist.add(Integer.toString(i));
        }*/
        ArrayAdapter<String> crimeradapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, uselist);

        crimeradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        crimer.setAdapter(crimeradapter);

        reportBtn = (Button) findViewById(R.id.reportbtn);

        Date Time = Calendar.getInstance().getTime();
        final String date_text = new SimpleDateFormat("yyyy년 MM월 dd일", Locale.getDefault()).format(Time);

        id.setText(loginId);
        date.setText(date_text);

        reportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(crimer.getSelectedItem().equals("--선택--"))
                {
                    showToast("좌석을 선택 해 주세요");
                }
                else {
                    if (reportEditText.getText().toString().equals("")) {
                        showToast("신고내용을 입력 해 주세요");
                    }//좌석선택 안했을시 if 추가 하기
                    else {
                        reportText = reportEditText.getText().toString();

                        Query query = myRef.child("Report");
                        query.addListenerForSingleValueEvent(new ValueEventListener() {

                            @Override
                            public void onDataChange(DataSnapshot datasnapshot) {

                                noCnt = (int) datasnapshot.getChildrenCount();
                                ReportVO reportVO = new ReportVO(noCnt, loginId, getTime, reportText, false, crimer.getSelectedItem().toString());
                                myRef.child("Report").push().setValue(reportVO)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Log.e(TAG, "신고 성공");
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
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }
            }
        });




    }


    public void showToast(String msg) {
        Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        toast.show();
    }


}

