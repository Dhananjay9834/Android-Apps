package com.example.mymusicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;

import android.widget.TextView;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        title=findViewById(R.id.tv_splash_title);





        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(i);
            }
        },3000);
    }

}