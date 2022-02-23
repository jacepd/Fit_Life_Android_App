package com.example.Fit_Life;

import androidx.appcompat.app.AppCompatActivity;
import com.example.basicinfoname.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class myInfo_page extends AppCompatActivity
        implements View.OnClickListener{

    private Button mButtonSaveReturn;
    private EditText mFirstName;
    private EditText mLastName;
    private EditText mHeightFt;
    private EditText mHeightIn;
    private EditText mWeight;
    private EditText mGoal;
    private EditText mActLvl;
    private EditText mAge;
    private EditText mSex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info_page);

        mButtonSaveReturn = (Button) findViewById(R.id.button_return);
        mButtonSaveReturn.setOnClickListener(this);

        mFirstName = (EditText) findViewById(R.id.firstName_input);
        mLastName = (EditText) findViewById(R.id.lastName_input);
        mHeightFt = (EditText) findViewById(R.id.heightFt_input);
        mHeightIn = (EditText) findViewById(R.id.heightIn_input);
        mWeight = (EditText) findViewById(R.id.weight_input);
        mGoal = (EditText) findViewById(R.id.goal_input);
        mActLvl = (EditText) findViewById(R.id.actLvL_input);
        mSex = (EditText) findViewById(R.id.sex_input);
        //mAge = (EditText) findViewById(R.id.age_input);

        helperMethods.readData()



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_return:

                //save info to file


                Intent messageIntent = new Intent(this, MainActivity.class);
                this.startActivity(messageIntent);
        }
    }
}