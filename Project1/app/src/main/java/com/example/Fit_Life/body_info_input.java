package com.example.Fit_Life;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.basicinfoname.R;

import java.util.ArrayList;

public class body_info_input extends AppCompatActivity
        implements View.OnClickListener{

    private String mFullNameReceived;
    private Button mButtonSubmit;
    private EditText mAge;
    private EditText mHeight;
    private EditText mWeight;
    private EditText mSex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_info_input);

        setTitle("Fit Life App");


        //Get the intent that created this activity.
        //Intent receivedIntent = getIntent();

        //Get the string data
//        mFullNameReceived = receivedIntent.getStringExtra("ET_STRING");
//
//        if(mFullNameReceived.matches("")){
//            Toast.makeText(body_info_input.this, "Empty string received", Toast.LENGTH_SHORT).show();
//        }
//        else{
//            Toast.makeText(body_info_input.this, mFullNameReceived, Toast.LENGTH_SHORT).show();
//        }

        //change to number picker later with date of birth instead of age input
        mAge    = (EditText) findViewById(R.id.age_input);
        mWeight = (EditText) findViewById(R.id.weight_input);
        mHeight = (EditText) findViewById(R.id.height_input);
        mSex = (EditText) findViewById(R.id.sex_input);

        //Get the button
        mButtonSubmit = (Button) findViewById(R.id.button_submit);
        mButtonSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId()) {
            case R.id.button_submit:

                //Error checking: ????

                String age = mAge.getText().toString();
                String weight = mWeight.getText().toString();
                String height = mHeight.getText().toString();
                String sex = mSex.getText().toString();



                ArrayList<String> myList = new ArrayList<String>();
                myList.add(age);
                myList.add(weight);
                myList.add(height);
                myList.add(sex);
                helperMethods.saveData(myList, this, true);

                //Start an activity and pass the EditText string to it.
                Intent messageIntent = new Intent(this, location_input.class);
                //String outStr = mFullNameReceived + " " + age + " " + weight + " " + height + " " + sex;
                //messageIntent.putExtra("ET_STRING", outStr);
                this.startActivity(messageIntent);

        }
    }
}