package com.example.sapan.newstime;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signin extends AppCompatActivity {

    EditText Email, Password;
    Button login;
    TextView t1,t2;
    FirebaseAuth firebaseauth;
    ProgressDialog progressdialog;
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";

    public void login(View view) {
        progressdialog = new ProgressDialog(this);
        progressdialog.setMessage("Please wait");





        (firebaseauth.signInWithEmailAndPassword(Email.getText().toString(), Password.getText().toString()))
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        progressdialog.dismiss();
                        if (task.isSuccessful()) {
                            String n = Email.getText().toString();
                            String e = Password.getText().toString();

                            SharedPreferences.Editor editor= sharedpreferences.edit();


                            editor.putString("email" ,n);
                            editor.putString("password" ,e);
                            editor.commit();

                            Toast.makeText(Signin.this, "Sign in succesful", Toast.LENGTH_SHORT).show();

                            Intent home = new Intent(Signin.this, MainActivity.class);

                            startActivity(home);

                        } else
                            Toast.makeText(Signin.this, "Sign in not succesful", Toast.LENGTH_SHORT).show();

                    }
                });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        Email=(EditText) findViewById(R.id.email);
        Password=(EditText) findViewById(R.id.password);
        login=(Button) findViewById(R.id.login);
        t1=(TextView) findViewById(R.id.tv1);
        t2=(TextView) findViewById(R.id.tv2);


        firebaseauth=FirebaseAuth.getInstance();


        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        if (sharedpreferences.contains("email")) {
            Email.setText(sharedpreferences.getString("email", ""));
        }
        if (sharedpreferences.contains("password")) {
            Password.setText(sharedpreferences.getString("password", ""));

        }


    }
}
