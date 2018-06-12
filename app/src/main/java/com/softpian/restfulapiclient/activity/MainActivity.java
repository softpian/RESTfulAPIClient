package com.softpian.restfulapiclient.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.softpian.restfulapiclient.R;
import com.softpian.restfulapiclient.event.ExceptionEvent;
import com.softpian.restfulapiclient.event.ResponseErrorEvent;
import com.softpian.restfulapiclient.event.WeatherDataEvent;
import com.softpian.restfulapiclient.model.WeatherData;
import com.softpian.restfulapiclient.network.RestfulService;
import com.softpian.restfulapiclient.util.Constant;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String CITY_NAME = "Auckland";

    @BindView(R.id.ivBackground) ImageView mBackgroundView;
    @BindView(R.id.ivWeatherIcon) ImageView mWeatherIconView;
    @BindView(R.id.ivIconFromUrl) ImageView mIconFromUrlView;
    @BindView(R.id.tvCity) TextView mCityView;
    @BindView(R.id.tvTemp) TextView mTemperatureView;
    @BindView(R.id.tvMinTemp) TextView mMinTemperatureView;
    @BindView(R.id.tvMaxTemp) TextView mMaxTemperatureView;
    @BindView(R.id.tvWindSpeed) TextView mWindSpeedView;
    @BindView(R.id.tvDescription) TextView mDescriptionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Picasso.get()
                .load(R.drawable.background)
                .fit()
                .into(mBackgroundView);

        RestfulService.getInstance().getWeatherData(CITY_NAME);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWeatherDataEvent(WeatherDataEvent weatherDataEvent) {

        WeatherData weatherData = weatherDataEvent.getWeatherData();

        mCityView.setText(weatherData.getName());

        double kelvin = weatherData.getMain().getTemp();
        double celsius = kelvin - 273.15;
        Log.d(TAG, "Current Temperature : " + celsius + " degrees Celsius!!");
        mTemperatureView.setText(String.valueOf(celsius));

        kelvin = weatherData.getMain().getTempMin();
        celsius = kelvin - 273.15;
        mMinTemperatureView.setText(String.valueOf(celsius));

        kelvin = weatherData.getMain().getTempMax();
        celsius = kelvin - 273.15;
        mMaxTemperatureView.setText(String.valueOf(celsius));

        mWindSpeedView.setText(String.valueOf(weatherData.getWind().getSpeed()));
        mDescriptionView.setText(weatherData.getWeather().get(0).getDescription());


        String weatherIconKey = getWeatherIconKey(weatherData.getWeather().get(0).getId());
        Picasso.get()
                .load(Constant.WEATHER_ICONS.get(weatherIconKey))
                .fit()
                .into(mWeatherIconView);

        StringBuilder iconUrl = new StringBuilder();
        iconUrl.append("http://openweathermap.org/img/w/");
        iconUrl.append(weatherData.getWeather().get(0).getIcon());
        iconUrl.append(".png");
        Picasso.get()
                .load(iconUrl.toString())
                .placeholder(R.drawable.placeholder)
                .fit()
                .into(mIconFromUrlView);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onResponseErrorEvent(ResponseErrorEvent responseErrorEvent) {
        Toast.makeText(this,
                "Requesting to server failed, Response code: " + responseErrorEvent.getResponseCode(),
                Toast.LENGTH_LONG).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onExceptionEvent(ExceptionEvent exceptionEvent) {
        Toast.makeText(this,
                exceptionEvent.getMessage() + ", " + "detailed exception information: " + exceptionEvent.getThrowable().getMessage(),
                Toast.LENGTH_LONG).show();
    }

    private static String getWeatherIconKey(int condition) {

        if (condition >= 200 && condition < 300) {
            return "thunderstorm";
        } else if (condition >= 300 && condition < 500) {
            return "drizzle";
        } else if (condition >= 500 && condition < 600) {
            return "rain";
        } else if (condition >= 600 && condition <= 700) {
            return "snow";
        } else if (condition >= 701 && condition < 800) {
            return "atmosphere";
        } else if (condition == 800) {
            return "clear";
        } else if (condition >= 801 && condition <= 804) {
            return "clouds";
        }

        return "dunno";
    }
}