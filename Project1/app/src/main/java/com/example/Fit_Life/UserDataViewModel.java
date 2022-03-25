package com.example.Fit_Life;


import android.app.Application;

        import androidx.lifecycle.AndroidViewModel;
        import androidx.lifecycle.LiveData;
        import androidx.lifecycle.MutableLiveData;


public class UserDataViewModel extends AndroidViewModel {
    private MutableLiveData<User> myUserData;
    private UserDataRepository mWeatherRepository;

    public UserDataViewModel(Application application){
        super(application);
        mWeatherRepository = UserDataRepository.getInstance(application);
        myUserData = UserDataRepository.getData();
    }
    public void setUserData(String location){
        mWeatherRepository.setUserData(location);
    }

    public LiveData<User> getData(){
        return myUserData;
    }


}