package com.app.study.firstapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by study on 6/22/2018.
 */

public class CustomerAdapter extends BaseAdapter {
    Context context;
    ArrayList<Customer> customerListArray;
    ImageButton edit;
    ImageButton delete;

    public CustomerAdapter(Context context, ArrayList<Customer> customerListArray) {

        this.context = context;
        this.customerListArray=customerListArray;
    }

    @Override
    public int getCount() {
        return customerListArray.size();
    }

    @Override
    public Object getItem(int position) {
        return customerListArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("adpater", "I am in adapter"); // Doesn't seem to get to this point as I don't see the log message
        View element;
        Customer customerList = customerListArray.get(position);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            element = inflater.inflate(R.layout.row_item, null);

        } else {
            element = convertView;
        }
        // Code of XML file with these widgets defined: https://gist.github.com/bharatkrishna/5175158
        TextView fName = convertView.findViewById(R.id.fname);
        fName.setText(customerList.getFName());

        TextView sName = convertView.findViewById(R.id.sname);
        sName.setText(customerList.getSName());

        TextView email = convertView.findViewById(R.id.email);
        email.setText(customerList.getCusEmail());



        edit = convertView.findViewById(R.id.editButton);
        edit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        delete = convertView.findViewById(R.id.cancelButton);
        delete.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return convertView;
    }

}
