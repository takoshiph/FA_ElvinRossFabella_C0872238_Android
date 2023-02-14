package com.example.fa_elvinrossfabella_c0872238_android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constants.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
        onCreate(db);
    }

    public long insertProduct(String name, String description, String price, String latitude, String longitude){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Constants.P_NAME,name);
        contentValues.put(Constants.P_DESCRIPTION,description);
        contentValues.put(String.valueOf(Constants.P_PRICE),price);
        contentValues.put(String.valueOf(Constants.P_LATITUDE),latitude);
        contentValues.put(String.valueOf(Constants.P_LONGITUDE),longitude);


        long id = db.insert(Constants.TABLE_NAME,null,contentValues);
        return id;

    }

    // Update Function to update data in database
    public void updateProduct(String id, String name, String description, String price, String latitude, String longitude){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Constants.P_NAME,name);
        contentValues.put(Constants.P_DESCRIPTION,description);
        contentValues.put(String.valueOf(Constants.P_PRICE),price);
        contentValues.put(String.valueOf(Constants.P_LATITUDE),latitude);
        contentValues.put(String.valueOf(Constants.P_LONGITUDE),longitude);
        db.update(Constants.TABLE_NAME,contentValues,Constants.P_ID+" =? ",new String[]{id} );

    }
    public void deleteProduct(int id){
        SQLiteDatabase db =  getWritableDatabase();
        db.delete(Constants.TABLE_NAME,Constants.P_ID+" =? ",new String[]{String.valueOf(id)});

    }
    public void deleteAllProduct(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM "+Constants.TABLE_NAME);
    }

    public ArrayList<ProductModel> getAllProduct(){
        ArrayList<ProductModel> arrayList = new ArrayList<>();
        String selectQuery = "SELECT * FROM "+Constants.TABLE_NAME;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do {
                ProductModel productModel = new ProductModel(
                        // only id is integer type
                        ""+cursor.getInt(cursor.getColumnIndexOrThrow(Constants.P_ID)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.P_NAME)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.P_DESCRIPTION)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(String.valueOf(Constants.P_PRICE))),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(String.valueOf(Constants.P_LATITUDE))),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(String.valueOf(Constants.P_LONGITUDE)))
                );
                arrayList.add(productModel);
            }while (cursor.moveToNext());
        }
        return arrayList;
    }

    public ArrayList<ProductModel> getSearchProduct(String query){
        ArrayList<ProductModel> contactList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String queryToSearch =
                "SELECT * FROM "+Constants.TABLE_NAME + " " +
                "WHERE " +Constants.P_NAME + " LIKE '%" +query+"%' OR " +
                        Constants.P_DESCRIPTION + " LIKE '%" +query+"%'";

        Cursor cursor = db.rawQuery(queryToSearch,null);

        // looping through all record and add to list
        if (cursor.moveToFirst()){
            do {
                ProductModel productModel = new ProductModel(
                        // only id is integer type
                        ""+cursor.getInt(cursor.getColumnIndexOrThrow(Constants.P_ID)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.P_NAME)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.P_DESCRIPTION)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(String.valueOf(Constants.P_PRICE))),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(String.valueOf(Constants.P_LATITUDE))),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(String.valueOf(Constants.P_LONGITUDE)))
                );
                contactList.add(productModel);
            }while (cursor.moveToNext());
        }
        // db.close();
        return contactList;

    }

}
