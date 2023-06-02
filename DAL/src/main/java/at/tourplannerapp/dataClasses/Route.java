package at.tourplannerapp.dataClasses;

import java.util.List;

public record Route(int tourId, List<String> coordinates) {}
