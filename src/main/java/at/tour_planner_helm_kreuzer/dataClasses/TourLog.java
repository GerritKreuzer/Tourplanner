package at.tour_planner_helm_kreuzer.dataClasses;

import java.util.Date;

public record TourLog(int id, int tourId, Date date, String comment, int difficulty, int totalTime, int rating) {}
