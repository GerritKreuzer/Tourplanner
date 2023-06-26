package at.tourplannerapp.view;

import at.tourplannerapp.viewmodel.SearchBarViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;

public class SearchBarController {
    private final SearchBarViewModel searchBarViewModel;
    @FXML
    public Button searchButton;
    @FXML
    public TextField searchTextField;

    public SearchBarController(SearchBarViewModel searchBarViewModel) {
        this.searchBarViewModel = searchBarViewModel;
    }

    public SearchBarViewModel getSearchBarViewModel() {
        return searchBarViewModel;
    }

    @FXML
    void initialize() {
        searchTextField.textProperty().bindBidirectional(searchBarViewModel.searchStringProperty());
        searchButton.disableProperty().bind(searchBarViewModel.searchDisabledBinding());
    }

    public void onSearchButton(ActionEvent actionEvent) {
        searchBarViewModel.doSearch();
    }
}
