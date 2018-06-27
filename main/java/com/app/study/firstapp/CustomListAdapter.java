package com.app.study.firstapp;

/**
 * Created by study on 6/18/2018.
 */
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class CustomListAdapter extends ArrayAdapter<Customer>  {
    private static final String TAG ="CustomListAdapters" ;
    ArrayList<Customer> customerList;
    Context context;
    DatabaseHelper mDatabaseHelper;
    Customer customer;



    // View lookup cache
    private static class ViewHolder {
        TextView fName;
        TextView sName;
        TextView email;
        ImageButton edit;
        ImageButton delete;
    }

    public CustomListAdapter(ArrayList<Customer> customerList, Context context) {
        super(context, R.layout.row_item, customerList);
        this.customerList = customerList;
        this.context = context;
        mDatabaseHelper = new DatabaseHelper(context);

    }


    private int lastPosition = -1;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Customer customer = getItem(position);
//        final long id=getItemId(position)+1;

        Log.i("CLA", "Customer position: " + customer);
        ViewHolder holder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            holder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            holder.fName = convertView.findViewById(R.id.fname);
            holder.sName = convertView.findViewById(R.id.sname);
            holder.email = convertView.findViewById(R.id.email);
            holder.edit = convertView.findViewById(R.id.editButton);
            holder.delete = convertView.findViewById(R.id.cancelButton);



            result = convertView;

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }



        Animation animation = AnimationUtils.loadAnimation(context, (position > lastPosition) ?
                R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        holder.fName.setText(customer.getFName());
        holder.sName.setText(customer.getSName());
        holder.email.setText(customer.getCusEmail());
        holder.edit.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                Log.i("CLA", "Customer data: " + mDatabaseHelper.getData());
                    Log.i("CLA", "Customer data: " +mDatabaseHelper.getCustomerDetails(customer.getCusEmail()));
                Cursor data = mDatabaseHelper.getCustomerDetails(customer.getCusEmail()); //get the id associated with that name
                int itemID = -1;
                String itemfname="";
                    while (data.moveToNext()) {
                        itemID = data.getInt(0);


                        if (itemID > -1) {
                            Log.d(TAG, "onItemClick: The ID is: " + itemID);
                            Log.d(TAG, "onItemClick: The ID is: " + itemfname);
                            Intent editScreenIntent = new Intent(context.getApplicationContext(), EditDetails.class);
                            editScreenIntent.putExtra("Firstname", data.getString(1));
                            editScreenIntent.putExtra("Surname", data.getString(2));
                            editScreenIntent.putExtra("DOB", data.getString(3));
                            editScreenIntent.putExtra("ID", data.getString(4));
                            editScreenIntent.putExtra("Gender", data.getString(5));
                            editScreenIntent.putExtra("Email", data.getString(6));
                            Log.i("CLA", "gender: " + data.getString(5));
                            Log.i("CLA", "email: " + data.getString(6));
                            Log.i("CR EDIT", "ID FOUND: ");
                            editScreenIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                            view.getContext().startActivity(editScreenIntent);
                        } else {

                            Log.i("CR EDIT", "NO ID ASSOCIATED WITH THAT NAME: ");
                        }
                    }
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor customerID= mDatabaseHelper.getCustomerID(customer.getCusEmail());
                mDatabaseHelper.deleteDetails(customerID);

                Log.i("CLA", "DELETE DETAILS FOR: " + customerID);
                notifyDataSetChanged();


            }


        });
        // Return the completed view to render on screen
        return convertView;
    }



    @Override
    public int getCount() {
        return customerList.size();
    }

    @Override
    public Customer getItem(int i) {
        return customerList.get(i) ;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}


//    @Override
//    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
//
//    }
//
//    @Override
//    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
//
//    }
//

//
//    @Override
//    public boolean hasStableIds() {
//        return false;
//    }
//
//
//
//    @Override
//    public int getItemViewType(int i) {
//        return i;
//    }
//
//    @Override
//    public int getViewTypeCount() {
//        return 0;
//    }
//

//
//    @Override
//    public boolean areAllItemsEnabled() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled(int i) {
//        return false;
//    }


