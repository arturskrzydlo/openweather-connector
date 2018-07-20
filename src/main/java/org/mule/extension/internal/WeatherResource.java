package org.mule.extension.internal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.mule.extension.internal.utils.LocalDateTimeSerializer;
import org.mule.extension.internal.utils.UnixTimestampDeserializer;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResource implements Serializable {

    private Double temperature;
    private Double pressure;
    private Integer humidity;

    @JsonProperty("dt")
    @JsonDeserialize(using = UnixTimestampDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime date;

    @JsonProperty("main")
    private void unpackNested(Map<String, String> main) {
        this.temperature = Double.valueOf(main.get("temp"));
        this.pressure = Double.valueOf(main.get("pressure"));
        this.humidity = Integer.valueOf(main.get("humidity"));
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
