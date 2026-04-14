package com.kerim.weatherApi.weatherControl;


import com.kerim.weatherApi.weatherResponses.WeatherDayJsonResponse;
import com.kerim.weatherApi.weatherResponses.WeatherForecastJsonResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.baseurl}")
    private String baseUrl;

    private RestClient restClient;

    /// Do not remove this.
    /// This will run once all @Value annotations are injected, preventing RestClient from being built
    /// with empty strings and causing an error when any of the Classes functions are called.
    @PostConstruct
    public void init() {
        restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    /// Put in a try-catch loop so the function can return null as HTTPErrorCode would not allow for a return null
    /// as it expects void.
    public WeatherDayJsonResponse getResponseForCurrentWeather(String cityName){
        try {
            return restClient.get()
                    .uri("current.json?key={apiKey}&q={cityName}", apiKey, cityName)
                    .retrieve()
                    .body(WeatherDayJsonResponse.class);
        } catch (Exception e) {
            return null;
        }
    }

    public Double getTemperatureForCurrentWeather(String cityName, boolean returnCelsius){
        WeatherDayJsonResponse response = getResponseForCurrentWeather(cityName);

        if(response == null) return null;

        return returnCelsius ? response.getCurrent().getTempC() : response.getCurrent().getTempF();
    }

    public Integer getIsDayForCurrentWeather(String cityName){
        WeatherDayJsonResponse response = getResponseForCurrentWeather(cityName);

        if(response == null) return null;

        return response.getCurrent().getIsDay();
    }

    public Integer getHumidityForCurrentWeather(String cityName){
        WeatherDayJsonResponse response = getResponseForCurrentWeather(cityName);

        if(response == null) return null;

        return response.getCurrent().getHumidity();
    }

    public WeatherDayJsonResponse.WeatherCondition getWeatherConditionForCurrentWeather(String cityName){
        WeatherDayJsonResponse response = getResponseForCurrentWeather(cityName);

        if(response == null) return null;

        return response.getCurrent().getCondition();
    }


    public WeatherForecastJsonResponse getResponseForForecast(String cityName, int days){
        try {
            return restClient.get()
                    .uri("forecast.json?key={apiKey}&q={cityName}&days={days}", apiKey, cityName, days)
                    .retrieve()
                    .body(WeatherForecastJsonResponse.class);
        } catch (Exception e) {
            return null;
        }
    }

    public WeatherForecastJsonResponse.ForecastDay[] getForecastDays(String cityName, int days){
        WeatherForecastJsonResponse response = getResponseForForecast(cityName, days);

        if(response == null || response.getResponse() == null) return null;

        return response.getResponse().getForecastDays();
    }
}