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
import androidx.lifecycle.ViewModelProvider;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.KeyEvent;
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

    private ImageView mProfilePic;
    Bitmap mThumbnailImage;

    private UserDataViewModel mUserDataViewModel;


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

                    Intent messageIntent = new Intent(getContext(), MainActivity.class);
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_my_info_frag, container, false);

        mButtonSaveReturn = view.findViewById(R.id.button_return);

        //Create the view model
        mUserDataViewModel = new ViewModelProvider(this).get(UserDataViewModel.class);

        mProfilePic = view.findViewById(R.id.profile_photo);
        mProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                mStartForResult.launch(cameraIntent);
            }
        });

        mFirstName = view.findViewById(R.id.firstName_input);
        mLastName = view.findViewById(R.id.lastName_input);
        mHeightFt = view.findViewById(R.id.heightFt_input);
        mHeightIn = view.findViewById(R.id.heightIn_input);
        mWeight = view.findViewById(R.id.weight_input);
        mGoal = view.findViewById(R.id.goal_input);
        mActLvl = view.findViewById(R.id.actLvL_input);
        mSex = view.findViewById(R.id.sex_input);
        mAge = view.findViewById(R.id.age_input);
        mCity = view.findViewById(R.id.city_input);
        mState = view.findViewById(R.id.state_input);



        mFirstName.setText(mUserDataViewModel.getData().getValue().getFirstName());
        mLastName.setText(mUserDataViewModel.getData().getValue().getLastName() );
        mAge.setText(Integer.toString(mUserDataViewModel.getData().getValue().getAge()));
        mWeight.setText(Integer.toString(mUserDataViewModel.getData().getValue().getWeight()));
        mHeightFt.setText(Integer.toString(mUserDataViewModel.getData().getValue().getHeightFeet()));
        mHeightIn.setText(Integer.toString(mUserDataViewModel.getData().getValue().getHeightInches()));
        mSex.setText(mUserDataViewModel.getData().getValue().getSex() );
        mCity.setText(mUserDataViewModel.getData().getValue().getCity() );
        mState.setText(mUserDataViewModel.getData().getValue().getState() );
        mGoal.setText(mUserDataViewModel.getData().getValue().getGoal() );
        mActLvl.setText(mUserDataViewModel.getData().getValue().getActivityLevel() );



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
            //Toast.makeText(getContext(),"File saved!",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getContext(),"Failed!",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        return file.getAbsolutePath();
    }

    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }



    //for back button
    @Override
    public void onResume() {
        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    // handle back button's click listener
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.putExtra("nextFrag", "homePage");
                    startActivity(intent);
                    //Toast.makeText(getActivity(), "Back press", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });
    }
}