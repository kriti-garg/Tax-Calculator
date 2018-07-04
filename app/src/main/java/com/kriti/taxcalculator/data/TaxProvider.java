package com.kriti.taxcalculator.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * Created by kriti on 4/7/18.
 */

public class TaxProvider extends ContentProvider {
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int TAX = 100;
    private static final int TAX_ID = 101;
    static {
        sUriMatcher.addURI(TaxContract.CONTENT_AUTHORITY, TaxContract.PATH_TAX, TAX);
        sUriMatcher.addURI(TaxContract.CONTENT_AUTHORITY, TaxContract.PATH_TAX + "/#", TAX_ID);
    }
    private TaxDbHelper mDbHelper;
    public boolean onCreate() {
        mDbHelper = new TaxDbHelper(getContext());
        return true;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }
    @Override
    public int update(Uri uri, ContentValues contentValues, String selection,
                      String[] selectionArgs) { return 0;}
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder){
        SQLiteDatabase database = mDbHelper.getReadableDatabase();
        // This cursor will hold the result of the query
        Cursor cursor;

        // Figure out if the URI matcher can match the URI to a specific code
        int match = sUriMatcher.match(uri);

        cursor = database.query(TaxContract.TaxEntry.TABLE_NAME, projection, selection, selectionArgs,
                null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        // Return the cursor
        return cursor;
    }
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return  0;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values){ return null;}
}



