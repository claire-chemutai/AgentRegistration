package com.app.study.firstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegisterAdmin extends AppCompatActivity {
    DatabaseHelper mDatabaseHelper;
    private EditText FirstName,SecondName,userEmail,Password,ConfirmPassword;
    String fname, sname, email, emailPattern, pass,cpass;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_admin);
        mDatabaseHelper = new DatabaseHelper(this);

        FirstName = (EditText)findViewById(R.id.FNameAdmin);
        SecondName = (EditText)findViewById(R.id.SNameAdmin);
        userEmail = (EditText)findViewById(R.id.emailAdmin);
        emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        Password = (EditText)findViewById(R.id.passwordAdmin);
        ConfirmPassword = (EditText)findViewById(R.id.conPasswordAdmin);

        submit= (Button)findViewById(R.id.submitButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fname = FirstName.getText().toString();
                sname = SecondName.getText().toString();
                email = userEmail.getText().toString().trim();
                pass = Password.getText().toString();
                cpass = ConfirmPassword.getText().toString();
                if (FirstName.length() != 0 && SecondName.length() != 0 && userEmail.length() != 0
                        && pass.length() != 0 && cpass.length() != 0 && pass.equals(cpass)) {
                    AddAdmin(fname,sname, email,pass);
                    FirstName.setText("");
                    SecondName.setText("");
                    userEmail.setText("");
                    Password.setText("");
                    ConfirmPassword.setText("");
                } else if (!pass.equals(cpass)){
                    toastMessage("Password not the same!");

                }
                else {
                    toastMessage("You must put something in the text field!");
                }

            }
        });


    }


    public void AddAdmin(String fname,String sname,String email,String password) {
        boolean insertData = mDatabaseHelper.addAdmin(fname,sname,email,password);

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }
    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
