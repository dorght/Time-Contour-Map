package net.rebootu.timemap.controllers;

import com.google.maps.model.LatLng;

import java.util.ArrayList;

import static net.rebootu.timemap.controllers.GoogleMapsApiKey.getGoogleMapsApiKey;

/**
 * Created by sean on 6/8/15.
 */
public class GoogleQuery {

    // get array of query points and assemble query string
    public static String getQueryString(LatLng origin, ArrayList<Destination> destinations) {
        final int MAX_DESTINATIONS = 79;
        final String API_KEY = getGoogleMapsApiKey();

        // verify arguments are in range
        // the lat, lng of destination points are assumed to be in range since they are normalized when calculated in NavFormulas class
        if (origin.lat < -90.0 || origin.lat > 90.0)
            throw new IllegalArgumentException("Origin's latitude value exceeds +/- 90 deg");
        else if (origin.lng < -180.0 || origin.lng > 180.0)
            throw new IllegalArgumentException("Origin's longitude value exceeds +/- 180 deg");
        else if (destinations.size() > MAX_DESTINATIONS)
            throw new IllegalArgumentException("URL string can accommodate at most 79 destinations");


        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://maps.googleapis.com/maps/api/distancematrix/json?");        // 57 char
        stringBuilder.append("origins=");                                                         //  7 char
        stringBuilder.append(origin.toString());                                                  //

        stringBuilder.append("&destinations=");                                                   // 14 char
        for (Destination destination : destinations) {
            stringBuilder.append(destination.getQueryPt().toString());                            // 22 char per (max)
            stringBuilder.append("|");                                                            //  1 char per
        }

        // delete last |
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);                                    // -1 char
        stringBuilder.append("&units=metric");                                                    // 13 char
        stringBuilder.append("&key=");                                                            //  5 char
        stringBuilder.append(API_KEY);                                                            // 86 char
                                                                                                  // ---------
                                                                                                  // URL length limit of 2000 characters
                                                                                                  // allows at most 79 destinations
        return stringBuilder.toString();
    }
}
