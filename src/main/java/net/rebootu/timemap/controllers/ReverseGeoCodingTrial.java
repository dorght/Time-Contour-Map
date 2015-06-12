package net.rebootu.timemap.controllers;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.LocationType;
import sun.security.krb5.internal.crypto.Des;

import java.io.*;

import static com.google.maps.GeocodingApi.reverseGeocode;
import static net.rebootu.timemap.controllers.ContourMap.getInitialQueryPts;
import static net.rebootu.timemap.controllers.GoogleMapsApiKey.getGoogleMapsApiKey;

/**
 * Created by sean on 6/9/15.
 */
public class ReverseGeoCodingTrial {


    public static void main(String args[]) throws IOException {
        GeoApiContext context = new GeoApiContext().setApiKey(getGoogleMapsApiKey());
        LatLng origin = new LatLng(Double.parseDouble(System.getenv("HOME_LAT")), Double.parseDouble(System.getenv("HOME_LNG")));

        Destination[] querypts = getInitialQueryPts(origin, 46510, 10, 2);



        for (Destination query : querypts) {


            try {
                GeocodingResult[] results = GeocodingApi.reverseGeocode(context, query.getQueryPt())
                                                        .locationType(LocationType.ROOFTOP, LocationType.RANGE_INTERPOLATED)
                                                        .await();
                query.setLocationpt(results[0].geometry.location);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        FileOutputStream fileStream = null;
        try {
            fileStream = new FileOutputStream("dest_pts.ser");
            ObjectOutputStream os = new ObjectOutputStream(fileStream);
            os.writeObject(querypts);
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
