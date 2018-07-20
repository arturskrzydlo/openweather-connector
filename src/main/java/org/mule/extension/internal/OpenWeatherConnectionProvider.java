package org.mule.extension.internal;

import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.api.connection.ConnectionValidationResult;
import org.mule.runtime.api.connection.PoolingConnectionProvider;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.http.api.HttpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;


public class OpenWeatherConnectionProvider implements PoolingConnectionProvider<OpenWeatherConnection> {

    private final Logger LOGGER = LoggerFactory.getLogger(OpenWeatherConnectionProvider.class);

    @Parameter
    @DisplayName("Api token")
    private String apiToken;

    @Inject
    private HttpService httpService;

    @Override
    public OpenWeatherConnection connect() throws ConnectionException {
        return new OpenWeatherConnection(apiToken, httpService);
    }

    @Override
    public void disconnect(OpenWeatherConnection connection) {
        try {
            connection.invalidate();
        } catch (Exception e) {
            LOGGER.error("Error while disconnecting : " + e.getMessage(), e);
        }
    }

    @Override
    public ConnectionValidationResult validate(OpenWeatherConnection connection) {
        return ConnectionValidationResult.success();
    }
}
