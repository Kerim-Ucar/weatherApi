package com.kerim.weatherApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/temperature")
    public String getTemperature(){
        Double[] temps = weatherService.getTemperature();
        return String.format("%.2f in C, %.2f in F", temps[0], temps[1]);
    }


}
