package com.example.zone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import static com.example.zone.LoginActivity.loginId;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        id = (TextView) findViewById(R.id.id);
        date = (TextView) findViewById(R.id.date);
        reportEditText = (EditText) findViewById(R.id.report);

        reportBtn = (Button) findViewById(R.id.reportbtn);

        Date Time = Calendar.getInstance().getTime();
        final String date_text = new SimpleDateFormat("yyyy년 MM월 dd일", Locale.getDefault()).format(Time);

        id.setText(loginId);
        date.setText(date_text);

        reportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference();
                reportText = reportEditText.getText().toString();
                ReportVO reportVO = new ReportVO(loginId, date_text, reportText);
                myRef.child("Report").push().setValue(reportVO)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.e(TAG, "회원가입 성공");
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
        });


    }


    public void showToast(String msg) {
        Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        toast.show();
    }


}

