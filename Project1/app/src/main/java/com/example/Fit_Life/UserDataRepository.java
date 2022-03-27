package com.example.Fit_Life;


import android.app.Application;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import androidx.core.os.HandlerCompat;
import androidx.lifecycle.MutableLiveData;

import org.json.JSONException;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserDataRepository {
    private static UserDataRepository instance;
    private final MutableLiveData<User> myUserData =
            new MutableLiveData<User>();
    //private String mLocation;
    private String mJsonString;
    private UserDao mUserDao;



    private UserDataRepository(Application application){
        UserRoomDatabase db = UserRoomDatabase.getDatabase(application);
        mUserDao = db.userDao();
        if(mLocation!=null) {
            loadData();
        }
    }
    public static synchronized UserDataRepository getInstance(Application application){
        if(instance==null){
            instance = new UserDataRepository(application);
        }
        return instance;
    }

    public void setUserData(String location){
        mLocation = location;
        loadData();
        insert();  //insert after the thread is finished
    }

    private void insert(){
        if(mLocation!=null && mJsonString!=null) {
            UserTable userTable = new UserTableBuilder().setLocation(mLocation).setWeatherJson(mJsonString).createWeatherTable();
            UserRoomDatabase.databaseExecutor.execute(() -> {
                mUserDao.insert(userTable);
            });
        }
    }

    public MutableLiveData<User> getData() {
        return myUserData;
    }

    private void loadData()
    {
        new FetchWeatherTask().execute(mLocation);
    }

    private class FetchWeatherTask{
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());
        public void execute(String location)
        {
            executorService.execute(() -> {
                String jsonWeatherData;
                URL weatherDataURL = NetworkUtils.buildURLFromString(location);
                jsonWeatherData = null;
                try{
                    jsonWeatherData = NetworkUtils.getDataFromURL(weatherDataURL);
                    if(jsonWeatherData != null) {
                        mJsonString = jsonWeatherData;
                        postToMainThread(jsonWeatherData);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            });
        }

        private void postToMainThread(String retrievedJsonData){
            mainThreadHandler.post(() -> {
                try {
                    jsonData.setValue(JSONWeatherUtils.getWeatherData(retrievedJsonData));
                }catch(JSONException e){
                    e.printStackTrace();
                }
            });
        }
    }
}
