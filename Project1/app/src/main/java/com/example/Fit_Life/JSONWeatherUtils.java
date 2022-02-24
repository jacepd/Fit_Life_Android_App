package com.example.Fit_Life;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//Declare methods as static. We don't want to create objects of this class.
public class JSONWeatherUtils {
    public static WeatherData getWeatherData(String data) throws JSONException{
        WeatherData weatherData = new WeatherData();
        LocationData locationData = new LocationData();

        //Start parsing JSON data
        JSONObject jsonObject = new JSONObject(data); //Must throw JSONException

        // Get location data
        JSONObject jsonCoords = jsonObject.getJSONObject("coord");
        locationData.setLatitude(jsonCoords.getDouble("lat"));
        locationData.setLongitude(jsonCoords.getDouble("long"));

        JSONObject jsonSys = jsonObject.getJSONObject("sys");
        locationData.setCountry(jsonSys.getString("country"));
        locationData.setCity(jsonObject.getString("name"));

        weatherData.setLocationData(locationData);





        WeatherData.CurrentCondition currentCondition = weatherData.getCurrentCondition();
        JSONObject jsonMain = jsonObject.getJSONObject("main");
        currentCondition.setHumidity(jsonMain.getInt("humidity"));
        currentCondition.setPressure(jsonMain.getInt("pressure"));
        weatherData.setCurrentCondition(currentCondition);

        //Get the temperature, wind and cloud data.
        WeatherData.Temperature temperature = weatherData.getTemperature();
        WeatherData.Wind wind = weatherData.getWind();
        WeatherData.Clouds clouds = weatherData.getClouds();
        temperature.setMaxTemp(jsonMain.getDouble("temp_max"));
        temperature.setMinTemp(jsonMain.getDouble("temp_min"));
        temperature.setTemp(jsonMain.getDouble("temp"));
        weatherData.setTemperature(temperature);

        return weatherData;
    }
}
