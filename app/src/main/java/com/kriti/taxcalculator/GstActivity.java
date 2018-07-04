package com.kriti.taxcalculator;

import android.content.Loader;
import android.app.LoaderManager;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.CursorLoader;
import android.view.View;
import android.widget.ListView;

import com.kriti.taxcalculator.data.TaxContract;

public class GstActivity extends AppCompatActivity  implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int PRODUCT_LOADER = 0;
    private TaxAdapter mCursorAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gst);
        ListView noteListView = findViewById(R.id.listview);
        View emptyView = findViewById(R.id.text);
        noteListView.setEmptyView(emptyView);
        mCursorAdapter = new TaxAdapter(this);
        noteListView.setAdapter(mCursorAdapter);
        getLoaderManager().initLoader(PRODUCT_LOADER,null,this);
    }
    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        // Uri baseUri = ;
        // Define a projection that specifies the columns from the table we care about.
        String[] projection = {
                TaxContract.TaxEntry._ID,
                TaxContract.TaxEntry.COLUMN_ITEMS,
                TaxContract.TaxEntry.COLUMN_TAX,
        };

        // This loader will execute the ContentProvider's query method on a background thread
        return new CursorLoader(this,   // Parent activity context
               TaxContract.TaxEntry.CONTENT_URI,   // Provider content URI to query
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);                  // Default sort order
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // Update {@link ProductCursorAdapter} with this new cursor containing updated product data
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // Callback called when the data needs to be deleted
        mCursorAdapter.swapCursor(null);
    }

}
