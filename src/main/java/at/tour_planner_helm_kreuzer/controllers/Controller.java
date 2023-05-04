package at.tour_planner_helm_kreuzer.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    @FXML
    private ListView<String> tourList;
    List<String> tours = new ArrayList<String>();//create an observable list
    public ObservableList<String> toursList = FXCollections.observableList(tours);
    public Controller() {
        System.out.println("Controller constructer call");
    }
    @FXML
    public void initialize() {
        System.out.println("test");
        tours.add("Test Tour");
        tourList.setItems(toursList);
    }

    @FXML
    private TableView<String> logsList;
    @FXML
    private TextField searchText;
    @FXML
    private Button addTour;
    @FXML
    private Button deleteTour;
    @FXML
    private Button editTour;
    @FXML
    private Button addTourLog;
    @FXML
    private Button deleteTourLog;
    @FXML
    private Button editTourLog;
}