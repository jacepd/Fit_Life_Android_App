package com.example.Fit_Life;

import androidx.appcompat.app.AppCompatActivity;
import com.example.basicinfoname.R;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

public class goals_input extends AppCompatActivity
        implements View.OnClickListener {

    private Button mButtonSubmit;
    private NumberPicker mActivityNumberPicker;
    private NumberPicker mGoalNumberPicker;

    private String [] activityArray;
    private String [] goalArray;

    private String selectedActivity;
    private String selectedGoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals_input);


        //Get the button
        mButtonSubmit = (Button) findViewById(R.id.button_submit);
        mButtonSubmit.setOnClickListener(this);


        //First number picker
        activityArray = getResources().getStringArray(R.array.sex);

        //sex feet number picker
        mActivityNumberPicker = (NumberPicker) findViewById(R.id.activityNumberPicker);
        mActivityNumberPicker.setMinValue(0);
        mActivityNumberPicker.setMaxValue(1);
        mActivityNumberPicker.setDisplayedValues(activityArray);
        mActivityNumberPicker.setValue(6);
        selectedActivity = activityArray[mActivityNumberPicker.getValue()];
        mActivityNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
                selectedActivity = activityArray[newVal];
            }
        });



    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_submit: {

            }
        }
    }

}