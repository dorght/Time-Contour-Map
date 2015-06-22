package net.rebootu.timemap.controllers;

import com.google.maps.model.LatLng;

import java.io.Serializable;
import java.util.Locale;

/**
 * Created by sean on 6/19/15.
 * a serializable version of the LatLng class
 */
public class LatLngSerial implements Serializable {
    public double lat;
    public double lng;

    public LatLngSerial(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public LatLng toLatLng() {
        LatLng pt = new LatLng(this.lat, this.lng);
        return pt;
    }

    public static LatLngSerial toLatLngSerial(LatLng pt) {
        LatLngSerial serialpt = new LatLngSerial(pt.lat, pt.lng);
        return serialpt;
    }

    @Override
    public String toString() {
        return String.format(Locale.ENGLISH, "%f,%f", lat, lng);
    }
}
