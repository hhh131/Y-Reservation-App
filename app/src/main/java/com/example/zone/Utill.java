package com.example.zone;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Utill {


//노티피케이션


    public String getDate()
    {
        Date Time = Calendar.getInstance().getTime();
       return new SimpleDateFormat("yyyy년 MM월 dd일", Locale.getDefault()).format(Time);
    }




}
