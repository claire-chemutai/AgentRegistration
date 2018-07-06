package com.app.study.firstapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.graphics.BitmapFactory;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;;

import java.util.ArrayList;

public class CustomersRegistered extends AppCompatActivity {

    private static final String TAG ="CustomersRegistered" ;
    CustomListAdapter customListAdapter=null;
    DatabaseHelper mDatabaseHelper;
    ArrayList<Customer> customerList;
    ListView listView;
    private TextView FName, SName, cusEmail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDatabaseHelper = new DatabaseHelper(this);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_customers_registered);

        listView =  findViewById(R.id.listview);
        FName =  findViewById(R.id.fname);
        SName =  findViewById(R.id.sname);
        cusEmail =  findViewById(R.id.email);
        Cursor cursor = mDatabaseHelper.getData();

        customerList= new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                Customer customerData = new Customer();
                customerList.add(customerData);
                customerData.setFName(cursor.getString(1));
                customerData.setSName(cursor.getString(2));
                customerData.setCusEmail(cursor.getString(6));

            } while (cursor.moveToNext() );
            customListAdapter = new CustomListAdapter(CustomersRegistered.this,R.layout.row_item,customerList);
            listView.setAdapter(customListAdapter);

        }


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String name = parent.getItemAtPosition(position).toString();
                Log.d(TAG, "onItemClick: You Clicked on " + name);

                Cursor data = mDatabaseHelper.getData();
                int itemID = 0;
                while (data.moveToNext()) {
                    itemID = data.getInt(0);
                }
                if (itemID > 0) {
                    Log.d(TAG, "onItemClick: The ID is: " + itemID);
                    Intent editScreenIntent = new Intent(CustomersRegistered.this, EditDetails.class);
                    editScreenIntent.putExtra("pid", data.getInt(0));
                    editScreenIntent.putExtra("Firstname", data.getString(1));
                    editScreenIntent.putExtra("Surname", data.getString(2));
                    editScreenIntent.putExtra("DOB", data.getString(3));
                    editScreenIntent.putExtra("ID", data.getString(4));
                    editScreenIntent.putExtra("Gender", data.getString(5));
                    editScreenIntent.putExtra("Email", data.getString(6));
                    Log.i("CR EDIT", "ID FOUND: " );
                    startActivity(editScreenIntent);
                } else {

                    Log.i("CR EDIT", "NO ID ASSOCIATED WITH THAT NAME: " );
                }
            }

    });

}


    private void editDetails(){
        Intent intent= new Intent(CustomersRegistered.this, EditDetails.class);
        startActivity(intent);

    }
    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }


}

