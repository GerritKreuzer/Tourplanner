package at.tour_planner_helm_kreuzer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.List;

public class HelloController {
    @FXML
    private TableView tourTable;
    List<String> tours = new ArrayList<String>();//create an observable list
    public ObservableList<String> toursTable = FXCollections.observableList(tours);

    public HelloController() {
        System.out.println("Controller constructer call");
    }

    @FXML
    public void initialize() {
        System.out.println("test");
        tours.add("Test Tour");
        tourTable.setItems(toursTable);
    }
}