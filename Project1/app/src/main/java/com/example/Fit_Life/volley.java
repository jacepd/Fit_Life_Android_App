package com.example.Fit_Life;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageRequest;
import com.example.basicinfoname.R;

public class volley extends AppCompatActivity implements View.OnClickListener{

    private ImageView mIvFetchedImage;
    private EditText mEtURL;
    private Button mBtSubmit;
    private String mURLString;
    private ImageRequest mImageRequest;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);

        mBtSubmit = findViewById(R.id.button_submit);
        mBtSubmit.setOnClickListener(this);

        // mEtURL = findViewById(R.id.et_query);


    }

    @Override
    public void onClick(View view) {

    }
}