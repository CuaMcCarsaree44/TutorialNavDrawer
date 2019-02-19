package com.example.navdrawer.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.navdrawer.MainActivity;
import com.example.navdrawer.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        //TODO 3.1 Create a Handler so we will go to Splashscreen for a period of times
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() { //We will fly to MainActivity in 3 seconds
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
            }
        }, 3000);
    }
}
