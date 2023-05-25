module com.example.bl {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bl to javafx.fxml;
    exports com.example.bl;
}