package com.example.zone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_wait);

        Handler hd = new Handler();
        hd.postDelayed(new splashandler(), 1500); // 로딩화면 1.5초동안 동작
    }

    private class splashandler implements Runnable {
        public void run() {
            startActivity(new Intent(getApplication(), MainActivity.class)); // 로딩이 끝난 후, Main으로 이동
            SplashActivity.this.finish();
        }
    }

    @Override
    public void onBackPressed() {
        // 뒤로가기 방지
    }
}