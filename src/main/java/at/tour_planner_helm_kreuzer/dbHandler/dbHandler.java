package at.tour_planner_helm_kreuzer.dbHandler;

import at.tour_planner_helm_kreuzer.dataClasses.route;
import at.tour_planner_helm_kreuzer.dataClasses.tour;
import at.tour_planner_helm_kreuzer.dataClasses.tourLog;

import java.sql.*;

public class dbHandler {

    Connection connection;
    private static final String url = "jdbc:postgresql://localhost:5432/swen2db";
    private static final String user = "swen1user";
    private static final String password = "swen1password";

    static final String INSERT_TOUR = "INSERT INTO tours (name, transportationType, tourDistance, estimatedTime, pathToMap, user) VALUES (?, ?, ?, ?, ?, ?)";
    static final String INSERT_ROUTE = "INSERT INTO coordinates (tourId, \"order\", coordinates) VALUES (?, ?, ?)";
    static final String INSERT_TOURLOG = "INSERT INTO tourlogs (tourId, date, comment, difficulty, totalTime, rating) VALUES (?, ?, ?, ?, ?, ?)";
    static final String DELETE_TOUR = "DELETE FROM tours WHERE id = ?";
    static final String DELETE_ROUTE = "DELETE FROM coordinates WHERE tourId = ?";
    static final String DELETE_TOURLOG = "DELETE FROM tourlogs WHERE id = ?";

    public dbHandler() {
        try {
            connection = DriverManager.getConnection(url, user, password);
            if(connection.isClosed()){
                System.err.println("Error connecting to database.");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void insertTour(tour tour) {
        try (PreparedStatement pstmt = connection.prepareStatement(INSERT_TOUR)) {
            pstmt.setString(1, tour.name());
            pstmt.setInt(2, tour.transportationType());
            pstmt.setInt(3, tour.tourDistance());
            pstmt.setTime(4, Time.valueOf(tour.estimatedTime() + ":00"));
            pstmt.setString(5, tour.pathToMap());
            pstmt.setString(6, tour.user());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertRoute(route route) {
        try (PreparedStatement pstmt = connection.prepareStatement(INSERT_ROUTE)) {
            for (int i = 0; i < route.coordinates().size(); i++) {
                pstmt.setInt(1, route.tourId());
                pstmt.setInt(2, i + 1);
                pstmt.setString(3, route.coordinates().get(i));
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertTourLog(tourLog tourLog) {
        try (PreparedStatement pstmt = connection.prepareStatement(INSERT_TOURLOG)) {
            pstmt.setInt(1, tourLog.tourId());
            pstmt.setDate(2, (Date) tourLog.date());
            pstmt.setString(3, tourLog.comment());
            pstmt.setInt(4, tourLog.difficulty());
            pstmt.setInt(5, tourLog.totalTime());
            pstmt.setInt(6, tourLog.rating());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTour(int tourId) {
        try (PreparedStatement pstmt = connection.prepareStatement(DELETE_TOUR)) {
            pstmt.setInt(1, tourId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRoute(int tourId) {
        try (PreparedStatement pstmt = connection.prepareStatement(DELETE_ROUTE)) {
            pstmt.setInt(1, tourId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTourLog(int tourLogId) {
        try (PreparedStatement pstmt = connection.prepareStatement(DELETE_TOURLOG)) {
            pstmt.setInt(1, tourLogId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
