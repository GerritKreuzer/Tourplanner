package at.tourplannerapp.dataClasses;

public record Tour(int id, String name, int transportationType, int tourDistance, int estimatedTime, String pathToMap, String user) {}
