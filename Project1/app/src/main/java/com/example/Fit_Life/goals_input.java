package com.example.Fit_Life;

import androidx.appcompat.app.AppCompatActivity;
import com.example.basicinfoname.R;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class goals_input extends AppCompatActivity
        implements View.OnClickListener {

    private Button mButtonSubmit;
    private NumberPicker mActivityLevelNumberPicker;
    private NumberPicker mGoalNumberPicker;

    private String [] activityLevelArray;
    private String [] goalArray;

    private String selectedActivityLevel;
    private String selectedGoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals_input);


        //Get the button
        mButtonSubmit = (Button) findViewById(R.id.button_submit);
        mButtonSubmit.setOnClickListener(this);


        //First number picker
        activityLevelArray = getResources().getStringArray(R.array.activity);

        //sex feet number picker
        mActivityLevelNumberPicker = (NumberPicker) findViewById(R.id.activityLevelNumberPicker);
        mActivityLevelNumberPicker.setMinValue(0);
        mActivityLevelNumberPicker.setMaxValue(1);
        mActivityLevelNumberPicker.setDisplayedValues(activityLevelArray);
        mActivityLevelNumberPicker.setValue(6);
        selectedActivityLevel = activityLevelArray[mActivityLevelNumberPicker.getValue()];
        mActivityLevelNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
                selectedActivityLevel = activityLevelArray[newVal];
            }
        });


        //Second number picker
        goalArray = getResources().getStringArray(R.array.weightGoals);

        //sex feet number picker
        mGoalNumberPicker = (NumberPicker) findViewById(R.id.goalNumberPicker);
        mGoalNumberPicker.setMinValue(0);
        mGoalNumberPicker.setMaxValue(2);
        mGoalNumberPicker.setDisplayedValues(goalArray);
        mGoalNumberPicker.setValue(6);
        selectedGoal = goalArray[mGoalNumberPicker.getValue()];
        mGoalNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
                selectedGoal = goalArray[newVal];
            }
        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_submit: {

                String goal = selectedGoal;
                String activityLevel = selectedActivityLevel;

                File myDir = this.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
                String fname = "userData.txt";
                File userInfoFile = new File(myDir, fname);

                String allDataStr = helperMethods.readData(userInfoFile);
                String[] datas = allDataStr.split(",");

                datas[9] = goal;
                datas[10] = activityLevel;

                helperMethods.saveData(datas, goals_input.this, false);

                Intent messageIntent = new Intent(goals_input.this, MainActivity.class);
                this.startActivity(messageIntent);

            }
        }
    }

}
