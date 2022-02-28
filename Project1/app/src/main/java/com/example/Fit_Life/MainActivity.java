package com.example.Fit_Life;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
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
import java.io.File;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    private Button mViewMyInfo;
    private Button mViewWeather;
    private Button mViewHikes;
    private Button mViewGoals;
    private Button mBMICalculator;
    private ImageView mProfilePic;

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
    private boolean tablet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tablet = helperMethods.isTablet(this);
        if (tablet){
            setContentView(R.layout.activity_main_tablet);
        }
        else{
            setContentView(R.layout.activity_main);
        }

        setTitle("Fit Life - Home");

        mBMICalculator = (Button) findViewById(R.id.button_BMI_Calculator);
        mBMICalculator.setOnClickListener(this);

        mViewHikes = (Button) findViewById(R.id.button_hikes);
        mViewHikes.setOnClickListener(this);

        mViewWeather = (Button) findViewById(R.id.button_weather);
        mViewWeather.setOnClickListener(this);

        mViewMyInfo = (Button) findViewById(R.id.button_view_my_info);
        mViewMyInfo.setOnClickListener(this);

        mViewGoals = (Button) findViewById(R.id.button_Fitness_Goals);
        mViewGoals.setOnClickListener(this);

        mProfilePic = (ImageView) findViewById(R.id.profile_photo);

        reloadData();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        if (tablet){
            tabletSwitchStatement(view);
        }
        else{
            phoneSwitchStatement(view);
        }
    }

    private void reloadData() {
        File myDir = this.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        String imFilename = "profilePic.jpg";

        File imagefile = new File(myDir, imFilename);
        Bitmap bMap = BitmapFactory.decodeFile(imagefile.toString());

        allDataStr = helperMethods.readData(this);
        String[] datas = allDataStr.split(",");
        firstName = datas[0];
        lastName = datas[1];
        age = Integer.parseInt(datas[2]);
        weight = Integer.parseInt(datas[3]);
        heightFeet = Integer.parseInt(datas[4]);
        heightInches = Integer.parseInt(datas[5]);
        sex = datas[6];
        city = datas[7];
        state = datas[8];
        goal = datas[9];
        activityLevel = datas[10];
        mProfilePic.setImageBitmap(bMap);
    }

    private void phoneSwitchStatement(View view) {
        Intent messageIntent;
        switch (view.getId()) {
            case R.id.button_BMI_Calculator:
                messageIntent = new Intent(this, bmi_page.class);
                this.startActivity(messageIntent);
                break;

            case R.id.button_hikes:
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("geo:0,0?q=" + city + " " + state + " " + "hikes"));
                startActivity(intent);
                break;

            case R.id.button_weather:
                messageIntent = new Intent(this, weather_output.class);
                this.startActivity(messageIntent);
                break;

            case R.id.button_view_my_info:
                messageIntent = new Intent(this, myInfo_page.class);
                this.startActivity(messageIntent);
                break;

            case R.id.button_Fitness_Goals:
                messageIntent = new Intent(this, goalsDisplay.class);
                this.startActivity(messageIntent);
                break;
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
}
