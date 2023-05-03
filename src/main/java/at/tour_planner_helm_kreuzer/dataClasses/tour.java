package at.tour_planner_helm_kreuzer.dataClasses;

public record tour(int id, String name, int transportationType, int tourDistance, int estimatedTime, String pathToMap, String user) {}
