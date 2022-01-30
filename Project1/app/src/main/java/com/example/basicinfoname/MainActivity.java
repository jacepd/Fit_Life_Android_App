package com.example.basicinfoname;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener{

    private Button mButtonSubmit;
    private EditText mFirst_input;
    private EditText mLast_input;
    private String mFirstName;
    private String mLastName;
    private String mDisplayText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get the button
        mButtonSubmit = (Button) findViewById(R.id.button_submit);
        mButtonSubmit.setOnClickListener(this);

        //Get the EditText
        mFirst_input = (EditText) findViewById(R.id.firstName_input);
        mLast_input = (EditText) findViewById(R.id.lastName_input);

    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId()) {
            case R.id.button_submit:
                mDisplayText = mFirst_input.getText().toString();
        }
    }
}