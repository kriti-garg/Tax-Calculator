package com.kriti.taxcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.*;
import android.transition.Transition;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
                Intent intent = new Intent(MainActivity.this,GstActivity.class);
                startActivity(intent);

            }
        });
    }
}
