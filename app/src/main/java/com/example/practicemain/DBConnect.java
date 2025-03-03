package com.example.practicemain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBConnect extends SQLiteOpenHelper {

    public DBConnect(Context context) {
        super(context, "db_user", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table user (id INTEGER primary key, name TEXT, email TEXT," + "height FLOAT, age int )";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public void insertUser(UserData userData) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", userData.getName());
        contentValues.put("email", userData.getEmail());
        contentValues.put("height", userData.getHeight());
        contentValues.put("age", userData.getAge());

        long check = db.insert("user", null, contentValues);
        if (check == -1) {
            Log.e("db_insert", "Error inserting data");

        } else {
            Log.e("db_insert", "Success inserting data");
        }

//        db.close();
    }

    public List<UserData> retriveUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        List<UserData> userDataList = new ArrayList<>();

        String query = "select * from user";
        Cursor cursor = db.rawQuery(query, null); // ResultSet

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(1); // index
                String email = cursor.getString(2);
                float height = cursor.getFloat(3);
                int age = cursor.getInt(4);

                userDataList.add(new UserData(name, email, age, height)); // new userdata insert

            } while (cursor.moveToNext());
        }
        return userDataList;
    }
    public void updateUser(int id, UserData userData) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", userData.getName());
        contentValues.put("email", userData.getEmail());
        contentValues.put("height", userData.getHeight());
        contentValues.put("age", userData.getAge());

        int result = db.update("user", contentValues, "id=?", new String[]{String.valueOf(id)});
        if (result > 0) {
            Log.e("db_update", "Success updating data");
        } else {
            Log.e("db_update", "Error updating data");
        }
    }

    public void deleteUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("user", "id=?", new String[]{String.valueOf(id)});
        if (result > 0) {
            Log.e("db_delete", "Success deleting data");
        } else {
            Log.e("db_delete", "Error deleting data");
        }
    }
}
