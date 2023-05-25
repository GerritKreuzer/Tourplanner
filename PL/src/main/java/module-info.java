module com.example.pl {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pl to javafx.fxml;
    exports com.example.pl;
}