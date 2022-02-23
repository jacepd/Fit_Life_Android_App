package com.example.Fit_Life;

import androidx.appcompat.app.AppCompatActivity;
import com.example.basicinfoname.R;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;

public class bmi_page extends AppCompatActivity
        implements View.OnClickListener{

    private Button mButtonReturn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_page);

        setTitle("Fit Life - BMI");

        //Get the button
        mButtonReturn = (Button) findViewById(R.id.button_return);
        mButtonReturn.setOnClickListener(this);

        TextView mBMI_val = (TextView) findViewById(R.id.bmi_val);
        TextView mBMI_type = (TextView) findViewById(R.id.bmi_type);


        String allDataStr = helperMethods.readData(this);
        String[] datas = allDataStr.split(",");
        double weight = Double.parseDouble(datas[3]);
        double heightFeet = Double.parseDouble(datas[4]);
        double heightInches = Double.parseDouble(datas[5]);
        double tHeight = heightFeet * 12;
        tHeight += heightInches;
        double result = (weight / (tHeight * tHeight)) * 703;
        result = helperMethods.round(result,1);

        mBMI_val.setText(Double.toString(result));
        if (result < 18.5){
            mBMI_type.setText(" UNDERWEIGHT ");
            mBMI_val.setTextColor(Color.parseColor("#42b0f5"));
            mBMI_type.setTextColor(Color.parseColor("#42b0f5"));
        }
        else if (result <= 24.9){
            mBMI_type.setText(" NORMAL ");
            mBMI_val.setTextColor(Color.parseColor("#07eb5e"));
            mBMI_type.setTextColor(Color.parseColor("#07eb5e"));
        }
        else if (result <= 29.9){
            mBMI_type.setText(" OVERWEIGHT ");
            mBMI_val.setTextColor(Color.parseColor("#d4eb07"));
            mBMI_type.setTextColor(Color.parseColor("#d4eb07"));
        }
        else if (result <= 34.9){
            mBMI_type.setText(" OBESE ");
            mBMI_val.setTextColor(Color.parseColor("#f58607"));
            mBMI_type.setTextColor(Color.parseColor("#f58607"));
        }
        else{
            mBMI_type.setText(" EXTREMELY OBESE ");
            mBMI_val.setTextColor(Color.parseColor("#f51707"));
            mBMI_type.setTextColor(Color.parseColor("#f51707"));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_return:
                Intent messageIntent = new Intent(this, MainActivity.class);
                this.startActivity(messageIntent);
        }
    }
}