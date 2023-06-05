package at.tourplannerapp;

import at.tourplannerapp.view.ControllerFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.BasicConfigurator;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        BasicConfigurator.configure();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainWindow.fxml"),
                ResourceBundle.getBundle("at.tourplannerapp.gui_strings", Locale.GERMAN),
                new JavaFXBuilderFactory(),
                controllerCLass -> ControllerFactory.getInstance().create(controllerCLass));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Tour Planner!");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}