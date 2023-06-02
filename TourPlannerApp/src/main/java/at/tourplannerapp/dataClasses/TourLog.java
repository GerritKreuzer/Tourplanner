package at.tourplannerapp.dataClasses;

import java.util.Date;

public record TourLog(int id, int tourId, Date date, String comment, int difficulty, int totalTime, int rating) {}
