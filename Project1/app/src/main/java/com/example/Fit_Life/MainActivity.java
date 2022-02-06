package com.example.Fit_Life;

import androidx.appcompat.app.AppCompatActivity;
import com.example.basicinfoname.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    private Button mViewMyInfo;
    private Button mViewWeather;
    private Button mViewHikes;

    private String allDatastr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the intent that created this activity.
        Intent receivedIntent = getIntent();

        setTitle("Fit Life - Home");

        allDatastr = receivedIntent.getStringExtra("ET_STRING");


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // case R.id.:
            // case default:
        }
    }
}