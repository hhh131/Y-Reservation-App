package com.example.zone;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.zone.LoginActivity.loginId;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.zone.Vo.ReservationVO;
import com.example.zone.Vo.SeatVO;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class CustomDialog
{
        //좌석 효율적 관리를 위해,,

    private static final String TAG = "CustomDialog";
    private Context context;
     CheckBox AgreeCB;
    Dialog dlg;
    FirebaseDatabase database;
    DatabaseReference myRef;
    Utill utill;

    public CustomDialog(Context context) {
        this.context = context;
    }

    // 호출할 다이얼로그 함수를 정의한다.
    public void callFunction(final String Zone, final String SeatNum, final Button btn) {







      utill = new Utill();

        // 커스텀 다이얼로그를 정의하기위해 Dialog클래스를 생성한다.

         dlg = new Dialog(context);

        // 액티비티의 타이틀바를 숨긴다.
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 커스텀 다이얼로그의 레이아웃을 설정한다.
        dlg.setContentView(R.layout.custom_dialog);
        dlg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));



        WindowManager.LayoutParams params = dlg.getWindow().getAttributes();
        params.width=WindowManager.LayoutParams.MATCH_PARENT;
       // params.height=WindowManager.LayoutParams.MATCH_PARENT;


        // 커스텀 다이얼로그를 노출한다.
        dlg.show();




        // 커스텀 다이얼로그의 각 위젯들을 정의한다.

        final TextView message = (TextView) dlg.findViewById(R.id.mesgase);
        final TextView ZoneName = (TextView) dlg.findViewById(R.id.ZoneName);
        final TextView Seat = (TextView) dlg.findViewById(R.id.SeatNum);
        final Button OKbtn = (Button) dlg.findViewById(R.id.okButton);
        final Button back = (Button) dlg.findViewById(R.id.back);
        AgreeCB =(CheckBox)dlg.findViewById(R.id.AgreeCB);

        Seat.setText(SeatNum);
        ZoneName.setText(Zone);
        OKbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  database = FirebaseDatabase.getInstance();
                  myRef = database.getReference();

                  ZoneRe(btn,Zone);


            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg.dismiss();

            }
        });
    }


    public void ZoneRe( final Button btn, final String Zone)
    {

        if (AgreeCB.isChecked() == true) {


            Toast.makeText(context, "예약 완료", Toast.LENGTH_SHORT).show();
            btn.setBackgroundColor(Color.rgb(0, 255, 0));
            // 커스텀 다이얼로그를 종료한다.

            SeatVO seatVO = new SeatVO(loginId,btn.getText().toString(),true);
            myRef.child("Seat").child(Zone).child(btn.getText().toString()).setValue(seatVO)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                            ReservationVO reservationVO = new ReservationVO(Zone,btn.getText().toString(),loginId,utill.getDate());

                            myRef.child("reservation").child(Zone).child(loginId).setValue(reservationVO);
                            Log.e(TAG, "좌석예약 성공");


                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e(TAG, "좌석예약 실패");


                        }
                    });
            //showMsg();
            btn.setBackground(ContextCompat.getDrawable(dlg.getContext(),R.drawable.round_bg_seat_my));


            dlg.dismiss();

        } else {
            Toast.makeText(context, "동의 하셔야 좌석 예약이 가능합니다.", Toast.LENGTH_SHORT).show();
        }
    }

}
