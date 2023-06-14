package at.tourplannerapp.dto;

public class Location {
    private LatLng latLng;
    private String adminArea4;
    private String adminArea5Type;
    private String adminArea4Type;
    private String adminArea5;
    private String street;
    private String adminArea1;
    private String adminArea3;
    private String adminArea6Type;
    private String adminArea6;
    private String type;
    private LatLng displayLatLng;
    private long linkId;
    private String postalCode;
    private String sideOfStreet;
    private boolean dragPoint;
    private String adminArea1Type;
    private String geocodeQuality;
    private String geocodeQualityCode;
    private String adminArea3Type;
    private String unknownInput;
    private String mapUrl;


    public LatLng getLatLng() { return latLng; }
    public void setLatLng(LatLng value) { this.latLng = value; }

    public String getAdminArea4() { return adminArea4; }
    public void setAdminArea4(String value) { this.adminArea4 = value; }

    public String getAdminArea5Type() { return adminArea5Type; }
    public void setAdminArea5Type(String value) { this.adminArea5Type = value; }

    public String getAdminArea4Type() { return adminArea4Type; }
    public void setAdminArea4Type(String value) { this.adminArea4Type = value; }

    public String getAdminArea6Type() { return adminArea6Type; }
    public void setAdminArea6Type(String value) { this.adminArea6Type = value; }

    public String getAdminArea5() { return adminArea5; }
    public void setAdminArea5(String value) { this.adminArea5 = value; }

    public String getAdminArea6() { return adminArea6; }
    public void setAdminArea6(String value) { this.adminArea6 = value; }

    public String getStreet() { return street; }
    public void setStreet(String value) { this.street = value; }

    public String getAdminArea1() { return adminArea1; }
    public void setAdminArea1(String value) { this.adminArea1 = value; }

    public String getAdminArea3() { return adminArea3; }
    public void setAdminArea3(String value) { this.adminArea3 = value; }

    public String getType() { return type; }
    public void setType(String value) { this.type = value; }

    public LatLng getDisplayLatLng() { return displayLatLng; }
    public void setDisplayLatLng(LatLng value) { this.displayLatLng = value; }

    public long getLinkId() { return linkId; }
    public void setLinkId(long value) { this.linkId = value; }

    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String value) { this.postalCode = value; }

    public String getSideOfStreet() { return sideOfStreet; }
    public void setSideOfStreet(String value) { this.sideOfStreet = value; }

    public boolean getDragPoint() { return dragPoint; }
    public void setDragPoint(boolean value) { this.dragPoint = value; }

    public String getAdminArea1Type() { return adminArea1Type; }
    public void setAdminArea1Type(String value) { this.adminArea1Type = value; }

    public String getGeocodeQuality() { return geocodeQuality; }
    public void setGeocodeQuality(String value) { this.geocodeQuality = value; }

    public String getGeocodeQualityCode() { return geocodeQualityCode; }
    public void setGeocodeQualityCode(String value) { this.geocodeQualityCode = value; }

    public String getAdminArea3Type() { return adminArea3Type; }
    public void setAdminArea3Type(String value) { this.adminArea3Type = value; }

    public String getUnknownInput() {
        return unknownInput;
    }

    public void setUnknownInput(String unknownInput) {
        this.unknownInput = unknownInput;
    }

    public String getMapUrl() {
        return mapUrl;
    }

    public void setMapUrl(String mapUrl) {
        this.mapUrl = mapUrl;
    }
}
