package com.example.Fit_Life;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.basicinfoname.R;

import java.net.URL;

public class weather_output extends AppCompatActivity implements View.OnClickListener {

    private Button mButtonReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTitle("Fit Life - Weather");

        // API Key for Openweathermap: 01ff6680aad0a6ca59af4f7a60f42b04

        // Practice API call for London looks like:
        // http:api.openweathermap.org/data/2.5/weather?q=London,uk&appid=99ea8383701bd7481e5ea568772f739

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_output);

        String location = "Salt&Lake&City,us";
        URL weatherDataURL = NetworkUtils.buildURLFromString(location);

        String jsonWeatherData = null;
        try {jsonWeatherData = NetworkUtils.getDataFromURL(weatherDataURL);}

        catch (Exception e) {
            e.printStackTrace();
        }

        mButtonReturn = (Button) findViewById(R.id.button_return);
        mButtonReturn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_return:

                Intent messageIntent = new Intent(this, MainActivity.class);
                this.startActivity(messageIntent);
                break;
        }
    }
}







