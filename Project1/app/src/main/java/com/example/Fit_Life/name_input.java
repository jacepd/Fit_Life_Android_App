package com.example.Fit_Life;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.basicinfoname.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class name_input extends AppCompatActivity
        implements View.OnClickListener{

    private Button mButtonSubmit;
    private EditText mFirst_input;
    private EditText mLast_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_name_input);
        setTitle("Fit Life - User Set Up");

        //Get the button
        mButtonSubmit = (Button) findViewById(R.id.button_submit);
        mButtonSubmit.setOnClickListener(this);

        //Get the EditText
        mFirst_input = (EditText) findViewById(R.id.firstName_input);
        mLast_input = (EditText) findViewById(R.id.lastName_input);

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, 55);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId()) {
            case R.id.button_submit:

                //Error checking:
                // make sure its a valid name and it's a string
                //Even better, don't even let them input any non string
                // account for weird last names with spaces and hyphens

                String first = mFirst_input.getText().toString();
                String last = mLast_input.getText().toString();
                ArrayList<String> myList = new ArrayList<String>();
                myList.add(first);
                myList.add(last);

                if (first.matches("")) {
                    //Complain that there's no text
                    Toast.makeText(name_input.this, "Enter a first name!", Toast.LENGTH_SHORT).show();
                }
                else if (last.matches("") ) {
                    //Complain that there's no text
                    Toast.makeText(name_input.this, "Enter a last name!", Toast.LENGTH_SHORT).show();
                }
                else {
                    //Start an activity and pass the EditText string to it.

                    helperMethods.saveData(myList, this, false);

                    Intent messageIntent = new Intent(this, body_info_input.class);
                    //String mFullName = first + " " + last;
                    //messageIntent.putExtra("ET_STRING", mFullName);
                    this.startActivity(messageIntent);
                }

        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, name_input.class);
        intent.putExtra("BackButtonPressed", true);
        this.startActivity(intent);
        finish();
    }


}