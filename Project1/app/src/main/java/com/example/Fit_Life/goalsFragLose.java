package com.example.Fit_Life;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.basicinfoname.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link goalsFragLose#newInstance} factory method to
 * create an instance of this fragment.
 */
public class goalsFragLose extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    private NumberPicker mPoundsNumberPicker;
    private int selectedPounds;
    private Button mButtonReturn;
    private UserDataViewModel mUserDataViewModel;
    private int mAge;
    private int mWeight;
    private int mHeightFeet;
    private int mHeightInches;
    private String mSex;
    private String mActivityLevel;
    private TextView mCaloriesNeeded;


    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    public goalsFragLose() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment goalsFragLose.
     */
    // TODO: Rename and change types and number of parameters
    public static goalsFragLose newInstance(String param1, String param2) {
        goalsFragLose fragment = new goalsFragLose();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_goals_frag_lose, container, false);

        mCaloriesNeeded = (TextView) view.findViewById(R.id.calories);

        mButtonReturn = (Button) view.findViewById(R.id.button_return);

        //Create the view model
        mUserDataViewModel = new ViewModelProvider(this).get(UserDataViewModel.class);

        //Set the observer
        (mUserDataViewModel.getData()).observe(getViewLifecycleOwner(),goalsObserver);



        mPoundsNumberPicker = (NumberPicker) view.findViewById(R.id.poundsNumberPicker);
        mPoundsNumberPicker.setMinValue(1);
        mPoundsNumberPicker.setMaxValue(4);
        mPoundsNumberPicker.setValue(1);
        selectedPounds = mPoundsNumberPicker.getValue();
        mPoundsNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
                selectedPounds = newVal;
                int myCalories =(int) getCalories();
                mCaloriesNeeded.setText(String.valueOf(myCalories));

                if (newVal > 2) {
                    Toast.makeText(getActivity(), "Careful, you're trying to lose more than 2 lbs this week", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    final Observer<User> goalsObserver  = new Observer<User>() {
        @Override
        public void onChanged(@Nullable final User myUser) {
            // Update the UI if this data variable changes
            if(myUser!=null) {
                mAge = myUser.getAge();
                mWeight = myUser.getWeight();
                mHeightFeet = myUser.getHeightFeet();
                mHeightInches = myUser.getHeightInches();
                mSex = myUser.getSex();
                mActivityLevel = myUser.getActivityLevel();
                mCaloriesNeeded.setText(String.valueOf((int) getCalories()));
            }
        }
    };




    private double getCalories(){
        double multiplayer;
        if(mActivityLevel.equals("Sedentary")){
            multiplayer = 1;
        }
        else{
            multiplayer = 1.4;
        }

        int totalHeight = (mHeightFeet * 12) + mHeightInches;

        double myBMR;
        if (mSex.equals("Male")){
            myBMR = 66 + (6.23 * mWeight) + (12.7 * totalHeight) - (6.8 * mAge);
        }
        else{
            myBMR = 655 + (4.35 * mWeight) + (4.7 * totalHeight) -(4.7 * mAge);
        }

        double calories = myBMR * multiplayer;
        calories -= (500 * selectedPounds);

        return calories;
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
                    //Toast.makeText(getActivity(), "Back press", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });

    }
}