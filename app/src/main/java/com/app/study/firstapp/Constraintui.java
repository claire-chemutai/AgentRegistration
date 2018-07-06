package com.app.study.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Constraintui extends AppCompatActivity {
    private TextView register, contact, time, location;
    private ImageButton profilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraintui);
//        register=findViewById(R.id.register);
//        contact=findViewById(R.id.contact);
//        time=findViewById(R.id.time);
//        location=findViewById(R.id.location);
//        profilePic=findViewById(R.id.profileimage);
//        profilePic.setOnClickListener(new View.OnClickListener(){

//            @Override
//            public void onClick(View view) {
//                login();
//            }
//        });
//    }
//    private void login(){
//        Intent intent= new Intent(Constraintui.this, MainActivity.class);
//        startActivity(intent);
//
    }
}
