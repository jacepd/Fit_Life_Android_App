package com.example.Fit_Life;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.basicinfoname.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

public class location_input extends AppCompatActivity
        implements View.OnClickListener {

    private EditText mZipcode;
    private Button mButtonSubmit;
    private FusedLocationProviderClient fusedLocationClient;
    private ActivityResultLauncher<String[]> locationPermissionRequest;
    private EditText mCity;
    private String mState;
    private String selectedState;
    private boolean cameFromBackButton;
    private NumberPicker mStatesNumberPicker;


    @Override
    @TargetApi(Build.VERSION_CODES.M)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_input);
        setTitle("Fit Life App - Location");

        Intent receivedIntent = getIntent();
        if (receivedIntent.hasExtra("BackButtonPressed")) {
            cameFromBackButton = true;
            Toast.makeText(this, "WAHHHOOOOO", Toast.LENGTH_SHORT).show();

        }
        else{
            cameFromBackButton = false;
        }

        //Get the button
        mButtonSubmit = (Button) findViewById(R.id.button_loc_submit);
        mButtonSubmit.setOnClickListener(this);

        mCity = (EditText) findViewById(R.id.city_input);

        String[] stateArray = getResources().getStringArray(R.array.us_states);

        //state number picker
        mStatesNumberPicker = (NumberPicker) findViewById(R.id.stateNumberPicker);
        mStatesNumberPicker.setMinValue(0);
        mStatesNumberPicker.setMaxValue(49);
        mStatesNumberPicker.setDisplayedValues(stateArray);
        mStatesNumberPicker.setValue(43);
        selectedState = stateArray[mStatesNumberPicker.getValue()];
        mStatesNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
                selectedState = stateArray[newVal];
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, body_info_input.class);
        intent.putExtra("BackButtonPressed", true);
        this.startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_loc_submit:

                ArrayList<String> myList = new ArrayList<>();

                String allDataStr = helperMethods.readData(this);
                String[] old_data = allDataStr.split(",");
                if(cameFromBackButton){
                    Toast.makeText(this, "WOOOOHOOOOO", Toast.LENGTH_SHORT).show();
                    String firstName = old_data[0];
                    String lastName = old_data[1];
                    String age = old_data[2];
                    String weight = old_data[3];
                    String heightFeet = old_data[4];
                    String heightInches = old_data[5];
                    String sex = old_data[6];
                    myList.add(firstName);
                    myList.add(lastName);
                    myList.add(age);
                    myList.add(weight);
                    myList.add(heightFeet);
                    myList.add(heightInches);
                    myList.add(sex);
                }

                myList.add(mCity.getText().toString());
                myList.add(selectedState);

                if(cameFromBackButton){
                    String goal = old_data[9];
                    String activityLevel = old_data[10];
                    myList.add(goal);
                    myList.add(activityLevel);
                    helperMethods.saveData(myList, this, false);
                }
                else{
                    myList.add("NoGoalSelected");
                    myList.add("NoActivityLevelSelected");
                    helperMethods.saveData(myList, this, true);
                }

                Intent messageIntent = new Intent(this, profile_pic_input.class);
                this.startActivity(messageIntent);
        }
    }

}