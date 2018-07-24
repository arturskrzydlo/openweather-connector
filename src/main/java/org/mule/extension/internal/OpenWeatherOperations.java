package org.mule.extension.internal;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
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
    private ObjectMapper mapper = new ObjectMapper();


    @DisplayName("Current cityName weather")
    @MediaType(MediaType.APPLICATION_JSON)
    @OutputResolver(output = OpenWeatherOutputResolver.class)
    public String retrieveCityCurrentWeather(@Connection OpenWeatherConnection connection,
                                             @Optional @Content(primary = true) SlackMessage message,
                                             @Example("Krakow") @DisplayName("city") String cityName,
                                             @Example("pl") String countryCode) throws IOException, TimeoutException {

        if (isWeatherCommand(message)) {
            City city = buildCityFromSlackMessage(message.getText(), cityName, countryCode);
            HttpResponse httpResponse = connection.getCurrentWeather(city);
            WeatherResource content = mapper.readValue(httpResponse.getEntity().getContent(), WeatherResource.class);
            return mapper.writeValueAsString(content);
        }

        return "{}";
    }

    private boolean isWeatherCommand(SlackMessage message) {
        return message != null && message.getText().toLowerCase().contains(WEATHER_COMMAND);
    }

    private City buildCityFromSlackMessage(String slackMessage, String cityName, String countryCode) {

        String messageWithTrimmedWeatherCommand = StringUtils.substringAfter(slackMessage, WEATHER_COMMAND);
        String[] weatherCommandArgs = messageWithTrimmedWeatherCommand.trim().split(",");
        String cityNameArg = weatherCommandArgs[0];

        City city;
        if (cityNameArg.isEmpty()) {
            city = new City(cityName, countryCode);
        } else {
            countryCode = "";
            if (weatherCommandArgs.length > 1) {
                countryCode = weatherCommandArgs[1];
            }
            cityNameArg = StringUtils.replace(cityNameArg, " ", "%20");
            city = new City(cityNameArg, countryCode);
        }

        return city;
    }


}
