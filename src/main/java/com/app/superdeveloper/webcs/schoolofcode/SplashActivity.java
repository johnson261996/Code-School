package com.app.superdeveloper.webcs.schoolofcode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by johnson on 02-06-2017.
 */

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread timer = new Thread(){
            public void run(){
                try {
                    sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    Intent openmain = new Intent("com.app.superdeveloper.webcs.schoolofcode.MAINACTIVITY");
                    startActivity(openmain);
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
