package com.example.Fit_Life;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Declare methods as static. We don't want to create objects of this class.
public class JSONWeatherUtils {
    public static WeatherData getWeatherData(String data) throws JSONException{
        WeatherData weatherData = new WeatherData();
        LocationData locationData = new LocationData();

        //Start parsing JSON data
        JSONObject jsonObject = new JSONObject(data); //Must throw JSONException

        // Get location data
        JSONObject jsonCoords = jsonObject.getJSONObject("coord");
        locationData.setLatitude(jsonCoords.getDouble("lat"));
        locationData.setLongitude(jsonCoords.getDouble("lon"));

        JSONObject jsonSys = jsonObject.getJSONObject("sys");
        locationData.setCountry(jsonSys.getString("country"));
        locationData.setCity(jsonObject.getString("name"));

        // Get Sunrise/Sunset Data
        locationData.setSunrise(jsonSys.getLong("sunrise"));
        locationData.setSunset(jsonSys.getLong("sunset"));

        weatherData.setLocationData(locationData);

        // Get the actual weather information.
        JSONArray jsonWeatherArray = jsonObject.getJSONArray("weather");
        JSONObject jsonWeather = jsonWeatherArray.getJSONObject(0);

        // Get the current condition of the weather.
        WeatherData.CurrentCondition currentCondition = weatherData.getCurrentCondition();
        currentCondition.setWeatherId(jsonWeather.getLong("id"));
        currentCondition.setDescr(jsonWeather.getString("description"));
        currentCondition.setCondition(jsonWeather.getString("main"));

        JSONObject jsonMain = jsonObject.getJSONObject("main");
        currentCondition.setHumidity(jsonMain.getInt("humidity"));
        currentCondition.setPressure(jsonMain.getInt("pressure"));
        weatherData.setCurrentCondition(currentCondition);

        // Get and set the Temperature data.
        WeatherData.Temperature temperature = weatherData.getTemperature();

        temperature.setMaxTemp(jsonMain.getDouble("temp_max"));
        temperature.setMinTemp(jsonMain.getDouble("temp_min"));
        temperature.setTemp(jsonMain.getDouble("temp"));
        weatherData.setTemperature(temperature);

        // Get and set Wind and Cloud data.
        WeatherData.Wind wind = weatherData.getWind();
        WeatherData.Clouds clouds = weatherData.getClouds();
        JSONObject jsonWind = jsonObject.getJSONObject("wind");
        JSONObject jsonClouds = jsonObject.getJSONObject("clouds");

        wind.setSpeed(jsonWind.getDouble("speed"));
        wind.setDeg(jsonWind.getDouble("deg"));
        weatherData.setWind(wind);

        clouds.setPerc(jsonClouds.getLong("all"));
        weatherData.setClouds(clouds);

        return weatherData;
    }
}
