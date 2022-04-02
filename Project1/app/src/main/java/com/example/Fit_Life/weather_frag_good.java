package com.example.Fit_Life;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.os.HandlerCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.basicinfoname.R;

import org.json.JSONException;

import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link weather_frag_good#newInstance} factory method to
 * create an instance of this fragment.
 */
public class weather_frag_good extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView mTvTemp;
    private TextView mTvMaxTemp;
    private TextView mTvMinTemp;
    private TextView mTvCondition;
    private TextView mTvRainAmount;
    private TextView mTvPress;
    private TextView mTvHum;

    private double mTemp;
    private double mMaxTemp;
    private double mMinTemp;
    private String mCondition;

    private TextView mEtLocation;
    private Button mButtonReturn;
    private String allDataStr;
    private String city;
    private WeatherData mWeatherData;

    private UserDataViewModel mUserDataViewModel;

    private static weather_frag_good.FetchWeatherTask mFetchWeatherTask = new weather_frag_good.FetchWeatherTask();

    public weather_frag_good() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment weather_frag_good.
     */
    // TODO: Rename and change types and number of parameters
    public static weather_frag_good newInstance(String param1, String param2) {
        weather_frag_good fragment = new weather_frag_good();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather_frag_good, container, false);

        //Create the view model
        mUserDataViewModel = new ViewModelProvider(this).get(UserDataViewModel.class);
        //Set the observer
        (mUserDataViewModel.getData()).observe(getViewLifecycleOwner(),weatherObserver);

        mButtonReturn = view.findViewById(R.id.button_return_weather);
        //the onClick is hardcoded in the XML, and taken care of the activity

        mTvTemp = view.findViewById(R.id.tv_temp_val);
        mTvMaxTemp = view.findViewById(R.id.tv_max_temp_val);
        mTvMinTemp = view.findViewById(R.id.tv_min_temp_val);
        mTvCondition = view.findViewById(R.id.tv_condition_val);
        // mTvRainAmount = (TextView) findViewById(R.id.tv_amount_of_rain);
        mEtLocation = view.findViewById(R.id.et_location);
        //mEtLocation.setText(UserDataViewModel.getData().getValue().getCity());

        allDataStr = helperMethods.readData(getContext());

        String[] split_data = allDataStr.split(",");

        city = mUserDataViewModel.getData().getValue().getCity();

        String[] fullCity = null;
        String location = "";

        if (city.contains(" ")) {
            fullCity = city.split(" ");

            for(int i = 0; i < fullCity.length; i++){
                if (i > 0) location = location + "+" + fullCity[i];
                else location = location + fullCity[i];
            }
        }
        else location = city;
        location = location + ",us";

        mFetchWeatherTask.setWeakReference(this);

        // Begin thread here
        loadWeatherData(location);
        return view;
    }


    //create an observer that watches the LiveData<WeatherData> object
    final Observer<User> weatherObserver  = new Observer<User>() {
        @Override
        public void onChanged(@Nullable final User myUser) {
            // Update the UI if this data variable changes
            if(myUser!=null) {
                mEtLocation.setText(myUser.getCity());
            }
        }
    };

    private void loadWeatherData(String location){
        mFetchWeatherTask.execute(location);
    }

    private static class FetchWeatherTask {

        public ExecutorService executorService = Executors.newSingleThreadExecutor();
        public Handler mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());

        WeakReference<weather_frag_good> weatherFragGoodWeakReference;

        public void execute(String location) {

            executorService.execute(() -> {

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

            weather_frag_good localRef = weatherFragGoodWeakReference.get();
            mainThreadHandler.post(() -> {

                if (jsonWeatherData != null) {

                    try { localRef.mWeatherData = JSONWeatherUtils.getWeatherData(jsonWeatherData); }
                    catch (JSONException e) { e.printStackTrace(); }

                    if (localRef.mWeatherData != null) {
                        localRef.mTemp = localRef.mWeatherData.getTemperature().getTemp();
                        localRef.mMaxTemp = localRef.mWeatherData.getTemperature().getMaxTemp();
                        localRef.mMinTemp = localRef.mWeatherData.getTemperature().getMinTemp();
                        localRef.mCondition = localRef.mWeatherData.getCurrentCondition().getCondition();

                        localRef.mTvTemp.setText(new StringBuilder().append(localRef.mTemp).append("F°").toString());
                        localRef.mTvMaxTemp.setText(new StringBuilder().append(localRef.mMaxTemp).append("F°").toString());
                        localRef.mTvMinTemp.setText(new StringBuilder().append(localRef.mMinTemp).append("F°").toString());
                        localRef.mTvCondition.setText(new StringBuilder().append(localRef.mCondition).toString());

                    }
                }
            });
        }

        public void setWeakReference(weather_frag_good ref) {
            weatherFragGoodWeakReference = new WeakReference<weather_frag_good>(ref);
        }
    }
    
    @Override
    public void onResume() {
        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    // handle back button's click listener
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.putExtra("nextFrag", "ahh");
                    startActivity(intent);
                    Toast.makeText(getActivity(), "Back press", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });

    }
}