package com.example.Fit_Life;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.basicinfoname.R;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    private boolean tablet;
    private String fragID;
    private String city ;
    private String state;
    private String goal ;

    private Button mViewMyInfo;
    private Button mViewWeather;
    private Button mViewHikes;
    private Button mViewGoals;
    private Button mBMICalculator;
    private ImageView mProfilePic;

    private UserDataViewModel mUserDataViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        temp_loadSomeData();
        setTitle("Fit Life - Home");

        Intent intent = getIntent();

        //we do this because intents that come back from google maps or camera don't have the intent, so it crashes
        if (intent.hasExtra("nextFrag")) {
            fragID = intent.getStringExtra("nextFrag");
        }
        else {
            fragID = "homePage";
        }


        //Create the view model
        mUserDataViewModel = new ViewModelProvider(this).get(UserDataViewModel.class);

        //Set the observer
        (mUserDataViewModel.getData()).observe(this,nameObserver);



        tablet = helperMethods.isTablet(this);
        if (tablet) {
            setContentView(R.layout.activity_main_tablet);
            //We need these for the tablet
            mViewMyInfo = (Button) findViewById(R.id.button_view_my_info);
            mViewMyInfo.setOnClickListener(this);

            mViewWeather = (Button) findViewById(R.id.button_weather);
            mViewWeather.setOnClickListener(this);

            mViewHikes = (Button) findViewById(R.id.button_hikes);
            mViewHikes.setOnClickListener(this);

            mBMICalculator = (Button) findViewById(R.id.button_BMI_Calculator);
            mBMICalculator.setOnClickListener(this);

            mViewGoals = (Button) findViewById(R.id.button_Fitness_Goals);
            mViewGoals.setOnClickListener(this);


            mProfilePic = (ImageView) findViewById(R.id.profile_photo);
            temp_loadProfilePic();
        }
        else {
            setContentView(R.layout.activity_main);
            phoneSwitchStatement();
        }
    }

    //create an observer that watches the LiveData<WeatherData> object
    final Observer<User> nameObserver  = new Observer<User>() {
        @Override
        public void onChanged(@Nullable final User myUser) {
            // Update the UI if this data variable changes
            if(myUser!=null) {
                mTvTemp.setText("" + Math.round(weatherData.getTemperature().getTemp() - 273.15) + " C");
                mTvHum.setText("" + weatherData.getCurrentCondition().getHumidity() + "%");
                mTvPress.setText("" + weatherData.getCurrentCondition().getPressure() + " hPa");
            }
        }
    };


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        if (tablet) {
            tabletSwitchStatement(view);
        }
        else {
            phoneSwitchStatement();
        }
    }


    private void phoneSwitchStatement() {
        FragmentTransaction fTrans = getSupportFragmentManager().beginTransaction();
        switch (fragID) {
            case "BMI":
                setTitle("Fit Life - BMI");
                fTrans.replace(R.id.main_activity, new bmi_Frag(),"bmi_frag");
                fTrans.commit();
                break;
            case "hikes":
                Intent my_intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("geo:0,0?q=" + city + " " + state + " " + "hikes"));
                startActivity(my_intent);
                break;
            case "weather":
                setTitle("Fit Life - Weather");
                fTrans.replace(R.id.main_activity, new weather_frag_good(),"weather_frag");
                fTrans.commit();
                break;
            case "info":
                setTitle("Fit Life - User Info");
                fTrans.replace(R.id.main_activity, new myInfo_frag(),"info_frag");
                fTrans.commit();
                break;
            case "goals":
                setTitle("Fit Life - Goals");
                if(goal.equals("Gain")){
                    fTrans.replace(R.id.main_activity, new goalsFragGain(),"Frag_Gain");
                }
                else if(goal.equals("Lose")){
                    fTrans.replace(R.id.main_activity, new goalsFragLose(),"Frag_Lose");
                }
                else{
                    fTrans.replace(R.id.main_activity, new goalsFragMaintain(),"Frag_Maintain");
                }
                fTrans.commit();
                break;
            default:
                fTrans.replace(R.id.main_activity, new homePage_frag(),"homePage_frag");
                fTrans.commit();

        }
    }

    private void tabletSwitchStatement(View view) {
        FragmentTransaction fTrans = getSupportFragmentManager().beginTransaction();

        switch (view.getId()) {
            case R.id.button_BMI_Calculator:
                setTitle("Fit Life - BMI");
                fTrans.replace(R.id.displayActivity, new bmi_Frag(),"bmi_frag");
                fTrans.commit();
                break;

            case R.id.button_hikes:
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("geo:0,0?q=" + city + " " + state + " " + "hikes"));
                startActivity(intent);
                break;

            case R.id.button_weather:
                setTitle("Fit Life - Weather");
                fTrans.replace(R.id.displayActivity, new weather_frag_good(),"weather_frag");
                fTrans.commit();
                break;

            case R.id.button_view_my_info:
                setTitle("Fit Life - User Info");
                fTrans.replace(R.id.displayActivity, new myInfo_frag(),"info_frag");
                fTrans.commit();
                break;

            case R.id.button_Fitness_Goals:
                setTitle("Fit Life - Goals");
                if(goal.equals("Gain")){
                    fTrans.replace(R.id.displayActivity, new goalsFragGain(),"Frag_Gain");
                }
                else if(goal.equals("Lose")){
                    fTrans.replace(R.id.displayActivity, new goalsFragLose(),"Frag_Lose");
                }
                else{
                    fTrans.replace(R.id.displayActivity, new goalsFragMaintain(),"Frag_Maintain");
                }
                fTrans.commit();
                break;
        }
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("BackButtonPressed", true);
        this.startActivity(intent);
        finish();
    }



    private void temp_loadSomeData() {
        File myDir = this.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        String imFilename = "profilePic.jpg";

//        File imagefile = new File(myDir, imFilename);
//        Bitmap bMap = BitmapFactory.decodeFile(imagefile.toString());
//
        String allDataStr = helperMethods.readData(this);
        String[] datas = allDataStr.split(",");
//        firstName = datas[0];
//        lastName = datas[1];
//        age = Integer.parseInt(datas[2]);
//        weight = Integer.parseInt(datas[3]);
//        heightFeet = Integer.parseInt(datas[4]);
//        heightInches = Integer.parseInt(datas[5]);
//        sex = datas[6];
//
//
//        activityLevel = datas[10];
//        mProfilePic.setImageBitmap(bMap);

        city = datas[7];
        state = datas[8];
        goal = datas[9];
    }



    private void temp_loadProfilePic() {
        File myDir = this.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        String imFilename = "profilePic.jpg";

        File imagefile = new File(myDir, imFilename);
        Bitmap bMap = BitmapFactory.decodeFile(imagefile.toString());

        mProfilePic.setImageBitmap(bMap);
    }


    //This is the function that gets called when any of the activity's fragments push the
    //home button. This is hardcoded in the Fragment's XML
    public void HomeButtonClicked(View v) {
        //Toast.makeText(this, "HOME", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("nextFrag", "homePage");
        startActivity(intent);
    }
}