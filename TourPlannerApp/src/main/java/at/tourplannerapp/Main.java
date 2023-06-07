package at.tourplannerapp;

import at.tourplannerapp.Repositories.TourLogRepository;
import at.tourplannerapp.Repositories.TourRepository;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"at.*"})
public class Main extends Application {

    private ConfigurableApplicationContext applicationContext;
    @Override
    public void init() throws Exception {
        SpringApplicationBuilder builder = new
                SpringApplicationBuilder(Main.class);
        builder.application()
                .setWebApplicationType(WebApplicationType.NONE);
        List<String> args = getParameters().getRaw(); // passed from command line
        applicationContext
                = builder.run(args.toArray(String[]::new));
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        applicationContext.stop();
    }
    @Override
    public void start(Stage stage) throws IOException {
        BasicConfigurator.configure();
        Parent root = FXMLDependencyInjection.load("MainWindow.fxml", Locale.GERMAN, applicationContext);  // Locale.GERMANY, Locale.ENGLISH
        Scene scene = new Scene(root);
        stage.setTitle("Tour Planner!");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}