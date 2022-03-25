package com.example.Fit_Life;


public class UserTableBuilder {
    private String location;
    private String weatherJson;

    public UserTableBuilder setLocation(String location) {
        this.location = location;
        return this;
    }

    public UserTableBuilder setWeatherJson(String weatherJson) {
        this.weatherJson = weatherJson;
        return this;
    }

    public UserTable createUserTable() {
        return new UserTable(location, weatherJson);
    }
}