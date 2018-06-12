package com.softpian.restfulapiclient.util;

import com.softpian.restfulapiclient.R;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Constant {
    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    public static final Map<String, Integer> WEATHER_ICONS;
    static {
        Map<String, Integer> weatherIcons = new HashMap<>();
        weatherIcons.put("thunderstorm", R.drawable.thunderstorm);
        weatherIcons.put("drizzle", R.drawable.drizzle);
        weatherIcons.put("rain", R.drawable.rain);
        weatherIcons.put("snow", R.drawable.snow);
        weatherIcons.put("atmosphere", R.drawable.atmosphere);
        weatherIcons.put("clear", R.drawable.clear);
        weatherIcons.put("clouds", R.drawable.clouds);
        weatherIcons.put("dunno", R.drawable.dunno);

        WEATHER_ICONS = Collections.unmodifiableMap(weatherIcons);
    }
}
