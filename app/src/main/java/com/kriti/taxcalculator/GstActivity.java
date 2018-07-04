package com.kriti.taxcalculator;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Loader;
import android.app.LoaderManager;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.CursorLoader;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.kriti.taxcalculator.data.TaxContract;

import static com.kriti.taxcalculator.data.TaxContract.TaxEntry._ID;

public class GstActivity extends AppCompatActivity  implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int PRODUCT_LOADER = 0;
    private TaxAdapter mCursorAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gst);
        ListView noteListView = findViewById(R.id.list);
        View emptyView = findViewById(R.id.empty_view);
        noteListView.setEmptyView(emptyView);
        mCursorAdapter = new TaxAdapter(this);
        noteListView.setAdapter(mCursorAdapter);
        getLoaderManager().initLoader(PRODUCT_LOADER,null,this);
    }

    public void showAlertBox(String item, Integer gst) {
        final EditText amountEditText = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Amount");
        builder.setMessage("Amount of " + item);
        builder.setView(amountEditText);
        String amountEntered = amountEditText.getText().toString().trim();
        final Double amount = Double.valueOf(amountEntered).doubleValue();
        final Integer tax = gst;
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                calculate(amount, tax);
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void calculate(Double amount, Integer gst) {

    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        // Uri baseUri = ;
        // Define a projection that specifies the columns from the table we care about.
        String[] projection = {
                _ID,
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
