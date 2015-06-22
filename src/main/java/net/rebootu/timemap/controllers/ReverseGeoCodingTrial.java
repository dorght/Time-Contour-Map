package net.rebootu.timemap.controllers;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.LocationType;

import java.io.*;
import java.util.ArrayList;

import static net.rebootu.timemap.controllers.ContourMap.getInitialQueryPts;
import static net.rebootu.timemap.controllers.GoogleMapsApiKey.getGoogleMapsApiKey;

/**
 * Created by sean on 6/9/15.
 */
public class ReverseGeoCodingTrial {


    public static void main(String args[]) throws IOException {
        GeoApiContext context = new GeoApiContext().setApiKey(getGoogleMapsApiKey());
        LatLng origin = UserLocation.userLocation();

        ArrayList<Destination> querypts = getInitialQueryPts(origin, 46510, 25, 1);
        GeocodingResult[] results = null;


        for (int i = 0; i < querypts.size(); i++) {
            try {
                results = GeocodingApi.reverseGeocode(context, querypts.get(i).getQueryPt())
                                      .locationType(LocationType.ROOFTOP, LocationType.RANGE_INTERPOLATED)
                                      .await();
                if (results.length != 0) {
                    LatLng locationpt = results[0].geometry.location;
                    querypts.get(i).setLocationpt(origin, locationpt);
                }
                else
                    querypts.remove(i--);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        LatLng[] polylinepts = new LatLng[querypts.size()];
        int i = 0;
        for (Destination query: querypts) {
            polylinepts[i++] = query.getLocationpt();
        }

        String polyline = PolylineEncoding.encodePolyline(polylinepts);
        String URL = "https://maps.googleapis.com/maps/api/staticmap?"
                                                              //      + "center=" + origin.toString()
                                                              //      + "&zoom=11"
                                                                    + "size=600x600"
                                                                    + "&path=fillcolor:0xAA000033"
                                                                        + "|color:0xFFFFFF00"
                                                                        + "|enc:"
                                                                            + polyline
                                                                    + "&path=fillcolor:0x00AA0033"
                                                                        + "|color:0xFFFFFF00"
                                                                        + "|enc:"
                                                                            + "_}tjFvhmgPut@a|Q`cFeqPfvP~\\j~Fj{MabCdqP_aM`dC"
                                                                    + "&path=fillcolor:0x0000AA33"
                                                                        + "|color:0xFFFFFF00"
                                                                        + "|enc:"
                                                                            + "s`fjFrpigPw}HflA??gvAykJ~m@kmG|uCgjBxdGwXtt@xtAjq@hxH";
System.out.println(URL);
System.out.println("URL length is " + URL.length());

//        FileOutputStream fileStream = null;
//        try {
//            fileStream = new FileOutputStream("destination_pts.ser");
//            ObjectOutputStream os = new ObjectOutputStream(fileStream);
//            os.writeObject(querypts);
//            os.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
    }
}
