package com.example.sapan.newstime;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {

    Button button;
    TextView userlogin,signin;
    EditText e1,e2;
    ProgressDialog progressdialog;
    private FirebaseAuth firebase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        e1=(EditText) findViewById(R.id.email);
        e2=(EditText) findViewById(R.id.password);
       //userlogin=(TextView) findViewById(R.id.userlogin);
        button=(Button) findViewById(R.id.logup);

        signin=(TextView) findViewById(R.id.signin);

        progressdialog=new ProgressDialog(this);
        firebase=FirebaseAuth.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registeruser();
                progressdialog.setMessage("Registering");
                progressdialog.show();
                Intent intent =new Intent(signup.this,Signin.class);
                startActivity(intent);


            }
        });

    }

    private void registeruser(){
        String email =e1.getText().toString().trim();
        String password=e2.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"email not enterred",Toast.LENGTH_LONG).show();

            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"password not enterred",Toast.LENGTH_LONG).show();

            return;
        }

        firebase.createUserWithEmailAndPassword(email,password).addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(signup.this, "registerred",Toast.LENGTH_SHORT).show();
                    progressdialog.dismiss();

                }else
                    Toast.makeText(signup.this,"not registerred",Toast.LENGTH_SHORT);
            }
        });

    }



}

