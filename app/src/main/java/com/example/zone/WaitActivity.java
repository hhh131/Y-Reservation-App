package com.example.zone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class WaitActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_wait);

        Handler hd = new Handler();
        hd.postDelayed(new splashandler(), 3000); // 로딩화면 2초동안 동작
    }

    private class splashandler implements Runnable {
        public void run() {
            startActivity(new Intent(getApplication(), SeatActivity.class)); // 로딩이 끝난 후, Main으로 이동
            WaitActivity.this.finish();
        }
    }

    @Override
    public void onBackPressed() {
        // 뒤로가기 방지
    }
}
//비콘으로 근처에서만 할 수 있게
