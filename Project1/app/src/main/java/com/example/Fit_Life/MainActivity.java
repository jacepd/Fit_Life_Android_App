package com.example.Fit_Life;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.basicinfoname.R;
import com.google.android.gms.common.util.IOUtils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Dictionary;

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
    private String latitude;
    private String longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        //This takes the picture from the recieved intent, but now commented out. Now it and takes
        //the picture from the saved file.

        // Get the intent that created this activity.
        //Intent receivedIntent = getIntent();
        //allDatastr = receivedIntent.getStringExtra("ET_STRING");
        //byte[] byteArray = getIntent().getByteArrayExtra("ET_IMAGE");
        //image = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        //mProfilePic.setImageBitmap(image);

        File myDir = this.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        String imFilename = "profilePic.jpg";

        File imagefile = new File(myDir, imFilename);
        Bitmap bMap = BitmapFactory.decodeFile(imagefile.toString());

        String fname = "userData.txt";
        File userInfoFile = new File(myDir, fname);

        allDataStr = helperMethods.readData(userInfoFile);

        String[] datas = allDataStr.split(",");
        firstName = datas[0];
        lastName = datas[1];
        age = Integer.parseInt(datas[2]);
        weight = Integer.parseInt(datas[3]);
        heightFeet = Integer.parseInt(datas[4]);
        heightInches = Integer.parseInt(datas[5]);
        sex = datas[6];
        latitude = datas[7];
        longitude = datas[8];
        mProfilePic.setImageBitmap(bMap);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_BMI_Calculator:
                double tHeight = heightFeet * 12;
                tHeight += heightInches;
                double result = (weight / (tHeight * tHeight)) * 703;
                result = helperMethods.round(result,1);
                Toast.makeText(this, "BMI: " + result, Toast.LENGTH_SHORT).show();

                Intent messageIntentBMI = new Intent(this, weather_output.class);
                messageIntentBMI.putExtra("BMI", result);
                this.startActivity(messageIntentBMI);

                break;

            case R.id.button_hikes:

                //We have to grab the search term and construct a URI object from it.
                //pull location from file

                Uri searchUri = Uri.parse("geo:40.767778,-111.845205?q=" + "hikes");

                //Create the implicit intent
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, searchUri);
                startActivity(mapIntent);
                break;
            case R.id.button_weather:

                Intent messageIntent = new Intent(this, weather_output.class);
                messageIntent.putExtra("ET_STRING", allDataStr);
                this.startActivity(messageIntent);

                break;
        }
    }
}
