package com.example.Fit_Life;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.basicinfoname.R;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;

//https://www.youtube.com/watch?v=dWq5CJDBDVE for the number picker

public class body_info_input extends AppCompatActivity
        implements View.OnClickListener{

    private Button mButtonSubmit;
    private EditText mDatePicker;

    private NumberPicker mWeightNumberPicker;
    private NumberPicker mHeightFeetNumberPicker;
    private NumberPicker mHeightInchesNumberPicker;
    private NumberPicker mSexNumberPicker;

    private int selectedAge;
    private int selectedHeightFeet;
    private int selectedHeightInches;
    private int selectedWeight;
    private String selectedSex;

    private String [] sexArray;

    private boolean cameFromBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_info_input);

        setTitle("Fit Life App - Body Info");

        //Get the button
        mButtonSubmit = (Button) findViewById(R.id.button_submit);
        mButtonSubmit.setOnClickListener(this);

        mDatePicker = (EditText) findViewById(R.id.datePicker);
        mDatePicker.setInputType(InputType.TYPE_NULL);
        mDatePicker.setOnClickListener(this);

        //weight number picker
        mWeightNumberPicker = (NumberPicker) findViewById(R.id.weightNumberPicker);
        mWeightNumberPicker.setMinValue(10);
        mWeightNumberPicker.setMaxValue(500);
        mWeightNumberPicker.setValue(200);
        selectedWeight = mWeightNumberPicker.getValue();
        mWeightNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
                selectedWeight = newVal;
            }
        });

        //height feet number picker
        mHeightFeetNumberPicker = (NumberPicker) findViewById(R.id.heightFeetNumberPicker);
        mHeightFeetNumberPicker.setMinValue(1);
        mHeightFeetNumberPicker.setMaxValue(9);
        mHeightFeetNumberPicker.setValue(6);
        selectedHeightFeet = mHeightFeetNumberPicker.getValue();
        mHeightFeetNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
                selectedHeightFeet = newVal;
            }
        });

        //height inches number picker
        mHeightInchesNumberPicker = (NumberPicker) findViewById(R.id.heightInchesNumberPicker);
        mHeightInchesNumberPicker.setMinValue(0);
        mHeightInchesNumberPicker.setMaxValue(11);
        mHeightInchesNumberPicker.setValue(2);
        selectedHeightInches = mHeightInchesNumberPicker.getValue();
        mHeightInchesNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
                selectedHeightInches = newVal;
            }
        });



        sexArray = getResources().getStringArray(R.array.sex);

        //sexnumber picker
        mSexNumberPicker = (NumberPicker) findViewById(R.id.sexNumberPicker);
        mSexNumberPicker.setMinValue(0);
        mSexNumberPicker.setMaxValue(1);
        mSexNumberPicker.setDisplayedValues(sexArray);
        mSexNumberPicker.setValue(0);
        selectedSex = sexArray[mSexNumberPicker.getValue()];
        mSexNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
                selectedSex = sexArray[newVal];
            }
        });



        //deal with back button being pressed
        //Get the intent that created this activity.
        Intent receivedIntent = getIntent();
        if (receivedIntent.hasExtra("BackButtonPressed")) {
            cameFromBackButton = true;

            //set the contents with previously loaded values
            String allDataStr = helperMethods.readData(this);
            String[] old_data = allDataStr.split(",");
            //old_data[2]; //age
            mWeightNumberPicker.setValue(Integer.parseInt(old_data[3]));//weight
            selectedWeight = mWeightNumberPicker.getValue();

            mHeightFeetNumberPicker.setValue(Integer.parseInt(old_data[4]));
            selectedHeightFeet = mHeightFeetNumberPicker.getValue();

            mHeightInchesNumberPicker.setValue(Integer.parseInt(old_data[5]));
            selectedHeightInches = mHeightInchesNumberPicker.getValue();

            String old_sex = old_data[6];
            int old_sex_num;
            if(old_sex.equals("Male")){
                old_sex_num = 0;
            }
            else{
                old_sex_num = 1;
            }
            mSexNumberPicker.setValue(old_sex_num);
            selectedSex = sexArray[mSexNumberPicker.getValue()];

        }
        else{
            cameFromBackButton = false;
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view)
    {
        switch (view.getId()) {
            case R.id.datePicker:
                DatePickerDialog picker;

                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                mDatePicker.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.updateDate(1996, 11, 25);
                picker.show();

                break;


            case R.id.button_submit:

                //Error checking: ????
                String date = mDatePicker.getText().toString();
                if (date.matches("") || date.split("/").length != 3 ) {
                    //Complain that there's no text
                    Toast.makeText(body_info_input.this, "Select birthdate!", Toast.LENGTH_SHORT).show();
                    return;
                }

                String[] datas = mDatePicker.getText().toString().split("/");
                int xday = Integer.parseInt(datas[0]);
                int xmonth = Integer.parseInt(datas[1]);
                int xyear = Integer.parseInt(datas[2]);

                LocalDate today = LocalDate.now(); // Today's date is 10th Jan 2022
                LocalDate birthday = LocalDate.of(xyear, xmonth, xday); // Birth date
                Period p = Period.between(birthday, today);
                selectedAge = p.getYears();


                String age = String.valueOf(selectedAge);
                String weight = String.valueOf(selectedWeight);
                String heightFeet = String.valueOf(selectedHeightFeet);
                String heightInches = String.valueOf(selectedHeightInches);
                String sex = selectedSex;


                ArrayList<String> myList = new ArrayList<String>();

                if(cameFromBackButton){
                    String allDataStr = helperMethods.readData(this);
                    String[] old_data = allDataStr.split(",");
                    String firstName = old_data[0];
                    String lastName = old_data[1];
                    myList.add(firstName);
                    myList.add(lastName);
                }

                myList.add(age);
                myList.add(weight);
                myList.add(heightFeet);
                myList.add(heightInches);
                myList.add(sex);

                if(cameFromBackButton){
                    helperMethods.saveData(myList, this, false);
                }
                else{
                    helperMethods.saveData(myList, this, true);
                }


                Intent messageIntent = new Intent(this, location_input.class);
                this.startActivity(messageIntent);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, name_input.class);
        intent.putExtra("BackButtonPressed", true);
        this.startActivity(intent);
        finish();
    }
}