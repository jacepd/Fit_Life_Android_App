package com.example.Fit_Life;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.basicinfoname.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link myInfo_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class myInfo_frag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
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

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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

                    ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                    mThumbnailImage.compress(Bitmap.CompressFormat.PNG, 100, bStream);
                    byte[] byteArray = bStream.toByteArray();


                    //Open a file and write to it
                    if(isExternalStorageWritable()){
                        String filePathString = saveImage(mThumbnailImage);
                    }
                    else {
                        Toast.makeText(getContext(), "External storage not writable.", Toast.LENGTH_SHORT).show();
                    }

                    Intent messageIntent = new Intent(getContext(), myInfo_page.class);
                    startActivity(messageIntent);
                }
            });


    public myInfo_frag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment myInfo_frag.
     */
    // TODO: Rename and change types and number of parameters
    public static myInfo_frag newInstance(String param1, String param2) {
        myInfo_frag fragment = new myInfo_frag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_my_info_frag, container, false);


        mButtonSaveReturn = (Button) view.findViewById(R.id.button_return);
        mButtonSaveReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Parsing the inputted data
                String theGoal = mGoal.getText().toString();
                String theActivityLevel = mActLvl.getText().toString();
                String theSex = mSex.getText().toString();
                String theState = mState.getText().toString();
                if(!(theGoal.equals("Gain") || theGoal.equals("Lose") || theGoal.equals("Maintain") || theGoal.equals("NoGoalSelected"))){
                    Toast.makeText(getContext(), "Enter Valid Goal:\nGain, Lose, or Maintain", Toast.LENGTH_LONG).show();
                }
                else if(!(theActivityLevel.equals("Sedentary") || theActivityLevel.equals("Active") || theActivityLevel.equals("NoActivityLevelSelected"))){
                    Toast.makeText(getContext(), "Enter Valid Activity Level:\nSedentary or Active", Toast.LENGTH_LONG).show();
                }
                else if(!(theSex.equals("Male") || theSex.equals("Female"))){
                    Toast.makeText(getContext(), "Enter Valid Sex:\nMale or Female", Toast.LENGTH_LONG).show();
                }
                else if(theState.length() != 2){
                    Toast.makeText(getContext(), "Enter Valid State Abbreviation:\n2 uppercase characters", Toast.LENGTH_LONG).show();
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

                    helperMethods.saveData(newDatas,getContext(),false);
                    Toast.makeText(getContext(), "Information Updated", Toast.LENGTH_SHORT).show();

                    Intent messageIntent = new Intent(getContext(), MainActivity.class);
                    getContext().startActivity(messageIntent);
                }
            }
        });

        mProfilePic = (ImageView) view.findViewById(R.id.profile_photo);
        mProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                mStartForResult.launch(cameraIntent);
            }
        });

        mFirstName = (EditText) view.findViewById(R.id.firstName_input);
        mLastName = (EditText) view.findViewById(R.id.lastName_input);
        mHeightFt = (EditText) view.findViewById(R.id.heightFt_input);
        mHeightIn = (EditText) view.findViewById(R.id.heightIn_input);
        mWeight = (EditText) view.findViewById(R.id.weight_input);
        mGoal = (EditText) view.findViewById(R.id.goal_input);
        mActLvl = (EditText) view.findViewById(R.id.actLvL_input);
        mSex = (EditText) view.findViewById(R.id.sex_input);
        mAge = (EditText) view.findViewById(R.id.age_input);
        mCity = (EditText) view.findViewById(R.id.city_input);
        mState = (EditText) view.findViewById(R.id.state_input);

        String allDataStr = helperMethods.readData(getContext());
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

        File myDir = getContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        String imFilename = "profilePic.jpg";

        File imagefile = new File(myDir, imFilename);
        Bitmap bMap = BitmapFactory.decodeFile(imagefile.toString());
        mProfilePic.setImageBitmap(bMap);

        return view;
    }

    private String saveImage(Bitmap finalBitmap) {

        File myDir = getContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        String fname = "profilePic.jpg";

        File file = new File(myDir, fname);
        if (file.exists()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            Toast.makeText(getContext(),"File saved!",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getContext(),"Failed!",Toast.LENGTH_SHORT).show();
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