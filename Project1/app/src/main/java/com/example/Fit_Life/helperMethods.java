package com.example.Fit_Life;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class helperMethods {

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



}
