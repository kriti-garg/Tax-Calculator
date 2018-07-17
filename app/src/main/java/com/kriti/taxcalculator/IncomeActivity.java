package com.kriti.taxcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class IncomeActivity extends AppCompatActivity {
    public String editIncome;
    public EditText et_Income;
    public TextView tv_textarea;
    public Double finalValue,intIncome;
    public Double tax;
    Button calAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        et_Income = findViewById(R.id.editIncome);
        tv_textarea = findViewById(R.id.textarea);
        calAmount = findViewById(R.id.amount);
        calAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateIncome(); }
        });
    }

    void calculateIncome(){
        intialise();
        check();
    }
    void intialise(){
        editIncome = et_Income.getText().toString().trim();
        //intIncome = Integer.parseInt(editIncome);

        if(validate()){
            intIncome = Double.valueOf(editIncome).doubleValue();
        }
    }

    public boolean validate(){
        boolean valid =true;
        if(editIncome.isEmpty()){
            et_Income.setError("Please enter valid income");
            valid=false;
        }
        return valid;
    }

    void check(){
        if(validate()){
            if (intIncome >= 0 && intIncome <= 250000) {
                tax = 0.0;
                finalValue = tax + intIncome;
                tv_textarea.setText("Tax on your income " + editIncome + " = " + tax + "\n\n" + "Total income (Inclusion of Tax)" + " = " + finalValue);

            } else if (intIncome > 250000.0 && intIncome <= 500000.0) {
                tax = (0.05 * (intIncome - 250000.0));
                finalValue = tax + intIncome;
                tv_textarea.setText("Tax on your income " + editIncome + " = " + tax + "\n\n" + "Total income (Inclusion of Tax)" + " = " + finalValue);
            } else if (intIncome > 500000.0 && intIncome <= 1000000.0) {
                tax = 25000 + 0.20 * (intIncome - 500000);
                finalValue = tax + intIncome;
                tv_textarea.setText("Tax on your income " + editIncome + " = " + tax + "\n\n" + "Total income (Inclusion of Tax)" + " = " + finalValue);
            } else if (intIncome > 1000000.0) {
                tax = 112500.0 + 0.30 * (intIncome - 1000000);
                finalValue = tax + intIncome;
                tv_textarea.setText("Tax on your income " + editIncome + " = " + tax + "\n\n" + "Total income (Inclusion of Tax)" + " = " + finalValue);
            }
        }
    }

}
