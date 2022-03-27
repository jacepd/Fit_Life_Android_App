package com.example.Fit_Life;


import android.app.Application;

        import androidx.lifecycle.AndroidViewModel;
        import androidx.lifecycle.LiveData;
        import androidx.lifecycle.MutableLiveData;


public class UserDataViewModel extends AndroidViewModel {
    private MutableLiveData<User> myUserData;
    private UserDataRepository mUserDataRepository;

    public UserDataViewModel(Application application){
        super(application);
        mUserDataRepository = UserDataRepository.getInstance(application);
        myUserData = UserDataRepository.getData();
    }

    public void setUserData(User user){
        mUserDataRepository.setUserData(user);
    }

    public LiveData<User> getData(){
        return myUserData;
    }
}