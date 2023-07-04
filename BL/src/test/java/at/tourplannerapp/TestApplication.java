package at.tourplannerapp;

import at.tourplannerapp.config.ApplicationConfigProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationConfigProperties.class})
public class TestApplication {
}
