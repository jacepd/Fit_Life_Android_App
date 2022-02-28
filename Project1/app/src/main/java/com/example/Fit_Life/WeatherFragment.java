package com.example.Fit_Life;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.os.HandlerCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.basicinfoname.R;

import org.json.JSONException;

import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class WeatherFragment extends Fragment {

    private EditText mEtLocation;
    private TextView mTvTemp;
    private TextView mTvPress;
    private TextView mTvHum;
    private WeatherData mWeatherData;
    private Button mButtonReturn;
    private TextView mTvMaxTemp;
    private TextView mTvMinTemp;
    private TextView mTvRainAmount;

    private double mTemp;
    private double mMaxTemp;
    private double mMinTemp;
    private String allDataStr;
    private String city;
    private String state;

    private FetchWeatherTask mFetchWeatherTask = new FetchWeatherTask();

    public WeatherFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        mButtonReturn = view.findViewById(R.id.button_return);

        mButtonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        mTvTemp = view.findViewById(R.id.tv_temp_val);
        mTvMaxTemp = view.findViewById(R.id.tv_max_temp_val);
        mTvMinTemp = view.findViewById(R.id.tv_min_temp_val);
        // mTvRainAmount = (TextView) findViewById(R.id.tv_amount_of_rain);

        mEtLocation = view.findViewById(R.id.et_location);

        allDataStr = helperMethods.readData(getContext());

        String[] split_data = allDataStr.split(",");

        city = split_data[7];
        state = split_data[8];

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

        return view;
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("tvTemp",mTvTemp.getText().toString());
        outState.putString("tvHum",mTvHum.getText().toString());
        outState.putString("tvPress",mTvPress.getText().toString());
    }

    private void loadWeatherData(String location){
      mFetchWeatherTask.execute(location);
    }
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

                        mTvTemp.setText(new StringBuilder().append(mTemp).append("F°").toString());
                        mTvMaxTemp.setText(new StringBuilder().append(mMaxTemp).append("F°").toString());
                        mTvMinTemp.setText(new StringBuilder().append(mMinTemp).append("F°").toString());


                    }
                }
            });
        }
    }
}