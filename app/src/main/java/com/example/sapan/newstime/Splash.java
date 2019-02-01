package com.example.sapan.newstime;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        handler=new Handler();
        handler.postDelayed(new Runnable()  {
            public void run() {
                startActivity(new Intent(Splash.this, Mainstart.class));
                finish();
            }
        },2000);
    }
}
