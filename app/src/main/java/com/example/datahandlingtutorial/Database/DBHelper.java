package com.example.datahandlingtutorial.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME  ="UserInfo.db";
    //Constructor for the Database class.
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creating the SQL query.
        String  SQL_CREATE_ENTRIES = "CREATE TABLE " +
                UserMaster.Users.TABLE_NAME + " (" +
                UserMaster.Users._ID + " INTEGER PRIMARY KEY," +
                UserMaster.Users.COLUMN_NAME_USERNAME + " TEXT," +
                UserMaster.Users.COLUMN_NAME_PASSWORD + " TEXT)";

        //Executing the query.
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //Creating the database handling methods
    //Add data to the database
    public long addInfo(String userName, String password){
        //Get the DB in writing mode
        SQLiteDatabase db = getWritableDatabase();

        //Create a new map of values, where column names the keys.
        ContentValues values = new ContentValues();
        values.put(UserMaster.Users.COLUMN_NAME_USERNAME, userName);
        values.put(UserMaster.Users.COLUMN_NAME_PASSWORD, password);

        //Insert the new row, returning the primary key values of the new row.
        long newRowId = db.insert(UserMaster.Users.TABLE_NAME, null, values);

        return newRowId;
    }

    //Read data from the database method
    public List readAllInfo(String req){
        //Getting readable database.
        SQLiteDatabase db = getReadableDatabase();

        //Define a projection that specify which column from the database
        //should be fetch.
        String [] projection = {
                UserMaster.Users._ID,
                UserMaster.Users.COLUMN_NAME_USERNAME,
                UserMaster.Users.COLUMN_NAME_PASSWORD,
        };
        //Filter the result WHERE "username" = 'SLIIT USER'
        //String Selection = Users.COLUMN_NAME_USERNAME + " = ?";
        //String [] selectArgs = {""};

        //How you want the results sorted in the resulting cursor
        String sortOrder = UserMaster.Users.COLUMN_NAME_USERNAME + " DESC";

        Cursor cursor = db.query(
                UserMaster.Users.TABLE_NAME, //Table name
                projection, //Column names to be return
                null, //Columns for the where condition.
                null, //Values for the where condition.
                null, //null means don't group the rows
                null, //null means don't filter the row groups
                sortOrder //sorting order.
        );

        //Create ArrayList to store the data from the resultSet. (Here resultSet = cursor Object).
        List userNames = new ArrayList<>();
        List passwords = new ArrayList<>();

        //Loop to fetch the data
        while (cursor.moveToNext()){
            String username = cursor.getString(cursor.getColumnIndexOrThrow(UserMaster.Users.COLUMN_NAME_USERNAME));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(UserMaster.Users.COLUMN_NAME_PASSWORD));
            userNames.add(username);
            passwords.add(password);
        }

        cursor.close();
        Log.i(TAG, "readAllInfo: "+ userNames);

        if(req.equals("user")){
            return userNames;
        } else if(req.equals("password")){
            return passwords;
        } else {
            return null;
        }
    }

    //Deleting an Object from the database
    public void deleteInfo(String userName){
        SQLiteDatabase db = getReadableDatabase();

        //Define where part for the query
        String selection = UserMaster.Users.COLUMN_NAME_USERNAME + " LIKE ?";
        String [] selectionArgs = { userName };
        //Issue SQL statement
        db.delete(UserMaster.Users.TABLE_NAME, selection, selectionArgs);
    }

    //Implementing the update class
    public int UpdateInfor(String userName, String password){
        //Getting readable database using SQLiteDatabase Helper
        SQLiteDatabase db = getReadableDatabase();

        //New values for one column
        ContentValues values = new ContentValues();
        values.put(UserMaster.Users.COLUMN_NAME_PASSWORD, password);

        //Which row to update, based on the title
        String selection = UserMaster.Users.COLUMN_NAME_USERNAME + " LIKE ?";
        String [] selectionArgs = {userName};

        int count = db.update(
                UserMaster.Users.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );
        return count;
    }


}
