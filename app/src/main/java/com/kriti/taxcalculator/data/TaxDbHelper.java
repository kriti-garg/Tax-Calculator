package com.kriti.taxcalculator.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.kriti.taxcalculator.data.TaxContract.TaxEntry;

/**
 * Created by kriti on 3/7/18.
 */

public class TaxDbHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "GST.db";
        private static final int DATABASE_VERSION = 1;
        public TaxDbHelper(Context context){
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
        }
    public void onCreate(SQLiteDatabase db){
        String SQL_CREATE_TAX_DETAILS_TABLE = "CREATE TABLE IF NOT EXISTS " + TaxEntry.TABLE_NAME + " ("
                + TaxEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +TaxEntry.COLUMN_ITEMS + " TEXT NOT NULL UNIQUE, "
                +TaxEntry.COLUMN_TAX + " INTEGER NOT NULL);";
        Log.v("TaxDbHelper" , "create table: " + SQL_CREATE_TAX_DETAILS_TABLE);
        db.execSQL(SQL_CREATE_TAX_DETAILS_TABLE);

    }
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){ }

    public Cursor readDetails(){
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                TaxEntry._ID,
                TaxEntry.COLUMN_ITEMS,
                TaxEntry.COLUMN_TAX,
        };
        Cursor cursor =db.query(
                TaxEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        return  cursor;
    }
    public long insertDetails(String itemsString , Integer taxString){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TaxContract.TaxEntry.COLUMN_ITEMS,itemsString);
        values.put(TaxContract.TaxEntry.COLUMN_TAX,taxString);
        long newRowId = db.insert(TaxContract.TaxEntry.TABLE_NAME,null,values);
        if(newRowId==-1){
            Log.v("TaxDbHelper", "Error in insertion");
        }
        else{
            Log.v("TaxDbHelper","insertion Successful");

        }
        return newRowId;
    }

}
