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

        Destination[] querypts = getInitialQueryPts(origin, 46510, 10, 1);
        GeocodingResult[] results = null;


        for (Destination query : querypts) {


            try {
                results = GeocodingApi.reverseGeocode(context, query.getQueryPt())
                                                        .locationType(LocationType.ROOFTOP, LocationType.RANGE_INTERPOLATED)
                                                        .await();
                if (results.length != 0)
                    query.setLocationpt(results[0].geometry.location);
                // TODO else remove (or mark) querypt from array?
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        LatLng[] test = new LatLng[querypts.length];
        int i = 0;
        for (Destination point : querypts) {
            test[i] = point.getLocationpt();
            if (test[i] != null)
                i++;
        }
        LatLng[] test2 = new LatLng[i];
        for (int j = 0; j < i; j++) {
            test2[j] = test[j];
        }

        String polyline = PolylineEncoding.encodePolyline(test2);
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
