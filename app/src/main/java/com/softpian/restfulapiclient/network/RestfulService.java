package com.softpian.restfulapiclient.network;

import com.softpian.restfulapiclient.BuildConfig;
import com.softpian.restfulapiclient.event.ExceptionEvent;
import com.softpian.restfulapiclient.event.ResponseErrorEvent;
import com.softpian.restfulapiclient.event.WeatherDataEvent;
import com.softpian.restfulapiclient.model.WeatherData;
import com.softpian.restfulapiclient.util.Constant;

import org.greenrobot.eventbus.EventBus;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestfulService {

    private RestfulService() {}

    private static class SingletonHelper {

        private static final RestfulService INSTANCE = new RestfulService();
    }

    public static RestfulService getInstance() {

        return SingletonHelper.INSTANCE;
    }

    private WeatherApi getServiceApi() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit.create(WeatherApi.class);
    }

    private WeatherApi getSimpleApi() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(WeatherApi.class);
    }

    public void getWeatherData(String cityName) {

        WeatherApi weatherApi = getServiceApi();
        Call<WeatherData> weatherDataCall = weatherApi.getWeatherData(cityName, BuildConfig.OPEN_WEATHER_MAP_API_KEY);
        weatherDataCall.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                if (response.isSuccessful()) {
                    WeatherData weatherData = response.body();
                    EventBus.getDefault().post(new WeatherDataEvent(weatherData));
                } else {
                    EventBus.getDefault().post(new ResponseErrorEvent(response.code()));
                }
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                EventBus.getDefault().post(new ExceptionEvent("Network exception occurred!", t));
            }
        });
    }
}