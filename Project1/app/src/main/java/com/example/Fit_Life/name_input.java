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

public class name_input extends AppCompatActivity
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
        setContentView(R.layout.activity_name_input);

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

                //Error checking:
                // make sure its a valid name and it's a string
                // account for weird last names with spaces and hyphens

                String first = mFirst_input.getText().toString();
                String last = mLast_input.getText().toString();
                TextView tv = (TextView)findViewById(R.id.display_text);
                String mFullName = first + " " + last;
                tv.setText(first + " " + last);

                if (mFullName.matches("")) {
                    //Complain that there's no text
                    Toast.makeText(name_input.this, "Enter a name first!", Toast.LENGTH_SHORT).show();
                }
                else {
                    //Start an activity and pass the EditText string to it.
                    Intent messageIntent = new Intent(this, body_info_input.class);
                    messageIntent.putExtra("ET_STRING", first + " " + last);
                    this.startActivity(messageIntent);
                }

        }
    }
}