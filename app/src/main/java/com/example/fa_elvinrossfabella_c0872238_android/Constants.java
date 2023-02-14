package com.example.fa_elvinrossfabella_c0872238_android;

public class Constants {

    public static final String DATABASE_NAME = "PRODUCT_DB";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "PRODUCT_TABLE";
    public static final String P_ID = "ID";
    public static final String P_NAME = "NAME";
    public static final String P_DESCRIPTION = "DESCRIPTION";
    public static final String P_PRICE = "PRICE";
    public static final String P_LATITUDE = "LATITUDE";
    public static final String P_LONGITUDE = "LONGITUDE";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "( "
            + P_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + P_NAME + " TEXT, "
            + P_DESCRIPTION + " TEXT, "
            + P_PRICE + " REAL, "
            + P_LATITUDE + " REAL, "
            + P_LONGITUDE + " REAL "
            + " );";
}
