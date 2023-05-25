module com.example.dl {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.dl to javafx.fxml;
    exports com.example.dl;
}