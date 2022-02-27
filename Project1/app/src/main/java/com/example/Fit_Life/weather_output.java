package com.example.Fit_Life;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.basicinfoname.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

public class weather_output extends AppCompatActivity implements View.OnClickListener{

    private Button mButtonReturn;
    private WeatherData mWeatherData;
    private WeatherData.Temperature mTemp;
    private double mMaxTemp;
    private WeatherData.CurrentCondition mCurrentCondition;
    private String allDataStr;
    private String firstName;
    private String lastName;
    private int age;
    private int weight;
    private int heightFeet;
    private int heightInches;
    private String sex;
    private String city;
    private String state;
    private String goal;
    private String activityLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTitle("Fit Life - Weather");

        // API Key for Openweathermap: 01ff6680aad0a6ca59af4f7a60f42b04

        // Practice API call for London looks like:
        // http:api.openweathermap.org/data/2.5/weather?q=London,uk&appid=99ea8383701bd7481e5ea568772f739

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_output);

        mButtonReturn = (Button) findViewById(R.id.button_return);
        mButtonReturn.setOnClickListener(this);

        TextView mLoc_val = (TextView) findViewById(R.id.tv_loc_val);

        TextView mMaxTemp_val = (TextView) findViewById(R.id.tv_temp_val);
        // TextView mMinTemp_val = (TextView) findViewById(R.id.tv_temp_val);

        TextView mRainAmount_val = (TextView) findViewById(R.id.tv_amount_of_rain);

        allDataStr = helperMethods.readData(this);
        String[] data = allDataStr.split(",");

        mTemp = mWeatherData.getTemperature();
        mMaxTemp = mTemp.getMaxTemp();

        mMaxTemp_val.setText((int) mMaxTemp);

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

    private class FetchWeatherTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... data) {

            // Get City/State from user file.
            city = data[11];
            state = data[12];

            String location = "Salt&Lake&City,us";
            URL weatherDataURL = NetworkUtils.buildURLFromString(location);

            String jsonWeatherData = null;
            try {
                jsonWeatherData = NetworkUtils.getDataFromURL(weatherDataURL);
                return jsonWeatherData;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String jsonWeatherData) {
            if (jsonWeatherData != null){
                try {
                    mWeatherData = JSONWeatherUtils.getWeatherData(jsonWeatherData);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
    }
}







