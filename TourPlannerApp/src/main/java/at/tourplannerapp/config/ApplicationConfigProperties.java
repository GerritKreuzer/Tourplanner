package at.tourplannerapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public class ApplicationConfigProperties {
    private int timeoutMs;
    private String mapquestKey;


    public int getTimeoutMs() {
        return timeoutMs;
    }

    public void setTimeoutMs(int timeoutMs) {
        this.timeoutMs = timeoutMs;
    }

    public String getMapquestKey() {
        return mapquestKey;
    }

    public void setMapquestKey(String mapquestKey) {
        this.mapquestKey = mapquestKey;
    }
}
