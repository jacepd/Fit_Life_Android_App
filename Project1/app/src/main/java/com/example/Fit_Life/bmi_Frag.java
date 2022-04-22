package com.example.Fit_Life;

import android.content.Intent;
import android.graphics.Color;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.basicinfoname.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link bmi_Frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class bmi_Frag extends Fragment {
    private Button mButtonReturn;
    private UserDataViewModel mUserDataViewModel;

    TextView mBMI_type;
    TextView mBMI_val;

    int weight;
    int heightFeet;
    int heightInches;

    public bmi_Frag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment bmi_Frag.
     */
    // TODO: Rename and change types and number of parameters
    public static bmi_Frag newInstance(String param1, String param2) {
        bmi_Frag fragment = new bmi_Frag();
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
        View view = inflater.inflate(R.layout.fragment_bmi, container, false);

        //Get the button
        mButtonReturn = (Button) view.findViewById(R.id.button_return);
        //the onClick is hardcoded in the XML, and taken care of the activity

        mBMI_val = (TextView) view.findViewById(R.id.bmi_val);
        mBMI_type = (TextView) view.findViewById(R.id.bmi_type);


        //Create the view model
        mUserDataViewModel = new ViewModelProvider(this).get(UserDataViewModel.class);

        //Set the observer
        (mUserDataViewModel.getData()).observe(getViewLifecycleOwner(),nameObserver);


        return view;
    }





    final Observer<User> nameObserver  = new Observer<User>() {
        @Override
        public void onChanged(@Nullable final User myUser) {
            // Update the UI if this data variable changes
            if(myUser!=null) {
                weight = myUser.getWeight();
                heightFeet = myUser.getHeightFeet();
                heightInches = myUser.getHeightInches();

                double tHeight = heightFeet * 12;
                tHeight += heightInches;
                double result = (weight / (tHeight * tHeight)) * 703;
                // Tested the BMI output from the round helper method
                result = helperMethods.round(result, 1);

                mBMI_val.setText(Double.toString(result));
                if (result < 18.5) {
                    mBMI_type.setText(" UNDERWEIGHT ");
                    mBMI_val.setTextColor(Color.parseColor("#42b0f5"));
                    mBMI_type.setTextColor(Color.parseColor("#42b0f5"));
                } else if (result <= 24.9) {
                    mBMI_type.setText(" NORMAL ");
                    mBMI_val.setTextColor(Color.parseColor("#07eb5e"));
                    mBMI_type.setTextColor(Color.parseColor("#07eb5e"));
                } else if (result <= 29.9) {
                    mBMI_type.setText(" OVERWEIGHT ");
                    mBMI_val.setTextColor(Color.parseColor("#d4eb07"));
                    mBMI_type.setTextColor(Color.parseColor("#d4eb07"));
                } else if (result <= 34.9) {
                    mBMI_type.setText(" OBESE ");
                    mBMI_val.setTextColor(Color.parseColor("#f58607"));
                    mBMI_type.setTextColor(Color.parseColor("#f58607"));
                } else {
                    mBMI_type.setText(" EXTREMELY OBESE ");
                    mBMI_val.setTextColor(Color.parseColor("#f51707"));
                    mBMI_type.setTextColor(Color.parseColor("#f51707"));
                }
            }
        }
    };

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