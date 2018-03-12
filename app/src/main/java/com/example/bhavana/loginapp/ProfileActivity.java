package com.example.bhavana.loginapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import junit.framework.TestResult;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{
private Button btn,btnmap;
private TextView textuser;

FirebaseAuth firebaseAuth;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        btn=(Button)findViewById(R.id.btnlogout);
        btnmap=(Button)findViewById(R.id.btnmap);
        textuser=(TextView)findViewById(R.id.textuser);

        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null)
            {
                finish();
                startActivity(new Intent(this,LoginActivity.class));
            }

        FirebaseUser user=firebaseAuth.getCurrentUser();
        textuser.setText("Welcome "+ user.getEmail());
        btn.setOnClickListener(this);
        btnmap.setOnClickListener(this);
    }

    // click button method
    @Override
    public void onClick(View view) {
        if(view==btn)
        {
            firebaseAuth.signOut();
            finish();
            //return to login Activity
            startActivity(new Intent(this,LoginActivity.class));
        }
        if (view==btnmap)
        {
            finish();
            startActivity(new Intent(this,MyLocation.class));
        }
    }
}
