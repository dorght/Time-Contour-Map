package net.rebootu.timemap.controllers;

import com.google.maps.model.LatLng;

/**
 * Created by sean on 6/1/15.
 */
public class Destination {
    private final int NEAREST_ARRAY_SIZE = 5;

    private int nodeId;

    private double latitude;
    private double longitude;
    private int timefromorigin;                         // seconds
    private int distancefromorigin;                     // meters
    private LatLng querypt;

    public LatLng getQueryPt() {
        return this.querypt;
    }

    public void setQueryPt(LatLng query) {
        this.querypt = query;
    }


    private Destination[] nearestNeighbor = new Destination[NEAREST_ARRAY_SIZE];

// www.movable-type.co.uk/scripts/latlong.html

    public double distance(LatLng location) {
        double dist = 0.0;

        return dist;
    }



    public double travelTimeGradiant(LatLng location, int time) {
        return time/distance(location);
    }

}
