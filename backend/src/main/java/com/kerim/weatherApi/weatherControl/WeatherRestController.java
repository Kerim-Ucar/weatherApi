package com.kerim.weatherApi.weatherControl;

import com.kerim.weatherApi.weatherResponses.WeatherDayJsonResponse;
import com.kerim.weatherApi.weatherResponses.WeatherForecastJsonResponse;
import io.github.bucket4j.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@CrossOrigin
@RequestMapping("/weather")
public class WeatherRestController {

    private final WeatherService weatherService;

    private final Bucket bucket;

    @Autowired
    public WeatherRestController(WeatherService weatherService, Bucket bucket) {
        this.weatherService = weatherService;
        this.bucket = bucket;
    }

    @GetMapping("/current/temperature")
    public ResponseEntity<Double> getTemperature(@RequestParam(defaultValue = "New York") String place, @RequestParam(defaultValue = "false") boolean celsius) {
        if (bucket.tryConsume(1)) {
            return ResponseEntity.ok(weatherService.getTemperatureForCurrentWeather(place, celsius));
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }

    @GetMapping("/current/humidity")
    public ResponseEntity<Integer> getHumidity(@RequestParam(defaultValue = "New York") String place) {
        if (bucket.tryConsume(1)) {
            return ResponseEntity.ok(weatherService.getHumidityForCurrentWeather(place));
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }

    @GetMapping("/current/isDay")
    public ResponseEntity<Integer> getDay(@RequestParam(defaultValue = "New York") String place) {
        if (bucket.tryConsume(1)) {
            return ResponseEntity.ok(weatherService.getIsDayForCurrentWeather(place));
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }

    @GetMapping("/current/condition")
    public ResponseEntity<WeatherDayJsonResponse.WeatherCondition> getCondition(@RequestParam(defaultValue = "New York") String place) {
        if (bucket.tryConsume(1)) {
            return ResponseEntity.ok(weatherService.getWeatherConditionForCurrentWeather(place));
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }

    @GetMapping("/forecast/upcoming")
    public ResponseEntity<WeatherForecastJsonResponse.ForecastDay[]> getForecast(@RequestParam(defaultValue = "New York") String place, @RequestParam(defaultValue = "3") int days) {
        if (bucket.tryConsume(1)) {
            return ResponseEntity.ok(weatherService.getForecastDays(place, days));
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }
}
