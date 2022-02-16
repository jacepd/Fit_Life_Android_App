package com.example.Fit_Life;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.basicinfoname.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EventListener;

public class profile_pic_input extends AppCompatActivity
        implements View.OnClickListener {

    private Button mButtonSubmit;
    private String mProfileStr;

    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
//                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        // Handle the Intent

                        //Get the bitmap
                        Bundle extras = intent.getExtras();
                        mThumbnailImage = (Bitmap) extras.get("data");

                    //Open a file and write to it
                    if(isExternalStorageWritable()){
                        String filePathString = saveImage(mThumbnailImage);
                        mDisplayIntent.putExtra("imagePath",filePathString);
                    }
                    else{
                        Toast.makeText(profile_pic_input.this,"External storage not writable.",Toast.LENGTH_SHORT).show();
                    }


                    ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                    mThumbnailImage.compress(Bitmap.CompressFormat.PNG, 100, bStream);
                    byte[] byteArray = bStream.toByteArray();


                    //Open a file and write to it
//                        if(isExternalStorageWritable()){
//                            String filePathString = saveImage(mThumbnailImage);
//                            mDisplayIntent.putExtra("imagePath",filePathString);
//                        }
//                        else {
//                            Toast.makeText(profile_pic_input.this, "External storage not writable.", Toast.LENGTH_SHORT).show();
//                        }

                        Intent messageIntent = new Intent(profile_pic_input.this, MainActivity.class);
                        messageIntent.putExtra("ET_STRING", mProfileStr);
                        messageIntent.putExtra("ET_IMAGE", byteArray);
                        startActivity(messageIntent);
//                    }
                }
            });


    Bitmap mThumbnailImage;
    //Define a global intent variable
    Intent mDisplayIntent;
    //Define a request code for the camera
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_pic_input);

        //Get the intent that created this activity.
        Intent receivedIntent = getIntent();

        setTitle("Fit Life App");



        //Get the string data
        mProfileStr = receivedIntent.getStringExtra("ET_STRING");

        if(mProfileStr.matches("")){
            Toast.makeText(profile_pic_input.this, "Empty string received", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(profile_pic_input.this, mProfileStr, Toast.LENGTH_SHORT).show();
        }


        //Get the button
        mButtonSubmit = (Button) findViewById(R.id.button_submit);
        mButtonSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_submit: {

                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                mStartForResult.launch(cameraIntent);
            }
        }
    }

    private String saveImage(Bitmap finalBitmap) {

        //String root = Environment.getExternalStorageDirectory().toString();
        //File myDir = new File(root + "/saved_images");
        //myDir.mkdirs();

        File myDir = new File(Environment.getExternalStorageDirectory()+File.separator+"images");
        myDir.mkdirs();

        //System.out.println(myDir.mkdirs());

        //String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        //String fname = "Thumbnail_"+ timeStamp +".jpg";
        String fname = "profilePic.jpg";

        Toast.makeText(this,"Made it here 1!",Toast.LENGTH_SHORT).show();
        File file = new File(myDir, fname);
        if (file.exists()) file.delete ();
        try {
            Toast.makeText(this,"Made it here 2!",Toast.LENGTH_SHORT).show();
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            Toast.makeText(this,"file saved!",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this,"Failed!",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        return file.getAbsolutePath();
    }

    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
}
