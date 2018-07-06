package com.app.study.firstapp;

/**
 * Created by study on 6/12/2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String CUSTOMER_TABLE = "Customers";
    private static final String COL0 = "ID";
    private static final String COL1 = "firstname";
    private static final String COL2 = "surname";
    private static final String COL3 = "DOB";
    private static final String COL4 = "idnumber";
    private static final String COL5 = "gender";
    private static final String COL6 = "email";

    private static final String ADMIN_TABLE = "Admin";
    private static final String COLUMN0 = "ID";
    private static final String COLUMN1 = "firstname";
    private static final String COLUMN2 = "surname";
    private static final String COLUMN3 = "email";
    private static final String COLUMN4 = "password";


    public DatabaseHelper(Context context) {
        super(context, CUSTOMER_TABLE, null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String createCustomerTable = "CREATE TABLE " + CUSTOMER_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL1 +" TEXT NOT NULL, " +
                COL2 + " TEXT NOT NULL, " +
                COL3 + " TEXT NOT NULL, " +
                COL4 + " TEXT NOT NULL, " +
                COL5 + " TEXT NOT NULL, " +
                COL6 + " TEXT NOT NULL)";
        String createAdminTable = "CREATE TABLE " + ADMIN_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN1 +" TEXT NOT NULL, " +
                COLUMN2 + " TEXT NOT NULL, " +
                COLUMN3 + " TEXT NOT NULL, " +
                COLUMN4 + " INTEGER NOT NULL)";

        db.execSQL(createCustomerTable);
        db.execSQL(createAdminTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CUSTOMER_TABLE);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + ADMIN_TABLE);
        onCreate(db);
    }

    public boolean addCustomer(String fname,String sname,String DOB,String id,
                           String gender, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, fname);
        contentValues.put(COL2, sname);
        contentValues.put(COL3, DOB);
        contentValues.put(COL4, id);
        contentValues.put(COL5, gender);
        contentValues.put(COL6, email);

        Log.d(TAG, "addData: Adding customer to " + CUSTOMER_TABLE);

        long result = db.insert(CUSTOMER_TABLE, null, contentValues);


        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addAdmin(String fname,String sname,String email,String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN1, fname);
        contentValues.put(COLUMN2, sname);
        contentValues.put(COLUMN3, email);
        contentValues.put(COLUMN4, password);

        Log.d(TAG, "addData: Adding Admin to " + ADMIN_TABLE);

        long result = db.insert(ADMIN_TABLE, null, contentValues);


        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns all the data from database
     * @return
     */
    public Cursor getData(){

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + CUSTOMER_TABLE;
        Cursor data = db.rawQuery(query, null);
        return data;

    }

    /**
     * Returns only the ID that matches the name passed in
     * @param email
     * @return
     */
    public Cursor getCustomerID(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL0 + " FROM " + CUSTOMER_TABLE +
                " WHERE " + COL6 + " = '" + email + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getCustomerDetails(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + CUSTOMER_TABLE +
                " WHERE " + COL6 + " = '" + email + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getAdminID(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COLUMN0 + " FROM " + ADMIN_TABLE +
                " WHERE " + COLUMN3 + " = '" + email + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Updates the custoemr details field
     * @param newfName
     * @param newSName
     * @param newDate
     * @param id
     * @param newGender
     * @param newEmail
     */
    public void updateCustomerDetails(int pid,String newfName, String newSName,String newDate,
                                      String id, String newGender,String newEmail ){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + CUSTOMER_TABLE + " SET " +
                COL1 + " = '" + newfName + "', " +
                COL2 + " = '" + newSName + "', " +
                COL3 + " = '" + newDate + "', " +
                COL4 + " = '" + id + "', " +
                COL5 + " = '" + newGender + "', " +
                COL6 + " = '" + newEmail + "' " +
                "WHERE " + COL0 + " = '" + pid + "'";

        Log.d(TAG, "updateCustomerDetails: query: " + query);
        Log.d(TAG, "updateCustomerDetails: " );
        db.execSQL(query);
    }

    /**
     * Delete from database
     * @param id
     */
    public void deleteDetails(Cursor id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + CUSTOMER_TABLE + " WHERE "
                + COL0 + " = '" + id + "'" ;
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting customer" + id + " from database.");
        db.execSQL(query);

    }

}


