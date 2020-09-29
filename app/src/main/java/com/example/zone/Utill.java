package com.example.zone;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

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

import static com.example.zone.LoginActivity.loginId;

public class Utill {






    public String getDate()
    {
        Date Time = Calendar.getInstance().getTime();
       return new SimpleDateFormat("yyyy년 MM월 dd일", Locale.getDefault()).format(Time);
    }




}
