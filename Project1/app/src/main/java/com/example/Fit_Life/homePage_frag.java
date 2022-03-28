package com.example.Fit_Life;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Environment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.basicinfoname.R;

import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link homePage_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class homePage_frag extends Fragment {

    private Button mViewMyInfo;
    private Button mViewWeather;
    private Button mViewHikes;
    private Button mViewGoals;
    private Button mBMICalculator;
    private ImageView mProfilePic;

    private String allDataStr;
    private String firstName;
    private String lastName;
    private int age;
    private int weight;
    private int heightFeet;
    private int heightInches;
    private String sex;
    private String city;
    private String state;
    private String goal;
    private String activityLevel;


    public homePage_frag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment homePage_frag.
     */
    // TODO: Rename and change types and number of parameters
    public static homePage_frag newInstance(String param1, String param2) {
        homePage_frag fragment = new homePage_frag();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_page_frag, container, false);




        mBMICalculator = (Button) view.findViewById(R.id.button_BMI_Calculator);
        mBMICalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("nextFrag", "BMI");
                startActivity(intent);
            }
        });

        mViewHikes = (Button) view.findViewById(R.id.button_hikes);
        mViewHikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("nextFrag", "hikes");
                startActivity(intent);
            }
        });

        mViewWeather = (Button) view.findViewById(R.id.button_weather);
        mViewWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("nextFrag", "weather");
                startActivity(intent);
            }
        });


        mViewMyInfo = (Button) view.findViewById(R.id.button_view_my_info);
        mViewMyInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("nextFrag", "info");
                startActivity(intent);
            }
        });


        mViewGoals = (Button) view.findViewById(R.id.button_Fitness_Goals);
        mViewGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("nextFrag", "goals");
                startActivity(intent);
            }
        });

        mProfilePic = (ImageView) view.findViewById(R.id.profile_photo);

        reloadData();
        return view;
    }


    private void reloadData() {
        File myDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        String imFilename = "profilePic.jpg";

        File imagefile = new File(myDir, imFilename);
        Bitmap bMap = BitmapFactory.decodeFile(imagefile.toString());

        allDataStr = helperMethods.readData(getActivity());
        String[] datas = allDataStr.split(",");
        firstName = datas[0];
        lastName = datas[1];
        age = Integer.parseInt(datas[2]);
        weight = Integer.parseInt(datas[3]);
        heightFeet = Integer.parseInt(datas[4]);
        heightInches = Integer.parseInt(datas[5]);
        sex = datas[6];
        city = datas[7];
        state = datas[8];
        goal = datas[9];
        activityLevel = datas[10];
        mProfilePic.setImageBitmap(bMap);
    }

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
                    intent.putExtra("nextFrag", "ahh");
                    startActivity(intent);
                    Toast.makeText(getActivity(), "Back press", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });

    }


}