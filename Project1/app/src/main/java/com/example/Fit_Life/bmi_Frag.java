package com.example.Fit_Life;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.basicinfoname.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link bmi_Frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class bmi_Frag extends Fragment {
    private Button mButtonReturn;

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
        mButtonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        TextView mBMI_val = (TextView) view.findViewById(R.id.bmi_val);
        TextView mBMI_type = (TextView) view.findViewById(R.id.bmi_type);

        String allDataStr = helperMethods.readData(getContext());
        String[] datas = allDataStr.split(",");
        double weight = Double.parseDouble(datas[3]);
        double heightFeet = Double.parseDouble(datas[4]);
        double heightInches = Double.parseDouble(datas[5]);
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

        return view;
    }
}