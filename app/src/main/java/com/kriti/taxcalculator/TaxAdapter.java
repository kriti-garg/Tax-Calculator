package com.kriti.taxcalculator;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.kriti.taxcalculator.data.TaxContract;

/**
 * Created by kriti on 4/7/18.
 */

public class TaxAdapter extends CursorAdapter {
    private final GstActivity activity;
    public TaxAdapter(GstActivity context){
        super(context, null, 0 /* flags */);
        this.activity = context;
    }
    public void bindView(View view, Context context, Cursor cursor){
        TextView itemTextView = view.findViewById(R.id.itemText);
        int itemColumnIndex= cursor.getColumnIndex(TaxContract.TaxEntry.COLUMN_ITEMS);
        String title = cursor.getString(itemColumnIndex);
        itemTextView.setText(title);


    }
    public View newView(Context context, Cursor cursor, ViewGroup parent){
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

}
