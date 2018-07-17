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
import android.widget.TextView;
import android.widget.Toast;

import com.kriti.taxcalculator.data.TaxContract;

import static com.kriti.taxcalculator.data.TaxContract.TaxEntry._ID;

public class GstActivity extends AppCompatActivity  implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int PRODUCT_LOADER = 0;
    private TaxAdapter mCursorAdapter;
    public TextView finalAmount,finalAmountstr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gst);
        ListView noteListView = findViewById(R.id.list);
        View emptyView = findViewById(R.id.empty_text);
        finalAmount = findViewById(R.id.finalamount);
        finalAmountstr = findViewById(R.id.finalamountstr);
        finalAmount.setVisibility(View.GONE);
        finalAmountstr.setVisibility(View.GONE);
        noteListView.setEmptyView(emptyView);
        mCursorAdapter = new TaxAdapter(this);
        noteListView.setAdapter(mCursorAdapter);
        getLoaderManager().initLoader(PRODUCT_LOADER,null,this);
    }

    public void showAlertBox(final String item, Integer gst) {
        Log.i("item",item);
        Log.i("gst",gst.toString());

        final EditText amountEditText = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Amount");
        builder.setMessage("Amount of " + item);
        builder.setView(amountEditText);

        final Integer tax = gst;
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                calculate(amountEditText,tax, item);
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

    private void calculate(EditText amountEditText, Integer gst, String item) {
        String amountEntered = amountEditText.getText().toString().trim();
        if(!amountEntered.equals("")) {
            final Double amount = Double.valueOf(amountEntered).doubleValue();
            Double tax = (0.01 * gst * amount);
            Double finalValue = tax + amount;
            finalAmount.setVisibility(View.VISIBLE);
            finalAmountstr.setVisibility(View.VISIBLE);
            finalAmountstr.setText("Final Amount of " + item + " after GST of " + gst + "%");
            finalAmount.setText(" " + finalValue + " ");
        }
        else
        {
            Toast.makeText(this,"Please enter valid value",Toast.LENGTH_SHORT).show();
        }
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
