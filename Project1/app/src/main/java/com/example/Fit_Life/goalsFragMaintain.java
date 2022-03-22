package com.example.Fit_Life;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

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
public class goalsFragMaintain extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    private Button mButtonReturn;


    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    public goalsFragMaintain() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment goalsFragMaintain.
     */
    // TODO: Rename and change types and number of parameters
    public static goalsFragMaintain newInstance(String param1, String param2) {
        goalsFragMaintain fragment = new goalsFragMaintain();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_goals_frag_maintain, container, false);

        TextView mCaloriesNeeded = (TextView) view.findViewById(R.id.calories);
        int myCalories =(int) getCalories();
        mCaloriesNeeded.setText(String.valueOf(myCalories));

        mButtonReturn = (Button) view.findViewById(R.id.button_return);
//        mButtonReturn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), MainActivity.class);
//                startActivity(intent);
//            }
//        });

        // Inflate the layout for this fragment
        return view;
    }


    private double getCalories(){
        String allDataStr = helperMethods.readData(getContext());

        String[] datas = allDataStr.split(",");

        int age = Integer.parseInt(datas[2]);
        int weight = Integer.parseInt(datas[3]);
        int heightFeet = Integer.parseInt(datas[4]);
        int heightInches = Integer.parseInt(datas[5]);
        String sex = datas[6];
        String activityLevel = datas[10];

        double multiplayer;
        if(activityLevel.equals("Sedentary")){
            multiplayer = 1;
        }
        else{
            multiplayer = 1.4;
        }

        int totalHeight = (heightFeet * 12) + heightInches;

        double myBMR;
        if (sex.equals("Male")){
            myBMR = 66 + (6.23 * weight) + (12.7 * totalHeight) - (6.8 * age);
        }
        else{
            myBMR = 655 + (4.35 * weight) + (4.7 * totalHeight) -(4.7 * age);
        }

        double calories = myBMR * multiplayer;

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
                    Toast.makeText(getActivity(), "Back press", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });

    }
}