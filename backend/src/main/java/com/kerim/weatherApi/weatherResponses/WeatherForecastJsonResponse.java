package com.kerim.weatherApi.weatherResponses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherForecastJsonResponse {

    public static class Response {
        @JsonProperty("forecastday")
        private ForecastDay[] forecastDays;

        public ForecastDay[] getForecastDays() {
            return forecastDays;
        }

        public void setForecastDays(ForecastDay[] forecastDays) {
            this.forecastDays = forecastDays;
        }
    }

    public static class ForecastDay {
        @JsonProperty("date")
        private String date;

        @JsonProperty("day")
        private Day day;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Day getDay() {
            return day;
        }

        public void setDay(Day day) {
            this.day = day;
        }
    }

    public static class Day {
        @JsonProperty("maxtemp_c")
        private double maxTempC;

        @JsonProperty("mintemp_c")
        private double minTempC;

        @JsonProperty("maxtemp_f")
        private double maxTempF;

        @JsonProperty("mintemp_f")
        private double minTempF;

        @JsonProperty("avghumidity")
        private int avgHumidity;

        @JsonProperty("condition")
        private WeatherCondition condition;

        public double getMaxTempC() {
            return maxTempC;
        }

        public void setMaxTempC(double maxTempC) {
            this.maxTempC = maxTempC;
        }

        public double getMinTempC() {
            return minTempC;
        }

        public void setMinTempC(double minTempC) {
            this.minTempC = minTempC;
        }

        public double getMaxTempF() {
            return maxTempF;
        }

        public void setMaxTempF(double maxTempF) {
            this.maxTempF = maxTempF;
        }

        public double getMinTempF() {
            return minTempF;
        }

        public void setMinTempF(double minTempF) {
            this.minTempF = minTempF;
        }

        public int getAvgHumidity() {
            return avgHumidity;
        }

        public void setAvgHumidity(int avgHumidity) {
            this.avgHumidity = avgHumidity;
        }

        public WeatherCondition getCondition() {
            return condition;
        }

        public void setCondition(WeatherCondition condition) {
            this.condition = condition;
        }
    }

    public static class WeatherCondition {
        @JsonProperty("text")
        private String text;

        @JsonProperty("icon")
        private String icon;

        @JsonProperty("code")
        private int code;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }

    @JsonProperty("forecast")
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
