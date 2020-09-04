package com.example.zone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MyInfoActivity extends AppCompatActivity {
TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        setTitle("상단바");
        tv=(TextView)findViewById(R.id.tv);
        tv.setText(LoginActivity.loginId);


    }
}