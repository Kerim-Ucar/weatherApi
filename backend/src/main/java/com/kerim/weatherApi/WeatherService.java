package com.kerim.weatherApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class WeatherService {

    private static final Logger weatherServiceLogger = LoggerFactory.getLogger(WeatherService.class);

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.baseurl}")
    private String baseUrl;

    //Returns array containing temperature in c and f, 2 api calls for each temp is useless
    public Double[] getTemperature() {
        RestClient weatherApiRequest = RestClient.builder()
                .baseUrl(baseUrl)
                .build();

        Double[] temperature = new Double[2];

        WeatherResponse jsonResponse = weatherApiRequest.get()
                .uri("current.json?key={apiKey}&q=London", apiKey)
                .retrieve()
                .body(WeatherResponse.class);

        if(jsonResponse != null){
            temperature[0] = jsonResponse.getCurrent().getTempC();
            temperature[1] = jsonResponse.getCurrent().getTempF();
            return temperature;
        }
        else{
            if(!isApiKeyAndBaseUrlSet()){
                weatherServiceLogger.error(String.format("ApiKey: isNull?: %b, isEmpty?: %b  BaseUrl: isNull?: %b, isEmpty?: %b", apiKey == null, apiKey.isEmpty(), baseUrl == null, baseUrl.isEmpty()));
            }

            weatherServiceLogger.error("Error getting temperature");
            return null;
        }

    }

    public boolean isApiKeyAndBaseUrlSet(){
        return apiKey != null && !apiKey.isEmpty()
                && baseUrl != null && !baseUrl.isEmpty();
    }

}