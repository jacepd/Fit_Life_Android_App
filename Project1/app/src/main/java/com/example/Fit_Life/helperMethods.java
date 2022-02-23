package com.example.Fit_Life;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;

public class helperMethods {

    //method that takes in an ArrayList
    //overwrite the data with append = false
    public static String saveData(ArrayList<String> args, Context context, boolean append) {

        File myDir = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        String fname = "userData.txt";
        File file = new File(myDir, fname);

        String s = "";
        for(int i=0; i < args.size(); i++){
            s += (args.get(i) + ",");
        }

        try {
            FileOutputStream stream = new FileOutputStream(file, append);
            stream.write(s.getBytes());
            stream.flush();
            stream.close();
        } catch (Exception e) {
            Toast.makeText(context,"Failed!",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return file.getAbsolutePath();
    }


    //method that takes in an Array
    public static String saveData(String[] arr, Context context, boolean append) {

        ArrayList<String> args = new ArrayList<String>();
        Collections.addAll(args, arr);

        File myDir = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        String fname = "userData.txt";
        File file = new File(myDir, fname);

        String s = "";
        for(int i=0; i < args.size(); i++){
            s += (args.get(i) + ",");
        }

        try {
            FileOutputStream stream = new FileOutputStream(file, append);
            stream.write(s.getBytes());
            stream.flush();
            stream.close();
        } catch (Exception e) {
            Toast.makeText(context,"Failed!",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return file.getAbsolutePath();
    }


    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }


    public static String readData(File userInfoFile) {
        String str = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(userInfoFile));
            str = reader.readLine();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
