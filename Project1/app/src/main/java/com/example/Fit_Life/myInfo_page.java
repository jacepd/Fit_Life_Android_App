package com.example.Fit_Life;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import com.example.basicinfoname.R;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ipsec.ike.exceptions.InvalidMajorVersionException;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class myInfo_page extends AppCompatActivity
        implements View.OnClickListener{

    private Button mButtonSaveReturn;
    private EditText mFirstName;
    private EditText mLastName;
    private EditText mHeightFt;
    private EditText mHeightIn;
    private EditText mWeight;
    private EditText mGoal;
    private EditText mActLvl;
    private EditText mAge;
    private EditText mSex;
    private EditText mCity;
    private EditText mState;
    private int dataSize;
    private String[] datas;
    private ImageView mProfilePic;
    Bitmap mThumbnailImage;

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
                        // String filePathString = saveImage(mThumbnailImage);
                        // mDisplayIntent.putExtra("imagePath",filePathString);
                    }
                    else{
                        Toast.makeText(myInfo_page.this,"External storage not writable.",Toast.LENGTH_SHORT).show();
                    }


                    ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                    mThumbnailImage.compress(Bitmap.CompressFormat.PNG, 100, bStream);
                    byte[] byteArray = bStream.toByteArray();


                    //Open a file and write to it
                    if(isExternalStorageWritable()){
                        String filePathString = saveImage(mThumbnailImage);
                    }
                    else {
                        Toast.makeText(myInfo_page.this, "External storage not writable.", Toast.LENGTH_SHORT).show();
                    }

                    Intent messageIntent = new Intent(myInfo_page.this, myInfo_page.class);
                    startActivity(messageIntent);
//                    }
                }
            });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info_page);

        mButtonSaveReturn = (Button) findViewById(R.id.button_return);
        mButtonSaveReturn.setOnClickListener(this);

        mProfilePic = (ImageView) findViewById(R.id.profile_photo);
        mProfilePic.setOnClickListener(this);

        mFirstName = (EditText) findViewById(R.id.firstName_input);
        mLastName = (EditText) findViewById(R.id.lastName_input);
        mHeightFt = (EditText) findViewById(R.id.heightFt_input);
        mHeightIn = (EditText) findViewById(R.id.heightIn_input);
        mWeight = (EditText) findViewById(R.id.weight_input);
        mGoal = (EditText) findViewById(R.id.goal_input);
        mActLvl = (EditText) findViewById(R.id.actLvL_input);
        mSex = (EditText) findViewById(R.id.sex_input);
        mAge = (EditText) findViewById(R.id.age_input);
        mCity = (EditText) findViewById(R.id.city_input);
        mState = (EditText) findViewById(R.id.state_input);


        String allDataStr = helperMethods.readData(this);
        datas = allDataStr.split(",");
        mFirstName.setText(datas[0]);
        mLastName.setText(datas[1]);
        mAge.setText(datas[2]);
        mWeight.setText(datas[3]);
        mHeightFt.setText(datas[4]);
        mHeightIn.setText(datas[5]);
        mSex.setText(datas[6]);
        mCity.setText(datas[7]);
        mState.setText(datas[8]);
        mGoal.setText(datas[9]);
        mActLvl.setText(datas[10]);

        dataSize = datas.length;

        File myDir = this.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        String imFilename = "profilePic.jpg";

        File imagefile = new File(myDir, imFilename);
        Bitmap bMap = BitmapFactory.decodeFile(imagefile.toString());
        mProfilePic.setImageBitmap(bMap);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.profile_photo:
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                mStartForResult.launch(cameraIntent);
                break;
            case R.id.button_return:

                //Parsing the inputted data
                String theGoal = mGoal.getText().toString();
                String theActivityLevel = mActLvl.getText().toString();
                String theSex = mSex.getText().toString();
                String theState = mState.getText().toString();
                if(!(theGoal.equals("Gain") || theGoal.equals("Lose") || theGoal.equals("Maintain") || theGoal.equals("NoGoalSelected"))){
                    Toast.makeText(myInfo_page.this, "Enter Valid Goal:\nGain, Lose, or Maintain", Toast.LENGTH_LONG).show();
                }
                else if(!(theActivityLevel.equals("Sedentary") || theActivityLevel.equals("Active") || theActivityLevel.equals("NoActivityLevelSelected"))){
                    Toast.makeText(myInfo_page.this, "Enter Valid Activity Level:\nSedentary or Active", Toast.LENGTH_LONG).show();
                }
                else if(!(theSex.equals("Male") || theSex.equals("Female"))){
                    Toast.makeText(myInfo_page.this, "Enter Valid Sex:\nMale or Female", Toast.LENGTH_LONG).show();
                }
                else if(theState.length() != 2){
                    Toast.makeText(myInfo_page.this, "Enter Valid State Abbreviation:\n2 uppercase characters", Toast.LENGTH_LONG).show();
                }
                else{
                    //save info to file
                    String[] newDatas = new String[dataSize];
                    newDatas[0] = mFirstName.getText().toString();
                    newDatas[1] = mLastName.getText().toString();
                    newDatas[2] = mAge.getText().toString(); //age
                    newDatas[3] = mWeight.getText().toString();
                    newDatas[4] = mHeightFt.getText().toString();
                    newDatas[5] = mHeightIn.getText().toString();
                    newDatas[6] = theSex;
                    newDatas[7] = mCity.getText().toString();
                    newDatas[8] = theState;
                    newDatas[9] = theGoal;
                    newDatas[10] = theActivityLevel;

                    helperMethods.saveData(newDatas,this,false);
                    Toast.makeText(myInfo_page.this, "Information Updated", Toast.LENGTH_SHORT).show();

                    Intent messageIntent = new Intent(this, MainActivity.class);
                    this.startActivity(messageIntent);
                    break;
                }

        }
    }

    private String saveImage(Bitmap finalBitmap) {

        File myDir = this.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        String fname = "profilePic.jpg";

        File file = new File(myDir, fname);
        if (file.exists()) file.delete ();
        try {
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("BackButtonPressed", true);
        this.startActivity(intent);
        finish();
    }
}