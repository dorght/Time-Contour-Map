package net.rebootu.timemap.controllers;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import com.google.maps.model.Unit;

import java.util.ArrayList;

import static net.rebootu.timemap.controllers.GoogleMapsApiKey.getGoogleMapsApiKey;

/**
 * Created by sean on 6/1/15.
 */
public class GoogleMapsDistanceMatrix {

    public int[] distanceMatrix (LatLng[] dest) {
        int[] distances = new int[dest.length];
        GeoApiContext context = new GeoApiContext().setApiKey(getGoogleMapsApiKey());
        LatLng origin = UserLocation.userLocation();

        DistanceMatrixApiRequest distancerequest = new DistanceMatrixApiRequest(context);
        distancerequest.units(Unit.METRIC)
                       .mode(TravelMode.DRIVING)
                       .origins(origin);

//      distancerequest.destinations();


        return distances;
    }

}
