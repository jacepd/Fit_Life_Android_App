package com.example.Fit_Life;

import androidx.appcompat.app.AppCompatActivity;
import com.example.basicinfoname.R;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    private Button mViewMyInfo;
    private Button mViewWeather;
    private Button mViewHikes;
    private Button mBMICalculator;
    private ImageView mProfilePic;

    private String allDatastr;
    private Bitmap image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the intent that created this activity.
        Intent receivedIntent = getIntent();

        setTitle("Fit Life - Home");

        allDatastr = receivedIntent.getStringExtra("ET_STRING");
        byte[] byteArray = getIntent().getByteArrayExtra("ET_IMAGE");
        image = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        mProfilePic = (ImageView) findViewById(R.id.profile_photo);
        mProfilePic.setImageBitmap(image);

        mBMICalculator = (Button) findViewById(R.id.button_BMI_Calculator);
        mBMICalculator.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        System.out.println("Made it");
        switch (view.getId()) {
            case R.id.button_BMI_Calculator:
                String[] data = allDatastr.split(" ");
                double weight = Double.parseDouble(data[3]);
                double height = Double.parseDouble(data[4]) * 12;
                double result = (weight / (height * height)) * 703;
                Toast.makeText(this, "BMI: " + result, Toast.LENGTH_SHORT).show();
            // case default:
        }
    }
}