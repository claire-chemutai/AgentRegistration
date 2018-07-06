package com.app.study.firstapp;

import android.database.Cursor;
import android.widget.Toast;

/**
 * Created by study on 6/18/2018.
 */

public class Customer {
    DatabaseHelper mDatabaseHelper;
    String FName, SName,DOB,cusEmail,ID,cusGender,Image;

    public Customer() {
    }
    public Customer(String FName, String SName,String cusEmail) {
        this.FName = FName;
        this.SName = SName;
        this.cusEmail = cusEmail;

    }
    public Customer(String FName, String SName,
                    String DOB, String ID, String cusGender, String cusEmail) {
        this.mDatabaseHelper = mDatabaseHelper;
        this.FName = FName;
        this.SName = SName;
        this.DOB = DOB;
        this.cusEmail = cusEmail;
        this.cusGender = cusGender;
        this.ID = ID;
//        this.Image = image;
    }

    public DatabaseHelper getmDatabaseHelper() {
        return mDatabaseHelper;
    }


    public void setmDatabaseHelper(DatabaseHelper mDatabaseHelper) {
        this.mDatabaseHelper = mDatabaseHelper;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getSName() {
        return SName;
    }

    public void setSName(String SName) {
        this.SName = SName;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getCusEmail() {
        return cusEmail;
    }

    public void setCusEmail(String cusEmail) {
        this.cusEmail = cusEmail;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        this.Image = image;
    }
    public String getCusGender() {
        return cusGender;
    }

    public void setCusGender(String cusGender) {
        this.cusGender = cusGender;
    }

    public void  remove(Cursor i) {
        mDatabaseHelper.deleteDetails(i);


    }




}
