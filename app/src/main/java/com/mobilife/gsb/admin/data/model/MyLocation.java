package com.mobilife.gsb.admin.data.model;

/**
 * Created by thawornlimwattanachai on 5/29/2017 AD.
 */

public class MyLocation {
    private double longitude;
    private double latitude;

    public MyLocation(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
