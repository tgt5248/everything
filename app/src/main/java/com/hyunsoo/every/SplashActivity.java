package com.hyunsoo.every;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

//처음 2초동안 스플래시액티비티 실행 후 메인액티비티로
public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try{
            Thread.sleep(2000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
