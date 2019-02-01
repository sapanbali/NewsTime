package com.example.sapan.newstime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Mainstart extends AppCompatActivity {
    Button signin;
    Button signup;

    public void signin(View view)
    {

        Intent intent=new Intent(Mainstart.this,Signin.class);
        startActivity(intent);
    }

    public void signup(View view)
    {

        Intent intent=new Intent(Mainstart.this,signup.class);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainstart);


        signin=(Button) findViewById(R.id.signin);
        signup=(Button) findViewById(R.id.signup);

    }
}
