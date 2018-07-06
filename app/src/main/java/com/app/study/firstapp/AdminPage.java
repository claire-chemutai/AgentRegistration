package com.app.study.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class AdminPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        ImageButton addButton = (ImageButton) findViewById(R.id.imageButton5);
        ImageButton viewButton = (ImageButton) findViewById(R.id.imageButton6);
        ImageButton myButton1 = (ImageButton) findViewById(R.id.timebtn);
        ImageButton myButton2 = (ImageButton) findViewById(R.id.contbtn);

        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                addList();
            }
        });
        viewButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                viewList();
            }
        });


    }

    private void addList() {
        Intent intent= new Intent(AdminPage.this, AgentRegistration.class);
        startActivity(intent);
    }
    private void viewList() {
        Intent intent= new Intent(AdminPage.this, CustomersRegistered.class);
        startActivity(intent);
    }
}
