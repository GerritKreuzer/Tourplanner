package at.tourplannerapp;

import at.tourplannerapp.config.ApplicationConfigProperties;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationConfigProperties.class})
public class Main extends Application {

    private ConfigurableApplicationContext applicationContext;

    private static Stage mainStage;

    public static void main(String[] args) {
        launch();
    }

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
        ApplicationConfigProperties configProperties
                = applicationContext.getBean(ApplicationConfigProperties.class);
        System.out.println("TIMEOUT IS: "+ configProperties.getTimeoutMs());
        System.out.println("MAPQUESTKEY IS: "+ configProperties.getMapquestKey());


        Parent root = FXMLDependencyInjection.load("MainWindow.fxml", Locale.ENGLISH, applicationContext);  // Locale.GERMAN, Locale.ENGLISH
        Scene scene = new Scene(root);
        stage.setTitle("Tour Planner!");
        stage.setMaximized(true);
        stage.setScene(scene);
        mainStage = stage;
        stage.show();
    }

    public static Stage getMainStage() {
        return mainStage;
    }
}