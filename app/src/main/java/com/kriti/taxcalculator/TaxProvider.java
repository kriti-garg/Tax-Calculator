package com.kriti.taxcalculator;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.net.Uri;

/**
 * Created by kriti on 4/7/18.
 */

public class TaxProvider extends ContentProvider {


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return  0;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values){ return null;}
}
