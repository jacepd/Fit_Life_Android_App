package com.example.Fit_Life;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {
    private static String BASE_URL = "https://api.openweathermap.org/data/2.5/weather?q=";
    private static String APPIDQUERY = "&appid=";
    private static final String app_id="dea49d627fea00ba2da57ad036b9b61a";

    public static URL buildURLFromString(String location){
        URL myURL = null;

        try {
            myURL = new URL(BASE_URL + location + APPIDQUERY + app_id);}

        catch(MalformedURLException e){
            e.printStackTrace();
        }

        return myURL;
    }

    public static String getDataFromURL(URL url) throws IOException{
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            InputStream inputStream = urlConnection.getInputStream();

            //The scanner trick: search for the next "beginning" of the input stream
            //No need to user BufferedReader
            Scanner scanner = new Scanner(inputStream);

            // Next beginning of the input stream, or when the next input is starting.
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if(hasInput){
                return scanner.next();
            }
            else{
                return null;
            }
        }

        catch(Exception e){
            e.printStackTrace();
            return null;
        }

        finally {
            urlConnection.disconnect();
        }
    }
}
