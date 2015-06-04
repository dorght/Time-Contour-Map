package net.rebootu.timemap.controllers;

import com.google.maps.model.LatLng;

/**
 * Created by sean on 6/3/15.
 */
public class UserLocation {
    // TODO picked pt

    // geoCoding at get LatLng from address

    // geoLocationAPI


    public static LatLng userLocation() {
        // TODO using home temporarily, set in environment variable so doesn't get github'd
        double lat = Double.parseDouble(System.getenv("HOME_LAT"));
        double lng = Double.parseDouble(System.getenv("HOME_LNG"));
        LatLng origin = new LatLng(lat, lng);

        return origin;
    }

}
