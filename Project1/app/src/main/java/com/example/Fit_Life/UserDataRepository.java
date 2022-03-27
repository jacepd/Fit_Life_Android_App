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
    private static final MutableLiveData<User> myUserData =
            new MutableLiveData<User>();
    private UserDao mUserDao;



    private UserDataRepository(Application application){
        UserRoomDatabase db = UserRoomDatabase.getDatabase(application);
        mUserDao = db.userDao();
//        if(myUserData!=null) {
//            loadData();
//        }
    }
    public static synchronized UserDataRepository getInstance(Application application){
        if(instance==null){
            instance = new UserDataRepository(application);
        }
        return instance;
    }

    public void setUserData(User user){
        myUserData.setValue(user);
//        loadData();
        insert();  //insert after the thread is finished
    }

    private void insert(){
        if(myUserData!=null) {
            //UserTable userTable = new UserTable(myUserData.getValue().getFirstName(), myUserData.getValue());
            UserTable userTable = new UserTable(myUserData.getValue().getFirstName(), myUserData.getValue().getLastName(),
                    myUserData.getValue().getAge(),myUserData.getValue().getWeight(),myUserData.getValue().getHeightFeet(),
                    myUserData.getValue().getHeightInches(),myUserData.getValue().getSex(),myUserData.getValue().getCity(),
                    myUserData.getValue().getState(),myUserData.getValue().getGoal(),myUserData.getValue().getActivityLevel());
            UserRoomDatabase.databaseExecutor.execute(() -> {
                mUserDao.insert(userTable);
            });
        }
    }

    public static MutableLiveData<User> getData() {
        return myUserData;
    }

//    private void loadData()
//    {
//        new FetchWeatherTask().execute(mLocation);
//    }

}
