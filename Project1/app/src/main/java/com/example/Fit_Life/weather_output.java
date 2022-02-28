package com.example.Fit_Life;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.basicinfoname.R;
import org.json.JSONException;
import java.net.URL;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class weather_output extends AppCompatActivity implements View.OnClickListener{

    private Button mButtonReturn;
    private Button mButtonUpdate;
    private WeatherData mWeatherData;
    private EditText mEtLocation;

    private TextView mTvTemp;
    private TextView mTvMaxTemp;
    private TextView mTvMinTemp;

    private TextView mTvRainAmount;

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
        mButtonUpdate = (Button) findViewById(R.id.button_update);

        mButtonReturn.setOnClickListener(this);
        mButtonUpdate.setOnClickListener(this);

        mTvTemp = (TextView) findViewById(R.id.tv_temp_val);
        // mTvMaxTemp = (TextView) findViewById(R.id.tv_temp_val);
        // mTvMinTemp = (TextView) findViewById(R.id.tv_temp_val);
        mTvRainAmount = (TextView) findViewById(R.id.tv_amount_of_rain);

        mEtLocation = (EditText) findViewById(R.id.et_location);

        allDataStr = helperMethods.readData(this);

        String[] split_data = allDataStr.split(",");

        city = split_data[7];
        state = split_data[8];

        // https://api.openweathermap.org/data/2.5/weather?zip=84025&appid=dea49d627fea00ba2da57ad036b9b61a

        String[] fullCity = null;
        String location = "";

        if (city.contains(" ")) {
            fullCity = city.split(" ");

            for(int i = 0; i < fullCity.length; i++){

                if (i > 0){
                    location= location + "+" + fullCity[i];
                }

                else {
                    location= location + fullCity[i];
                }
            }
        }

        else{ location = city; }

        location = location + ",us";

        // Begin thread here
        loadWeatherData(location);

        int i = 0;
    }

    private void loadWeatherData(String location){
        new FetchWeatherTask().execute(location);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_return:
                Intent messageIntent = new Intent(this, MainActivity.class);
                this.startActivity(messageIntent);
                break;

            case R.id.button_submit:
                String inputFromEt = mEtLocation.getText().toString().replace(
                        ' ', '&');

                loadWeatherData(inputFromEt);
        }
    }

    /*
    *
    *
    *
    *
    */
    private class FetchWeatherTask {

        public ExecutorService executorService = Executors.newSingleThreadExecutor();
        public Handler mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());

        public void execute(String location) {

            executorService.execute(() -> {

                // Toast.makeText(weather_output.this, "" + location, Toast.LENGTH_SHORT).show();

                // location = "Salt&Lake&City,us";
                URL weatherDataURL = NetworkUtils.buildURLFromString("" + location);

                String jsonWeatherData = null;

                try {
                    jsonWeatherData = NetworkUtils.getDataFromURL(weatherDataURL);
                    postToMainThread(jsonWeatherData);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }


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

                        mTvTemp.setText(String.valueOf((int) mTemp));
                    }
                }
            });
        }
    }
}







