package com.softpian.restfulapiclient.event;

import com.softpian.restfulapiclient.model.WeatherData;

public class WeatherDataEvent {

    private WeatherData mWeatherData;

    public WeatherDataEvent(WeatherData weatherData) {
        mWeatherData = weatherData;
    }

    public WeatherData getWeatherData() {
        return mWeatherData;
    }
}
