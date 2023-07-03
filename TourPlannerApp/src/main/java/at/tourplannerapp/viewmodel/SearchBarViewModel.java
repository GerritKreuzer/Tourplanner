package at.tourplannerapp.viewmodel;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class SearchBarViewModel {
    private static final Logger LOGGER = LogManager.getLogger(SearchBarViewModel.class);
    private final StringProperty searchString = new SimpleStringProperty("");
    private final BooleanBinding isSearchDisabledBinding = Bindings.createBooleanBinding(() -> false);
    private List<SearchListener> listeners = new ArrayList<>();
    public SearchBarViewModel() {
        searchString.addListener((arg, oldVal, newVal) -> isSearchDisabledBinding.invalidate());
    }

    public StringProperty searchStringProperty() {
        return searchString;
    }

    public BooleanBinding searchDisabledBinding() {
        return isSearchDisabledBinding;
    }

    public void addSearchListener(SearchListener listener) {
        listeners.add(listener);
    }

    public void removeSearchListener(SearchListener listener) {
        listeners.remove(listener);
    }

    public void doSearch() {
        for (var listener : listeners) {
            listener.search(searchString.get());
            LOGGER.info("Perform search with searchString=[{}]", searchString.get());
        }
    }

    public interface SearchListener {
        void search(String searchString);
    }
}
