package org.mule.extension.internal;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mule.runtime.extension.api.annotation.metadata.OutputResolver;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.Content;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Example;
import org.mule.runtime.http.api.domain.message.response.HttpResponse;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


/**
 * This class is a container for operations, every public method in this class will be taken as an extension operation.
 */

public class OpenWeatherOperations {


    private final static String WEATHER_COMMAND = "#weather";

    @DisplayName("Current city weather")
    @MediaType(MediaType.APPLICATION_JSON)
    @OutputResolver(output = OpenWeatherOutputResolver.class)
    public String retrieveCityCurrentWeather(@Connection OpenWeatherConnection connection,
                                             @Optional @Content(primary = true) SlackMessage message,
                                             @Example("Krakow") String city,
                                             @Example("pl") String countryCode) throws IOException, TimeoutException {



        ObjectMapper mapper = new ObjectMapper();

        if(message!=null && message.getText().toLowerCase().equals(WEATHER_COMMAND)){
            HttpResponse httpResponse = connection.getCurrentWeather(new City(city, countryCode));
            WeatherResource content = mapper.readValue(httpResponse.getEntity().getContent(), WeatherResource.class);
            return mapper.writeValueAsString(content);
        }

        return "{}";


    }


}
