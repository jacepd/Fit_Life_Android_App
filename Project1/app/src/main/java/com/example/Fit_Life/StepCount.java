package com.example.Fit_Life;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.basicinfoname.R;

import java.util.ArrayList;

public class StepCount extends AppCompatActivity
        implements View.OnClickListener{

    private SensorManager mSensorManager;
    private TextView mTvData;
    private Sensor mStepCounter;
    private Button mButtonHome;
    private int numSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_count);

        mTvData = (TextView) findViewById(R.id.tv_data);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mStepCounter = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);

        mButtonHome = (Button) findViewById(R.id.button_home);
        mButtonHome.setOnClickListener(this);
    }



    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
        finish();
    }


    private SensorEventListener mListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            numSteps = (int)sensorEvent.values[0];
            mTvData.setText( Integer.toString(numSteps) );
        }


        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        if(mStepCounter!=null){
            mSensorManager.registerListener(mListener,mStepCounter,SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mStepCounter!=null){
            mSensorManager.unregisterListener(mListener);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_home:

                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("fromStepCount", numSteps);
                this.startActivity(intent);
                finish();
        }
    }
}
