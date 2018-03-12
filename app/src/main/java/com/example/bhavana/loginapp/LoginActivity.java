package com.example.bhavana.loginapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
private EditText ed1,ed2;
private Button b1,b2;
//private ProgressBar progressBar;
private ProgressDialog progressDialog;
 String userid,password;

 private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null)
        {
            finish();
            // start the mapsactivity
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
        }

        //progressBar=new ProgressBar(this);
        progressDialog=new ProgressDialog(this);
        b1=(Button)findViewById(R.id.loginbtn);
        b2=(Button)findViewById(R.id.register);
        ed1=(EditText)findViewById(R.id.userid);

        ed2=(EditText)findViewById(R.id.password);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view==b1)
        {
            userLogin();
        }
        if(view==b2)
        {
            Registeration();
        }
    }

    //register method

    private void Registeration() {
        userid=ed1.getText().toString().trim();
        password=ed2.getText().toString().trim();
        if(TextUtils.isEmpty(userid))
        {
            // empty user login id
            Toast.makeText(this, "please fill the UserLogin Id", Toast.LENGTH_SHORT).show();
            //stopping the function

            return;
        }

        if(TextUtils.isEmpty(password))
        {
            //password filled empty
            Toast.makeText(this, "please fill the password", Toast.LENGTH_SHORT).show();
            //stopping the funtion
            return ;
        }
        //if the vatlidation arte ok
        // first show the progress bar
        progressDialog.setMessage(" Registering.....");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(userid,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()){
                    // user is succesfully registered
                    // start the porfile activity here

                    Toast.makeText(LoginActivity.this,"Registered successfully",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(LoginActivity.this," unsuccessful",Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    //Login method

    private void userLogin() {
        userid=ed1.getText().toString().trim();
        password=ed2.getText().toString().trim();

        if(TextUtils.isEmpty(userid))
        {
            // empty user login id
            Toast.makeText(this, "please fill the UserLogin Id", Toast.LENGTH_SHORT).show();
            //stopping the function

            return;
        }

        if(TextUtils.isEmpty(password))
        {
            //password filled empty
            Toast.makeText(this, "please fill the password", Toast.LENGTH_SHORT).show();
             //stopping the funtion
             return ;
        }

        //if the vatlidation arte ok
        // first show the progress bar
           progressDialog.setMessage(" logging in.....");
          progressDialog.show();

          firebaseAuth.signInWithEmailAndPassword(userid,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
              @Override
              public void onComplete(@NonNull Task<AuthResult> task) {
                  progressDialog.dismiss();
                  if(task.isSuccessful())
                  {
                      finish();
                      // start the mapsactivity
                      startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                  }
              }
          });

    }
}
