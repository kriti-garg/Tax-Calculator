package com.kriti.taxcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.*;
import android.transition.Transition;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       Log.v("hiii in" , "main activity");
    }
}
