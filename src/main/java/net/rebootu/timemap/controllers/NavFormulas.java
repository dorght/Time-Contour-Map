package net.rebootu.timemap.controllers;

import com.google.maps.model.LatLng;

/**
 * Created by sean on 6/5/15.
 * navigation formulas adapted from www.movable-type.co.uk/scripts/latlong.html
 */
public class NavFormulas {

    /**
     * LatLng Destination (LatLng, double, double) find latitude and longitude of destination point along great circle
     *     route given start point, distance, and initial bearing
     * @param origin the latitude, longitude of the start point
     * @param distance distance to be traveled along a great circle path (meters)
     * @param bearing initial bearing from the origin point of the great circle path (from true N, +cw)
     * @return LatLng
     */
    public static LatLng destination (LatLng origin, double distance, double bearing) {
        final double EARTH_R = 6371e3;          // mean radius of earth in meters
        double delta = distance / EARTH_R;

        double lat1 = Math.toRadians(origin.lat);
        double lng1 = Math.toRadians(origin.lng);
        double lat2 = Math.asin(Math.sin(lat1) * Math.cos(delta) +
                Math.cos(lat1) * Math.sin(delta) * Math.cos(bearing));
        double lng2 = lng1 + Math.atan2(Math.sin(bearing) * Math.sin(delta) * Math.cos(lat1),
                Math.cos(delta) - Math.sin(lat1) * Math.sin(lat2));

        // convert latitude to decimal degrees (+ is N, - is S)
        lat2 = Math.toDegrees(lat2);
        // convert longitude to decimal degrees and normalize to +/- 180deg (+ is E, - is W)
        lng2 = Math.toDegrees(lng2);
        if (lng2 <= -180.0)
            lng2 = 360.0 + lng2;
        else if (lng2 > 180.0)
            lng2 = lng2 - 360.0;

        return new LatLng(lat2, lng2);
    }
}
