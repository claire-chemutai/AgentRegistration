package com.app.study.firstapp;


import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class AgentRegistration extends AppCompatActivity {
    private static final String TAG = "AgentRegistration";
    DatabaseHelper mDatabaseHelper;
    private EditText FirstName, SecondName,dateOfBirth,emailAgent,identification;
    String genderType,emailPattern,fname,sname,DOB,email,id,image;
    private Button submit,photoButton;
//    private static final int CAMERA_REQUEST = 1888;
//    private static final int MY_CAMERA_PERMISSION_CODE = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_registration);

        mDatabaseHelper = new DatabaseHelper(this);

        FirstName = findViewById(R.id.FNameAgent);
        SecondName = findViewById(R.id.SNameAgent);
        emailAgent = findViewById(R.id.emailAgent);
        dateOfBirth = findViewById(R.id.DOB);
        emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        identification = findViewById(R.id.IDNumber);


        final Spinner genderSpinner =  findViewById(R.id.SpinnerGenderType);


//        photoButton = findViewById(R.id.addPictureBtn);
//        photoButton.setOnClickListener(new View.OnClickListener() {
//
//            @TargetApi(Build.VERSION_CODES.M)
//            @Override
//            public void onClick(View v) {
//                if (checkSelfPermission(Manifest.permission.CAMERA)
//                        != PackageManager.PERMISSION_GRANTED) {
//                    requestPermissions(new String[]{Manifest.permission.CAMERA},
//                            MY_CAMERA_PERMISSION_CODE);
//                } else {
//                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
//                }
//            }
//        });

        submit= findViewById(R.id.submitButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fname = FirstName.getText().toString();
                sname = SecondName.getText().toString();
                email = emailAgent.getText().toString().trim();
                DOB = dateOfBirth.getText().toString();
                id = identification.getText().toString();
                genderType = genderSpinner.getSelectedItem().toString();
                if (FirstName.length() != 0 && SecondName.length() != 0 && email.length() != 0
                        && DOB.length() != 0 && id.length()!=0) {


                    Log.d(TAG, "DATE OF BIRTH: " + DOB);
                    AddAgent(fname,sname, DOB, id, genderType,email);
                    FirstName.setText("");
                    SecondName.setText("");
                    emailAgent.setText("");
                    dateOfBirth.setText("");
                    identification.setText("");


                }
                else {
                    toastMessage("You must put something in the text field!");
                }

            }
        });


    }

    public void AddAgent(String fname,String sname,String date,String id,
                            String gender, String email) {
        boolean insertData = mDatabaseHelper.addCustomer(fname,sname, date,id,
                gender, email);

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
//                Intent cameraIntent = new
//                        Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(cameraIntent, CAMERA_REQUEST);
//            } else {
//                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
//            }
//
//        }
//    }
//
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
//            Bitmap photo = (Bitmap) data.getExtras().get("data");
////            image.setImage(data.get("data"));
////            imageView.setImageBitmap(photo);
//
//
//
//        }
//    }


    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

}
