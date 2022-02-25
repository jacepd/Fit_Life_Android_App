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
    private double mlongitude = -111.8421;
    private double mlatitude = 40.7649;

    private boolean cameFromBackButton;


    @Override
    @TargetApi(Build.VERSION_CODES.M)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_input);
        setTitle("Fit Life App");


        Intent receivedIntent = getIntent();
        if (receivedIntent.hasExtra("BackButtonPressed")) {
            cameFromBackButton = true;
            Toast.makeText(this, "WAHHHOOOOO", Toast.LENGTH_SHORT).show();

        }
        else{
            cameFromBackButton = false;
        }

        //eventually add option to enter zip code or ask user to pull current location from phones
        mZipcode = (EditText) findViewById(R.id.zipcode_input);

        //Get the button
        mButtonSubmit = (Button) findViewById(R.id.button_loc_submit);
        mButtonSubmit.setOnClickListener(this);

        /*
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {
            // You can use the API that requires the permission.
            Toast.makeText(this, "PERMISSION ALREADY GRANTED", Toast.LENGTH_SHORT).show();

        }
        else {
            // You can directly ask for the permission.
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION}, 44);
            Toast.makeText(this, "ASKING FOR PERMISSION", Toast.LENGTH_SHORT).show();
        }

        LocationRequest mLocationRequest = LocationRequest.create();
        mLocationRequest.setInterval(60000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationServices.getFusedLocationProviderClient(this).getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                //TODO: UI updates.
                location_input.this.mlongitude = location.getLongitude();
                location_input.this.mlatitude = location.getLatitude();
            }
        });

 */
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, body_info_input.class);
        intent.putExtra("BackButtonPressed", true);
        this.startActivity(intent);
        finish();
    }



/*
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 44:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission is granted. Continue the action or workflow
                    // in your app.



                } else {
                    Toast.makeText(this, "no permis :(", Toast.LENGTH_SHORT).show();
                    // Explain to the user that the feature is unavailable because
                    // the features requires a permission that the user has denied.
                    // At the same time, respect the user's decision. Don't link to
                    // system settings in an effort to convince the user to change
                    // their decision.
                }
                return;
        }
    }
*/


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_loc_submit:

                Toast.makeText(this, mlatitude + " " + mlongitude, Toast.LENGTH_SHORT).show();

                String slat = Double.toString(mlatitude);
                String slong = Double.toString(mlongitude);
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

                myList.add(slat);
                myList.add(slong);

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


                //tony was here
                String zipcode = mZipcode.getText().toString();
                //String outStr = mProfileStr + " " + zipcode;

                //Start an activity and pass the EditText string to it.
                Intent messageIntent = new Intent(this, profile_pic_input.class);
                //messageIntent.putExtra("ET_STRING", outStr);
                this.startActivity(messageIntent);



        }
    }

}