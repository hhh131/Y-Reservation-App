package com.example.zone.SeminarRoom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zone.R;
import com.example.zone.seminarImgDialog;

public class SeminarRoomSel extends AppCompatActivity implements View.OnClickListener,View.OnLongClickListener{
    Button Sbutton;
    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;
    int buttons[] = {R.id.seminar1, R.id.seminar2, R.id.seminar3, R.id.seminar4, R.id.seminar5, R.id.seminar6,
            R.id.seminar7, R.id.seminar8, R.id.seminar9};
    String buttonIndex[] = new String[buttons.length];
    private Button[] ButtonArray = new Button[buttons.length];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seminar);
        setTitle("세미나실 선택");
        for (int i = 0; i < 9; i++) {
            ButtonArray[i] = (Button) findViewById(buttons[i]);
            buttonIndex[i] = ButtonArray[i].getText().toString();
            ButtonArray[i].setOnClickListener(this);
            //ButtonArray[i].setOnLongClickListener(this);
        }


    }
    @Override
    public boolean onLongClick(View v) {

        seminarImgDialog reservationDialog = new seminarImgDialog(getApplicationContext());
        //커스텀 다이얼로그를 호출한다.
        reservationDialog.callFunction("Seminar");
        return false;
    }



    @Override
    public void onClick(View v){
        Sbutton = (Button) v;

        String SeminarRoom=Sbutton.getText().toString();
        SeminarRoom=SeminarRoom.substring(0,1);
        Intent intent=new Intent(SeminarRoomSel.this, seminarDay.class);
        intent.putExtra("RoomNum",SeminarRoom);
        startActivity(intent);

        //intent.putExtra("RoomNum",SeminarRoom);



       /* switch (SeminarRoom){
            case "1":

                break;
            case "2":

                break;
            case "3":

                break;
            case "4":

                break;
            case "5":

                break;
            case "6":

                break;
            case "7":

                break;
            case "8":

                break;
            case "9":

                break;


        }*/

    }


}
