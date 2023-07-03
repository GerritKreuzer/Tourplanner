package at.tourplannerapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "app")
public class ApplicationConfigProperties {
    private String mapquestUnit;
    private String mapquestKey;

    private String weatherKey;

    public String getMapquestUnit() {
        return mapquestUnit;
    }

    public void setMapquestUnit(String mapquestUnit) {
        this.mapquestUnit = mapquestUnit;
    }

    public String getMapquestKey() {
        return mapquestKey;
    }

    public void setMapquestKey(String mapquestKey) {
        this.mapquestKey = mapquestKey;
    }

    public String getWeatherKey() {
        return weatherKey;
    }

    public void setWeatherKey(String weatherKey) {
        this.weatherKey = weatherKey;
    }
}
