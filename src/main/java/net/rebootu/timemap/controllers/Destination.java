package net.rebootu.timemap.controllers;

import com.google.maps.model.LatLng;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sean on 6/1/15.
 */
public class Destination  implements Serializable {
    private static int nodeId;
    private final int NEAREST_ARRAY_SIZE = 5;

    // location point is the point returned from google reverse geocoding as closest address to the query point
    private LatLngSerial querypt;                       // decimal degrees
    private LatLngSerial locationpt;

    private ArrayList<Destination> nearestNeighbor = new ArrayList<Destination>();
    private int timefromorigin;                         // seconds
    private long distancefromorigin;                     // meters
    private int initialbearing;                         // degrees from true N

    // find gradiant in travel time between to destination points
    public static double travelTimeGradiant(Destination dest1, Destination dest2) {
        double timediff = Math.abs(dest1.getTimefromorigin() - dest2.getTimefromorigin());
        double distance = NavFormulas.distBetweenPts(dest1.locationpt.toLatLng(), dest2.locationpt.toLatLng());
        double gradiant = timediff / distance;

        return gradiant;
    }

    public LatLng getQueryPt() {
        return this.querypt.toLatLng();
    }
    public void setQueryPt(LatLng query) {
        this.querypt = LatLngSerial.toLatLngSerial(query);
    }

    public LatLng getLocationpt() {
        return this.locationpt.toLatLng();
    }
    public void setLocationpt(LatLng origin, LatLng locationpt) {
        this.locationpt = LatLngSerial.toLatLngSerial(locationpt);
        setDistancefromorigin(origin);
        setInitalBearing(origin);
    }

    public int getTimefromorigin() {
        return this.timefromorigin;
    }
    public void setTimefromorigin(int timefromorigin) {
        this.timefromorigin = timefromorigin;
    }

    public long getDistancefromorigin() {
        return this.distancefromorigin;
    }
    public void setDistancefromorigin(LatLng origin) {
        this.distancefromorigin = Math.round(NavFormulas.distBetweenPts(origin, this.getLocationpt()));
    }


    public int getInitialBearing() {
        return this.initialbearing;
    }
    public void setInitalBearing(LatLng origin) {
        this.initialbearing = (int) Math.round(NavFormulas.initBearing(origin, this.getLocationpt()));
    }

}
