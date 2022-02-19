package com.example.Fit_Life;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.basicinfoname.R;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    private Button mViewMyInfo;
    private Button mViewWeather;
    private Button mViewHikes;
    private Button mViewGoals;
    private Button mBMICalculator;
    private ImageView mProfilePic;

    private String allDatastr;
    private Bitmap image;


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
        String fname = "profilePic.jpg";

        File file = new File(myDir, fname);
        Bitmap bMap = BitmapFactory.decodeFile(file.toString());

        mProfilePic.setImageBitmap(bMap);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_BMI_Calculator:
                String[] data = allDatastr.split(" ");
                double weight = Double.parseDouble(data[3]);
                double height = Double.parseDouble(data[4]) * 12;
                double result = (weight / (height * height)) * 703;
                Toast.makeText(this, "BMI: " + result, Toast.LENGTH_SHORT).show();
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
                messageIntent.putExtra("ET_STRING", allDatastr);
                this.startActivity(messageIntent);

                break;
        }
    }
}