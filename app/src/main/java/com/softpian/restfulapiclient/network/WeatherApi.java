package com.softpian.restfulapiclient.network;

import com.softpian.restfulapiclient.model.WeatherData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("weather")
    Call<WeatherData> getWeatherData(@Query("q") String cityName, @Query("APPID") String key);
}
