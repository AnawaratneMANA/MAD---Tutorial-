package com.example.datahandlingtutorial.Database;

import android.provider.BaseColumns;

public class UserMaster {
    //Constructor
    public UserMaster(){

    }
    //Method
    public static class Users implements BaseColumns{
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_PASSWORD = "password";

    }
}
