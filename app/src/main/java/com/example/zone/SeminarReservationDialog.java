package com.example.zone;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
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

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.zone.Adapter.MyAdapter;
import com.example.zone.Room.MysmallAdapter;
import com.example.zone.Room.MyuntilAdapter;
import com.example.zone.Vo.SeminarVO;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static com.example.zone.JoinLogin.LoginActivity.loginId;

public class SeminarReservationDialog {
    //좌석 효율적 관리를 위해,,

    private static final String TAG = "CustomDialog";
    private Context context;
    CheckBox AgreeCB;
    Dialog dlg;
    int CT;
    FirebaseDatabase database;
    DatabaseReference myRef;
    MysmallAdapter smalladapter;
    MyuntilAdapter untiladapter;
    Utill utill;
    Boolean status;
    MyAdapter myAdapter;
    public static final String NOTIFICATION_CHANNEL_ID = "10001";
    public SeminarReservationDialog(Context context) {
        this.context = context;
    }

    // 호출할 다이얼로그 함수를 정의한다.
    public void callFunction(final String Zone, final String RoomNum, final String dayString, final String timeString, final int checkuntilk, final MysmallAdapter smalladapter) {


        utill = new Utill();

        // 커스텀 다이얼로그를 정의하기위해 Dialog클래스를 생성한다.

        dlg = new Dialog(context);

        // 액티비티의 타이틀바를 숨긴다.
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 커스텀 다이얼로그의 레이아웃을 설정한다.
        dlg.setContentView(R.layout.seminar_dialog);
        dlg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        WindowManager.LayoutParams params = dlg.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        // params.height=WindowManager.LayoutParams.MATCH_PARENT;


        // 커스텀 다이얼로그를 노출한다.
        dlg.show();


        // 커스텀 다이얼로그의 각 위젯들을 정의한다.

        final TextView message = (TextView) dlg.findViewById(R.id.mesgase);
        final TextView ZoneName = (TextView) dlg.findViewById(R.id.ZoneName);
        final TextView Seat = (TextView) dlg.findViewById(R.id.SeatNum);
        final Button OKbtn = (Button) dlg.findViewById(R.id.okButton);
        final Button back = (Button) dlg.findViewById(R.id.back);
        AgreeCB = (CheckBox) dlg.findViewById(R.id.AgreeCB);

        Seat.setText(RoomNum);
        ZoneName.setText("세미나 실");
        OKbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                myRef = database.getReference();

                ZoneRe(Zone, RoomNum,dayString,timeString,checkuntilk,smalladapter);


            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg.dismiss();

            }
        });
    }


    public void ZoneRe(final String Zone, final String RoomNum,final String dayString,final String timeString,final int checkuntilk,final MysmallAdapter smalladapter) {

        if (AgreeCB.isChecked() == true) {


            final Query query = myRef.child("Seat").child(Zone).child(RoomNum);
            query.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot datasnapshot) {


                    if (datasnapshot.child(dayString).hasChild(timeString)) {

                        Toast.makeText(context, "이미 예약중입니다.", Toast.LENGTH_SHORT).show();

                    } else {


                        String dayStringSub=dayString.substring(0,5);
                        SeminarVO seminarVO = new SeminarVO(loginId, true, false);
                        String intime = timeString;
                        //ReservationVO reservationVO = new ReservationVO(Zone,RoomNum,loginId,"");
                        for (int a = 0; a < checkuntilk; a++) {
                            myRef.child("Seat").child(Zone).child(RoomNum).child("2020").child(dayStringSub).child(intime).setValue(seminarVO)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Log.e(Zone, "예약 성공");
                                            Toast.makeText(context, "예약 성공", Toast.LENGTH_SHORT).show();
                                            // finish();
                                            createNotificationChannel(RoomNum);
                                            smalladapter.notifyDataSetChanged();
                                            //smalladapter.notifyDataSetChanged();
                                            //untiladapter.notifyDataSetChanged();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.e(Zone, "예약 실패");
                                            Toast.makeText(context, "예약 실패", Toast.LENGTH_SHORT).show();

                                        }
                                    });
                            CT = Integer.parseInt(intime) + 1;
                            intime = Integer.toString(CT);
                        }
                    }
                }










                @Override
                public void onCancelled(@NonNull DatabaseError error) {


                }
            });



        }
        else {
            Toast.makeText(context, "동의 하셔야 좌석 예약이 가능합니다.", Toast.LENGTH_SHORT).show();
        }
        dlg.dismiss();






            //showMsg();









    }
    private void createNotificationChannel(String num) {

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

  /*      Intent notificationIntent = new Intent(this, QuietZone.class);
        notificationIntent.putExtra("notificationId", count); //전달할 값
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK) ;
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent,  PendingIntent.FLAG_UPDATE_CURRENT);
*/
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.logo))
               //BitMap 이미지 요구
                .setContentTitle("Seminar "+num+" 예약완료")
                .setContentText("반드시 지정된 시간에 입실 해 주세요")
                .setDefaults(Notification.FLAG_FOREGROUND_SERVICE)
                // 더 많은 내용이라서 일부만 보여줘야 하는 경우 아래 주석을 제거하면 setContentText에 있는 문자열 대신 아래 문자열을 보여줌
                //.setStyle(new NotificationCompat.BigTextStyle().bigText("더 많은 내용을 보여줘야 하는 경우..."))
                .setPriority(NotificationCompat.PRIORITY_LOW)
                //.setContentIntent(pendingIntent) // 사용자가 노티피케이션을 탭시 ResultActivity로 이동하도록 설정
                .setAutoCancel(true);

        //OREO API 26 이상에서는 채널 필요
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            builder.setSmallIcon(R.drawable.logo); //mipmap 사용시 Oreo 이상에서 시스템 UI 에러남
            CharSequence channelName  = "노티페케이션 채널";
            String description = "오레오 이상을 위한 것임";
            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName , importance);
            channel.setDescription(description);

            // 노티피케이션 채널을 시스템에 등록
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);

        }else builder.setSmallIcon(R.mipmap.ic_main); // Oreo 이하에서 mipmap 사용하지 않으면 Couldn't create icon: StatusBarIcon 에러남

        assert notificationManager != null;
        notificationManager.notify(1234, builder.build()); // 고유숫자로 노티피케이션 동작시킴
    }
}
