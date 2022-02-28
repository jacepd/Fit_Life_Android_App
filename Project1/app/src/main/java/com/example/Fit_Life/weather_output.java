package com.example.Fit_Life;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.basicinfoname.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class weather_output extends AppCompatActivity implements View.OnClickListener{

    private Button mButtonReturn;
    private WeatherData mWeatherData;
    private TextView mTvMaxTemp = (TextView) findViewById(R.id.tv_temp_val);
    private TextView mTvMinTemp = (TextView) findViewById(R.id.tv_temp_val);
    private double mTemp;
    private double mMaxTemp;
    private double mMinTemp;
    private String allDataStr;
    private String city;
    private String state;

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


        TextView mRainAmount_val = (TextView) findViewById(R.id.tv_amount_of_rain);

        allDataStr = helperMethods.readData(this);
        String[] data = allDataStr.split(",");

        // Begin thread here
        loadWeatherData(allDataStr);
    }

    private void loadWeatherData(String allDataStr){
        new FetchWeatherTask().execute(allDataStr);
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

    private class FetchWeatherTask {

        public ExecutorService executorService = Executors.newSingleThreadExecutor();
        public Handler mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());

        public void execute(String... data) {

            executorService.execute(() -> {

                city = data[7];
                state = data[8];

                String[] fullCity = null;
                String location = null;

                if (city.contains(" ")) {
                    fullCity = city.split(" ");

                    for(int i = 0; i < fullCity.length; i++){
                        location = "" + fullCity[i];
                    }
                }

                else{ location = city; }

                location = location + "," + state;

                // location = "Salt&Lake&City,us";
                URL weatherDataURL = NetworkUtils.buildURLFromString(location);

                String jsonWeatherData = null;

                try {
                    jsonWeatherData = NetworkUtils.getDataFromURL(weatherDataURL);
                    postToMainThread(jsonWeatherData);
                }
                catch (Exception e) { e.printStackTrace(); }


            });
        }

        public void postToMainThread(String jsonWeatherData) {

            mainThreadHandler.post(() -> {
                
                if (jsonWeatherData != null) {

                    try { mWeatherData = JSONWeatherUtils.getWeatherData(jsonWeatherData); }
                    catch (JSONException e) { e.printStackTrace(); }

                    if (mWeatherData != null) {
                        mTemp = mWeatherData.getTemperature().getTemp();
                        mMaxTemp = mWeatherData.getTemperature().getMaxTemp();
                        mMinTemp = mWeatherData.getTemperature().getMinTemp();

                        mTvMaxTemp.setText((int) mMaxTemp);
                    }
                }
            });
        }
    }
}







