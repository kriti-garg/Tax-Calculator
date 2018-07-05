package com.kriti.taxcalculator;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.*;
import android.transition.Transition;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.kriti.taxcalculator.data.TaxDbHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button income = findViewById(R.id.income);
        income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,IncomeActivity.class);
                startActivity(intent);
            }
        });
        Button gst = findViewById(R.id.gst);
        gst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
                Intent intent = new Intent(MainActivity.this,GstActivity.class);
                startActivity(intent);

            }
        });
    }
    public void insertData(){
        TaxDbHelper taxDbHelper = new TaxDbHelper(this);
        taxDbHelper.insertDetails("Food",5);
        taxDbHelper.insertDetails("Clothes",5);
        taxDbHelper.insertDetails("Electronics",12);
        taxDbHelper.insertDetails("Utensils",12);
        taxDbHelper.insertDetails("Wood",18);
        Cursor cursor = taxDbHelper.readDetails();
        while (cursor.moveToNext()) {
            Log.v("Hello", "details: " + cursor.getInt(0) + " " + cursor.getString(1) + " " + cursor.getInt(2));
        }
    }
}
