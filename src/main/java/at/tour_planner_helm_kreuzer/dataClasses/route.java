package at.tour_planner_helm_kreuzer.dataClasses;

import java.util.List;

public record route(int tourId, List<String> coordinates) {}
