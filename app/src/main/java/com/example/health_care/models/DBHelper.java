package com.example.health_care.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static DBHelper dbHelper = null;

    public DBHelper(Context context) {
        super(context, "sth1.db", null, 5);
    }

    public static DBHelper getDbHelper() {
        return dbHelper;
    }

    public static void setDbHelper(DBHelper dbHelper) {
        DBHelper.dbHelper = dbHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table admin(username TEXT primary key, name TEXT, password TEXT, email TEXT)");
        DB.execSQL("create Table customer(username TEXT primary key, name TEXT, password TEXT, email TEXT)");
        DB.execSQL("create Table drugs(id TEXT primary key, name TEXT, price REAL, description TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists drugs");
        DB.execSQL("drop Table if exists users");
    }


    public Boolean updateuserdata(String name, String contact, String dob) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("contact", contact);
        contentValues.put("dob", dob);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.update("Userdetails", contentValues, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Boolean deletedata(String name) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Userdetails", "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getUserdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from users", null);
        return cursor;
    }

    public Cursor getDrugdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from drugs", null);
        return cursor;
    }

    public void insertDrugData(String id, String name, double price, String description) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("name", name);
        contentValues.put("price", price);
        contentValues.put("description", description);
        long result = DB.insertOrThrow("drugs", null, contentValues);
        DB.close();
    }

    public void insertAdminData(String username, String password, String name, String email) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("name", name);
        contentValues.put("email", email);
        long result = DB.insertOrThrow("admin", null, contentValues);
        DB.close();
    }

    public void insertCustomerData(String username, String password, String name, String email) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("name", name);
        contentValues.put("email", email);
        long result = DB.insertOrThrow("customer", null, contentValues);
        DB.close();
    }

    public Cursor getCustomerdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from customer", null);
        return cursor;
    }

    public Cursor getAdmindata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from admin", null);
        return cursor;
    }
}