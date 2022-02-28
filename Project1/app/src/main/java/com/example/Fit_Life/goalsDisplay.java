package com.example.Fit_Life;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import com.example.basicinfoname.R;
import android.os.Bundle;

public class goalsDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals_display);
        FragmentTransaction fTrans = getSupportFragmentManager().beginTransaction();

        String allDataStr = helperMethods.readData(this);
        String[] datas = allDataStr.split(",");
        String goal = datas[9];

        if(goal.equals("Gain")){
            fTrans.replace(R.id.fl_frag_ph_1, new goalsFragGain(),"Frag_Gain");
        }
        else if(goal.equals("Lose")){
            fTrans.replace(R.id.fl_frag_ph_1, new goalsFragLose(),"Frag_Lose");
        }
        else{
            fTrans.replace(R.id.fl_frag_ph_1, new goalsFragMaintain(),"Frag_Maintain");
        }

        fTrans.commit();
    }


}