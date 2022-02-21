package com.example.Fit_Life;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.basicinfoname.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.util.List;

public class location_input extends AppCompatActivity
        implements View.OnClickListener {

    //private String mProfileStr;
    private EditText mZipcode;
    private Button mButtonSubmit;
    //private FusedLocationProviderClient fusedLocationClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_input);
       // fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        setTitle("Fit Life App");

          //commenting out intent bundle stuff. No need for it because now we save to files
//        //Get the intent that created this activity.
//        Intent receivedIntent = getIntent();
//
//        //Get the string data
//        mProfileStr = receivedIntent.getStringExtra("ET_STRING");
//
//        if (mProfileStr.matches("")) {
//            Toast.makeText(location_input.this, "Empty string received", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(location_input.this, mProfileStr, Toast.LENGTH_SHORT).show();
//        }


        //eventually add option to enter zip code or ask user to pull current location from phones
        mZipcode = (EditText) findViewById(R.id.zipcode_input);

        //Get the button
        mButtonSubmit = (Button) findViewById(R.id.button_loc_submit);
        mButtonSubmit.setOnClickListener(this);

    }


    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(location_input.this,
                            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                        getLastLocation();
                    }
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_loc_submit:


                //tony was here
                //getLastLocation();
                String zipcode = mZipcode.getText().toString();
                //String outStr = mProfileStr + " " + zipcode;

                //Start an activity and pass the EditText string to it.
                Intent messageIntent = new Intent(this, profile_pic_input.class);
                //messageIntent.putExtra("ET_STRING", outStr);
                this.startActivity(messageIntent);



        }
    }


    private void getLastLocation() {
        LocationManager lm = (LocationManager) getSystemService(this.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.


            if (ActivityCompat.shouldShowRequestPermissionRationale(location_input.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)){
                ActivityCompat.requestPermissions(location_input.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }else{
                ActivityCompat.requestPermissions(location_input.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }

//                    ActivityCompat.requestPermissions(this, new String[]{
//                            Manifest.permission.ACCESS_COARSE_LOCATION,
//                            Manifest.permission.ACCESS_FINE_LOCATION}, 44);
            return;
        }


        List<String> providers = lm.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            Location l = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                // Found best last known location: %s", l);
                bestLocation = l;
            }
        }

        //Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        double longitude = bestLocation.getLongitude();
        double latitude = bestLocation.getLatitude();

        String zipcode = mZipcode.getText().toString();
        //String outStr = mProfileStr + " " + zipcode;

        //Start an activity and pass the EditText string to it.
        Intent messageIntent = new Intent(this, profile_pic_input.class);
        //messageIntent.putExtra("ET_STRING", outStr);
        this.startActivity(messageIntent);
    }

    private Location getLastKnownLocation() {
        LocationManager mLocationManager = (LocationManager)getApplicationContext().getSystemService(LOCATION_SERVICE);
        List<String> providers = mLocationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            Location l = mLocationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                // Found best last known location: %s", l);
                bestLocation = l;
            }
        }
        return bestLocation;
    }

}