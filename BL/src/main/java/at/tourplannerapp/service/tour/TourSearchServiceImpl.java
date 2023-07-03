package at.tourplannerapp.service.tour;

import at.tourplannerapp.model.TourItem;
import at.tourplannerapp.model.TourLog;
import org.springframework.stereotype.Service;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TourSearchServiceImpl implements TourSearchService{

    @Override
    public List<TourItem> findMatchingTours(String searchString, Map<TourItem, List<TourLog>> searchMap) {
        List<TourItem> tours = new ArrayList<>(searchMap.keySet());
        List<TourItem> foundTours = new ArrayList<>();
        if (searchString==null || searchString.isEmpty()) {
            return tours;
        }
        Map<TourItem, List<String>> searchDataMap = getSearchData(searchMap);

        for (Map.Entry<TourItem,List<String>> entry : searchDataMap.entrySet()) {
            if (hasMatchingSubstring(searchString.toLowerCase(), entry.getValue())) {
                foundTours.add(entry.getKey());
            }
        }

        return foundTours;
    }

    private static boolean hasMatchingSubstring(String searchString, List<String> searchData) {
        for (String searchDataString : searchData){
            if (searchDataString.contains(searchString)) {
                return true;
            }
        }
        return false;
    }

    private Map<TourItem, List<String>> getSearchData(Map<TourItem, List<TourLog>> searchMap) {
        Map<TourItem, List<String>> searchDataMap = new HashMap<>();
        List<TourItem> tours = new ArrayList<>(searchMap.keySet());
        tours.forEach(tourItem -> {
            List<TourLog> tourLogs =  searchMap.get(tourItem);
            tourItem.setCalculatedProperties(tourLogs);
            List<String> searchDataList = getSearchDataForTourItem(tourItem);

            tourLogs.forEach(tourLog -> {
                searchDataList.addAll(getSearchDataForTourLogs(tourLog));
            });
            searchDataMap.put(tourItem, searchDataList);
        });
        return searchDataMap;
    }

    private List<String> getSearchDataForTourItem(TourItem tourItem) {
        List<String> tourItemSearchData = new ArrayList<>();
        for (Field field : tourItem.getClass().getDeclaredFields()) {
            try {
                if(field.getName().equals("tourId") || field.getName().equals("estimatedTime") || field.getName().equals("map")) {
                    continue;
                }
                Object value = new PropertyDescriptor(field.getName(), TourItem.class).getReadMethod().invoke(tourItem);
                if (value != null) {
                    tourItemSearchData.add(value.toString().toLowerCase());
                }
            } catch (IllegalAccessException | IntrospectionException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        return tourItemSearchData;
    }

    private List<String> getSearchDataForTourLogs(TourLog tourLog) {
        List<String> tourLogSearchData = new ArrayList<>();
        for (Field field : tourLog.getClass().getDeclaredFields()) {
            try {
                if(field.getName().equals("tourLogId")) {
                    continue;
                }
                Object value = new PropertyDescriptor(field.getName(), TourLog.class).getReadMethod().invoke(tourLog);
                if (value != null) {
                    tourLogSearchData.add(value.toString().toLowerCase());
                }
            } catch (IllegalAccessException | IntrospectionException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        return tourLogSearchData;
    }
}
