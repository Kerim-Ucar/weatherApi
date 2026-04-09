package com.kerim.weatherApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {

    @JsonProperty("current")
    private Current current;

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public static class Current{
        @JsonProperty("temp_c")
        private double tempC;

        @JsonProperty("temp_f")
        private double tempF;

        public double getTempC() {
            return tempC;
        }

        public double getTempF() {
            return tempF;
        }

        public void setTempC(double tempC) {
            this.tempC = tempC;
        }

        public void setTempF(double tempF) {
            this.tempF = tempF;
        }
    }

}
