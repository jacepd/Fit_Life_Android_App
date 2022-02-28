package com.example.Fit_Life;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.basicinfoname.R;
import org.json.JSONException;
import java.net.URL;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class weather_output extends AppCompatActivity{

    // API Key for Openweathermap: 01ff6680aad0a6ca59af4f7a60f42b04
    // Practice API call for London looks like:
    // http:api.openweathermap.org/data/2.5/weather?q=London,uk&appid=99ea8383701bd7481e5ea568772f739

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Fit Life - Weather");

        setContentView(R.layout.activity_weather_output);

        FragmentTransaction fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.replace(R.id.weather_activity, new WeatherFragment(),"weather_frag");
        fTrans.commit();

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("BackButtonPressed", true);
        this.startActivity(intent);
        finish();
    }
}







