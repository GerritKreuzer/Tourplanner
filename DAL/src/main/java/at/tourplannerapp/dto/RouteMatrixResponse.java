package at.tourplannerapp.dto;

public class RouteMatrixResponse {
    private boolean allToAll;
    private double[] distance;
    private long[] time;
    private Location[] locations;
    private boolean manyToOne;
    private Info info;

    public boolean getAllToAll() { return allToAll; }
    public void setAllToAll(boolean value) { this.allToAll = value; }

    public double[] getDistance() { return distance; }
    public void setDistance(double[] value) { this.distance = value; }

    public long[] getTime() { return time; }
    public void setTime(long[] value) { this.time = value; }

    public Location[] getLocations() { return locations; }
    public void setLocations(Location[] value) { this.locations = value; }

    public boolean getManyToOne() { return manyToOne; }
    public void setManyToOne(boolean value) { this.manyToOne = value; }

    public Info getInfo() { return info; }
    public void setInfo(Info value) { this.info = value; }
}
