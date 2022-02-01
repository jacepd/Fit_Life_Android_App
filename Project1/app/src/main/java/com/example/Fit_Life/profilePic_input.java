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

public class profilePic_input extends AppCompatActivity
        implements View.OnClickListener{

    private String mProfileStr;
    private Button mButtonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilepic_input);

        //Get the intent that created this activity.
        Intent receivedIntent = getIntent();

        //Get the string data
        mProfileStr = receivedIntent.getStringExtra("ET_STRING");

        if(mProfileStr.matches("")){
            Toast.makeText(profilePic_input.this, "Empty string received", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(profilePic_input.this, mProfileStr, Toast.LENGTH_SHORT).show();
        }

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

//                String outStr = mFullNameReceived + " " + age + " " + weight + " " + height;
//
//                //Start an activity and pass the EditText string to it.
//                Intent messageIntent = new Intent(this, location_input.class);
//                messageIntent.putExtra("ET_STRING", outStr);
//                this.startActivity(messageIntent);

        }
    }
}
