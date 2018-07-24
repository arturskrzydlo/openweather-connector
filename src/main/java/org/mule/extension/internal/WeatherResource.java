package org.mule.extension.internal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.mule.extension.internal.utils.LocalDateTimeSerializer;
import org.mule.extension.internal.utils.UnixTimestampDeserializer;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResource implements Serializable {

    private Double temperature;
    private Double pressure;
    private Integer humidity;
    private String weatherDesc;
    private String weatherMain;
    private String weatherIcon;
    private String name;


    @JsonProperty("dt")
    @JsonDeserialize(using = UnixTimestampDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime date;

    @JsonProperty("main")
    private void unpackNestedMain(Map<String, String> main) {
        this.temperature = Double.valueOf(main.get("temp"));
        this.pressure = Double.valueOf(main.get("pressure"));
        this.humidity = Integer.valueOf(main.get("humidity"));
    }

    @JsonProperty("weather")
    private void unpackNestedWeather(List<Map<String, String>> weather){
        Map<String, String> weatherAttributes = weather.get(0);
        this.weatherDesc = weatherAttributes.get("description");
        this.weatherMain = weatherAttributes.get("main");
        this.weatherIcon = weatherAttributes.get("icon");
    }

    public Double getTemperature() {
        return temperature;
    }

    public Double getPressure() {
        return pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getWeatherDesc() {
        return weatherDesc;
    }

    public String getWeatherMain() {
        return weatherMain;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "{" +
                "temperature=" + temperature +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", date=" + date +
                '}';
    }
}
