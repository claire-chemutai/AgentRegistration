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
    DatabaseHelper mDatabaseHelper;
    ArrayList<Customer> customerList;
    ListView listView;
    private Cursor customers;
    private static ListAdapter adapter;
    private TextView FName, SName, cusEmail;
    private ImageView profilePic;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDatabaseHelper = new DatabaseHelper(this);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_customers_registered);
        listView =  findViewById(R.id.listview);
        FName =  findViewById(R.id.fname);
        SName =  findViewById(R.id.sname);
        cusEmail =  findViewById(R.id.email);
//        profilePic =  findViewById(R.id.profilePic);
        customerList = getCustomerData();
        getCustomerData();

        adapter = new CustomListAdapter(customerList, getApplicationContext());
        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String name = parent.getItemAtPosition(position).toString();
                Log.d(TAG, "onItemClick: You Clicked on " + name);

                Cursor data = mDatabaseHelper.getData(); //get the id associated with that name
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
//                    editScreenIntent.putExtra("Image", data.getString(7));
                    Log.i("CR EDIT", "ID FOUND: " );
                    startActivity(editScreenIntent);
                } else {

                    Log.i("CR EDIT", "NO ID ASSOCIATED WITH THAT NAME: " );
                }
            }

    });

}

    public ArrayList<Customer> getCustomerData() {
        Cursor cursor = mDatabaseHelper.getData();
        customerList= new ArrayList<>();

        customers = mDatabaseHelper.getData();

        // looping through all rows and adding to list

            if (cursor.moveToFirst()) {
                do {
                    Customer customerData = new Customer();
                    customerList.add(customerData);
                    customerData.setFName(cursor.getString(1));
//                    FName.setText(cursor.getString(1));
                    customerData.setSName(cursor.getString(2));
//                    SName.setText(cursor.getString(2));
                    customerData.setCusEmail(cursor.getString(6));
//                    cusEmail.setText(cursor.getString(6));
//                    customerData.setImage(cursor.getString(7));
//                    profilePic.setImageBitmap(BitmapFactory.decodeFile(cursor.getString(7)));

                } while (cursor.moveToNext() );
                CustomListAdapter customListAdapter = new CustomListAdapter( customerList,CustomersRegistered.this);
                listView.setAdapter(customListAdapter); //listView is defined in onCreate() method
                customers.close();
            }


        return customerList;


    }
    private void showList() {
        ArrayList<Customer> customerList = new ArrayList<Customer>();
        mDatabaseHelper = new DatabaseHelper(this);
        customers = mDatabaseHelper.getData();

        // This successfully dumps the db contents to log output. Querying from DB seems to be working fine.
        Log.d("db", DatabaseUtils.dumpCursorToString(customers));
        int itemID = -1;
        Customer customerData = new Customer();


//        while (customers.moveToNext()){
//            customerList.add(customerData);
//            itemID = customers.getInt(0);


        if (customers != null && customers.getCount() != 0) {
            do {

//                    Customer customerData = new Customer();
                customerList.add(customerData);
                customerData.setFName(customers.getString(customers.getColumnIndex("fname")));

                customerData.setSName(customers.getString(customers.getColumnIndex("sname")));

                customerData.setCusEmail(customers.getString(customers.getColumnIndex("email")));


            } while (customers.moveToNext());
        }
        CustomListAdapter customListAdapter = new CustomListAdapter(customerList,CustomersRegistered.this);
        listView.setAdapter(customListAdapter); //listView is defined in onCreate() method
        customers.close();

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

