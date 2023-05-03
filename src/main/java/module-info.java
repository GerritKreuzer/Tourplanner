module at.tour_planner_helm_kreuzer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens at.tour_planner_helm_kreuzer to javafx.fxml;
    exports at.tour_planner_helm_kreuzer;
}