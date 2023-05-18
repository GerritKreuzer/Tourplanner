module at.tour_planner_helm_kreuzer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    exports at.tour_planner_helm_kreuzer.view;
    opens at.tour_planner_helm_kreuzer.view to javafx.fxml;
    exports at.tour_planner_helm_kreuzer;
    opens at.tour_planner_helm_kreuzer to javafx.fxml;
}