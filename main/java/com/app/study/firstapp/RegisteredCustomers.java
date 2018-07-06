package com.app.study.firstapp;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class RegisteredCustomers extends AppCompatActivity {
    ListView listView;
    private DatabaseHelper db;
    private Cursor customers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_customers);
        listView = (ListView) findViewById(R.id.listview);

        showList();
    }

    private void showList() {
        ArrayList<Customer> customerList = new ArrayList<Customer>();
        db = new DatabaseHelper(this);
        customers = db.getData();

        // This successfully dumps the db contents to log output. Querying from DB seems to be working fine.
        Log.d("db", DatabaseUtils.dumpCursorToString(customers));



//        while (customers.moveToNext()){
//            customerList.add(customerData);
//            itemID = customers.getInt(0);


            if (customers != null && customers.getCount() != 0) {
                Customer customerData = new Customer();
                do{


                customerData.setFName(customers.getString(customers.getColumnIndex("fname")));

                customerData.setSName(customers.getString(customers.getColumnIndex("sname")));

                customerData.setCusEmail(customers.getString(customers.getColumnIndex("email")));

            }while (customerList.add(customerData));
        }
        CustomerAdapter customerAdapter = new CustomerAdapter(RegisteredCustomers.this, customerList);
        listView.setAdapter(customerAdapter); //listView is defined in onCreate() method
        customers.close();
    }
}

