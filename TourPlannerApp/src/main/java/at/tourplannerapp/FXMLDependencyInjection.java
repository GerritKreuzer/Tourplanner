package at.tourplannerapp;

import at.tourplannerapp.view.ControllerFactory;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class FXMLDependencyInjection {
    public static Parent load(String location, Locale locale, ConfigurableApplicationContext context) throws IOException {
        FXMLLoader loader = getLoader(location, locale, context);
        return loader.load();
    }

    public static FXMLLoader getLoader(String location, Locale locale, ConfigurableApplicationContext context) {
        return new FXMLLoader(
                FXMLDependencyInjection.class.getResource("/at/tourplannerapp/" + location),
                ResourceBundle.getBundle("at.tourplannerapp." + "gui_strings", locale),
                new JavaFXBuilderFactory(),
                controllerClass -> ControllerFactory.getInstance(context).create(controllerClass)
        );
    }
}
