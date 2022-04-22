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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.basicinfoname.R;

public class step_counter extends AppCompatActivity {

    private SensorManager mSensorManager;
    private TextView mTvData;
    private Sensor mStepCounter;
    private int current_steps;
    private final double mThreshold = 2.0;

    //Previous positions
    private double last_x, last_y, last_z;
    private double now_x, now_y,now_z;
    private boolean mNotFirstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_step_counter);

        mTvData = (TextView) findViewById(R.id.tv_data);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mStepCounter = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
    }

    private SensorEventListener mListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            //Get the acceleration rates along the y and z axes
            now_x = sensorEvent.values[0];
            now_y = sensorEvent.values[1];
            now_z = sensorEvent.values[2];

            if(mNotFirstTime){
                double dx = Math.abs(last_x - now_x);
                double dy = Math.abs(last_y - now_y);
                double dz = Math.abs(last_z - now_z);

                //Check if the values of acceleration have changed on any pair of axes
                if( (dx > mThreshold && dy > mThreshold) ||
                        (dx > mThreshold && dz > mThreshold)||
                        (dy > mThreshold && dz > mThreshold)){

                    current_steps++;
                    mTvData.setText(current_steps);
                }
            }
            last_x = now_x;
            last_y = now_y;
            last_z = now_z;
            mNotFirstTime = true;
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
}
