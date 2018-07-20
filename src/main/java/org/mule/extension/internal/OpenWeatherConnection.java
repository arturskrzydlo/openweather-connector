package org.mule.extension.internal;


import org.mule.runtime.http.api.HttpService;
import org.mule.runtime.http.api.client.HttpClient;
import org.mule.runtime.http.api.client.HttpClientConfiguration;
import org.mule.runtime.http.api.domain.message.request.HttpRequest;
import org.mule.runtime.http.api.domain.message.response.HttpResponse;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static org.mule.runtime.http.api.HttpConstants.Method.GET;


public final class OpenWeatherConnection {

    private static final String API_URI = "https://api.openweathermap.org/data";
    private static final String CITY_CURRENT_WEATHER_URL = "/2.5/weather?q={city},{countryCode}&appid={apiToken}";

    private final String apiToken;
    private HttpClient httpClient;


    public OpenWeatherConnection(String apiToken, HttpService httpService) {

        this.apiToken = apiToken;
        initHttpClient(httpService);
    }

    public HttpResponse getCurrentWeather(City city) throws IOException, TimeoutException {

        String uri = UriComponentsBuilder.fromHttpUrl(API_URI)
                .path(CITY_CURRENT_WEATHER_URL)
                .buildAndExpand(city.getName(), city.getCountryCode(), apiToken)
                .toUriString();

        return httpClient.send(HttpRequest.builder()
                .method(GET)
                .uri(uri).build(), 5000, true, null);

    }

    public void invalidate() {
        // do something to invalidate this connection!
    }

    private void initHttpClient(HttpService httpService) {
        HttpClientConfiguration.Builder builder = new HttpClientConfiguration.Builder();

        builder.setName("openweather");
        httpClient = httpService.getClientFactory().create(builder.build());
        httpClient.start();
    }
}
