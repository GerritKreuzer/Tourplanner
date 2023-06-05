package at.tourplannerapp.dbHandler;

import java.sql.*;
/*
public class DbHandler {

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

    public DbHandler() {
        try {
            connection = DriverManager.getConnection(url, user, password);
            if(connection.isClosed()){
                System.err.println("Error connecting to database.");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void insertData(Object data) {

        try {
            PreparedStatement pstmt = null;
            if (data instanceof Tour tour) {

                pstmt = connection.prepareStatement(INSERT_TOUR);
                pstmt.setString(1, tour.name());
                pstmt.setInt(2, tour.transportationType());
                pstmt.setInt(3, tour.tourDistance());
                pstmt.setInt(4, tour.estimatedTime());
                pstmt.setString(5, tour.pathToMap());
                pstmt.setString(6, tour.user());
            } else if (data instanceof Route route) {

                pstmt = connection.prepareStatement(INSERT_ROUTE);
                for (int i = 0; i < route.coordinates().size(); i++) {
                    pstmt.setInt(1, route.tourId());
                    pstmt.setInt(2, i + 1);
                    pstmt.setString(3, route.coordinates().get(i));
                    pstmt.executeUpdate();
                }
            } else if (data instanceof TourLog tourLog) {

                pstmt = connection.prepareStatement(INSERT_TOURLOG);
                pstmt.setInt(1, tourLog.tourId());
                pstmt.setDate(2, (Date) tourLog.date());
                pstmt.setString(3, tourLog.comment());
                pstmt.setInt(4, tourLog.difficulty());
                pstmt.setInt(5, tourLog.totalTime());
                pstmt.setInt(6, tourLog.rating());
            }else{
                throw new IllegalStateException("Invalid data type: " + data.getClass().toString());
            }
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteData(int id, String tableName) {
        String deleteQuery = switch (tableName) {
            case "tour" -> DELETE_TOUR;
            case "route" -> DELETE_ROUTE;
            case "tourlog" -> DELETE_TOURLOG;
            default -> throw new IllegalArgumentException("Invalid table name: " + tableName);
        };

        try (PreparedStatement pstmt = connection.prepareStatement(deleteQuery)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
*/