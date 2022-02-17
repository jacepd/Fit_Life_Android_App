package com.example.Fit_Life;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.basicinfoname.R;

import java.net.URL;

public class weather_output extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_output);

        if(savedInstanceState!=null)
        {
            Fragment weatherFragment = getSupportFragmentManager().findFragmentByTag("weather_frag");
        }
        else {
            WeatherFragment weatherFragment = new WeatherFragment();
            FragmentTransaction fTrans = getSupportFragmentManager().beginTransaction();
            fTrans.replace(R.id.fl_frag_weather, weatherFragment, "weather_frag");
            fTrans.commit();
        }


        Intent receivedIntent = getIntent();

        setTitle("Fit Life - Home");

        String recieved = receivedIntent.getStringExtra("ET_STRING");

        String[] allData = recieved.split(" ");

        String zipcode = allData[5];

        String URL = "http://api.openweathermap.org/data/2.5/weather?q=";

        URL weatherDataURL = NetworkUtils.buildURLFromString(zipcode);



    }
}