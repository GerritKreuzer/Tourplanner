package at.tourplannerapp.dto;

public class RouteMatrixRequestBody {
    private String[] locations;

    public String[] getLocations() { return locations; }
    public void setLocations(String[] value) { this.locations = value; }

    public RouteMatrixRequestBody(String[] locations) {
        this.locations = locations;
    }
}
