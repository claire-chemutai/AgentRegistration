package com.app.study.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private Button LogIn;
    private Button Register;
    private TextView Info;
    private int counter=5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       Name = (EditText)findViewById(R.id.name);
       Password = (EditText)findViewById(R.id.password);
       LogIn= (Button)findViewById(R.id.loginButton);
       Register= (Button)findViewById(R.id.registerButton);
       Info= (TextView)findViewById(R.id.info);

       Info.setText("No. of attempts remaining is: 5");

       LogIn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               validate(Name.getText().toString(), Password.getText().toString());
           }
       });

        Register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                register();
            }
        });


    }
    private void validate(String username, String password){
        if (username.equals("Admin") && password.equals("1234")){
            Intent intent= new Intent(MainActivity.this, AdminPage.class);
            startActivity(intent);
        }
        else{
            counter--;
            Info.setText("Incorrect details, No. of attempts remaining: "+ String.valueOf(counter));
            if (counter==0){
                LogIn.setEnabled(false);

            }
    }
    }
    private void register(){
        Intent intent= new Intent(MainActivity.this, RegisterAdmin.class);
        startActivity(intent);

    }

}
