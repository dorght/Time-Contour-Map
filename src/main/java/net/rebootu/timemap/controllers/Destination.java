package net.rebootu.timemap.controllers;

import com.google.maps.model.LatLng;

import java.io.Serializable;

/**
 * Created by sean on 6/1/15.
 */
public class Destination  implements Serializable {
    private final int NEAREST_ARRAY_SIZE = 5;
    private static int nodeId;

    // point returned from google reverse geocoding as closest address
    private LatLng locationpt;
    private Destination[] nearestNeighbor = new Destination[NEAREST_ARRAY_SIZE];

    private int timefromorigin;                         // seconds
    private int distancefromorigin;                     // meters
    private LatLng querypt;

// www.movable-type.co.uk/scripts/latlong.html
    // find gradiant in travel time between to destination points
  public static double travelTimeGradiant(Destination dest1, Destination dest2) {
        return Math.abs(dest1.getTimefromorigin() - dest2.getTimefromorigin()) /
                  NavFormulas.distBetweenPts(dest1.locationpt, dest2.locationpt);
  }

    public LatLng getQueryPt() {
        return this.querypt;
    }
    public void setQueryPt(LatLng query) {
        this.querypt = query;
    }

    public LatLng getLocationpt() {
        return this.locationpt;
    }
    public void setLocationpt(LatLng locationpt) {
        this.locationpt = locationpt;
    }

    public int getTimefromorigin() {
        return this.timefromorigin;
    }
    public void setTimefromorigin(int timefromorigin) {
        this.timefromorigin = timefromorigin;
    }

    public int getDistancefromorigin() {
        return this.distancefromorigin;
    }
    public void setDistancefromorigin(int distancefromorigin) {
        this.distancefromorigin = distancefromorigin;
    }

}
