package com.app.study.firstapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;

public class EditDetails extends AppCompatActivity {


    private Button btnSave,btnCancel;
    private EditText editable_fname,editable_sname,editable_DOB,editable_ID,
            editable_gender,editable_email;


    DatabaseHelper mDatabaseHelper;

    private String selectedfName, selectedsName,selectedDOB,selectedgender,selectedEmail,selectedID;
    private Integer pid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_details);
        btnSave =  findViewById(R.id.saveButton);
        btnCancel =  findViewById(R.id.cancelButton);
        editable_fname =  findViewById(R.id.fname);
        editable_sname =  findViewById(R.id.sname);
        editable_DOB =  findViewById(R.id.DOB);
        editable_ID =  findViewById(R.id.ID);
        editable_gender = findViewById(R.id.gender);
        editable_email =  findViewById(R.id.email);

        mDatabaseHelper = new DatabaseHelper(this);

        Intent receivedIntent = getIntent();
        pid = receivedIntent.getIntExtra("pid", -1);
        Log.i("receintent", "pid: " + pid);

        //now get the name we passed as an extra
        selectedfName = receivedIntent.getStringExtra("Firstname");
        editable_fname.setText(selectedfName);

        selectedsName = receivedIntent.getStringExtra("Surname");
        editable_sname.setText(selectedsName);

        selectedDOB = receivedIntent.getStringExtra("DOB");
        editable_DOB.setText(selectedDOB);

        selectedID = receivedIntent.getStringExtra("ID");
        editable_ID.setText(selectedID);

        selectedgender = receivedIntent.getStringExtra("Gender");
        editable_gender.setText(selectedgender);

        selectedEmail = receivedIntent.getStringExtra("Email");
        editable_email.setText(selectedEmail);


        Log.i("CLA", "gender: " + selectedsName);
        Log.i("CLA", "email: " + selectedEmail);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseHelper.updateCustomerDetails(pid,selectedfName,selectedsName,
                        selectedDOB,selectedID,selectedgender,selectedEmail);
                toastMessage("Data Successfully updated!");
                Intent intent= new Intent( EditDetails.this,CustomersRegistered.class);
                startActivity(intent);

            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                editable_fname.setText(selectedfName);
                editable_sname.setText(selectedsName);
                editable_DOB.setText(selectedDOB);
                editable_ID.setText(selectedID);
                editable_gender.setText(selectedgender);
                editable_email.setText(selectedEmail);

            }

        });
//        profilePicture.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View view) {
//                startActivityForResult(new Intent(Intent.ACTION_PICK,
//                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
//            }
//        });




    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//
//        //Detects request codes
//        if(requestCode==GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
//            Uri selectedImage = data.getData();
//            Bitmap bitmap = getImage(selectedImage);
//            profilePicture.setImageBitmap(bitmap);
//
////            try {
////                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
////            } catch (FileNotFoundException e) {
////                // TODO Auto-generated catch block
////                e.printStackTrace();
////            } catch (IOException e) {
////                // TODO Auto-generated catch block
////                e.printStackTrace();
////            }
//        }
//    }
//
//    private String getFilePath(Uri uri) {
//        String[] projection = {MediaStore.Images.Media.DATA};
//        Cursor cursor = managedQuery(uri, projection, null, null, null);
//
//        int column_index = cursor
//                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//        cursor.moveToFirst();
//        String filePath = cursor.getString(column_index);
//        profilePic.setText(filePath);
//        // cursor.close();
//        // Convert file path into bitmap image using below line.
////        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
//
//        return filePath;
//    }
//
//    private Bitmap getImage(Uri uri) {
//        String[] projection = {MediaStore.Images.Media.DATA};
//        Cursor cursor = managedQuery(uri, projection, null, null, null);
//
//        int column_index = cursor
//                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//        cursor.moveToFirst();
//        String filePath = cursor.getString(column_index);
//        // cursor.close();
//        // Convert file path into bitmap image using below line.
//        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
//
//        return bitmap;
//    }

    private void toastMessage(String s) {

        Toast.makeText(this,s, Toast.LENGTH_SHORT).show();
    }
}
