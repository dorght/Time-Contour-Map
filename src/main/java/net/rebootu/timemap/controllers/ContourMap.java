package net.rebootu.timemap.controllers;

import com.google.maps.model.LatLng;
import sun.security.krb5.internal.crypto.Des;

/**
 * Created by sean on 6/3/15.
 */
public class ContourMap {

//    Contour[] contours;

//    public calculateConours() {
//    }


//    public ContourMap(UserLocation userLoc) {
//        this.userLocation = userLoc;
//        this.destination = getInitialQueryPts(userLoc.getLatLong(), this.radius);
//    }

    // adapted from www.movable-type.co.uk/scripts/latlong.html
    public static Destination[] getInitialQueryPts(LatLng origin, int mapradius) {
        final int POINTS = 100;
        final int LEVELS = 4;

        return getInitialQueryPts(origin, mapradius, POINTS, LEVELS);
    }

    public static Destination[] getInitialQueryPts(LatLng origin, int mapradius, int points, int levels) {
        final double EARTH_R = 6371e3;          // mean radius of earth in meters

        Destination[] querypts = new Destination[points];
        int point = 0;

        double sumcircumferences = Math.PI * (mapradius) * (levels + 1);
        double arclength = sumcircumferences / points;

        for (int i = 0; i < levels; i++) {
            double d = mapradius / levels * (i + 1);
            double delta = d / EARTH_R;
            double arcangle = arclength / d;      // 2D subtended angle in radians
            double arcsum = 0;

            while (arcsum < 2.0 * Math.PI) {
                // find latitude and longitude of point given start point, distance, and initial bearing
                double lat1 = Math.toRadians(origin.lat);
                double lng1 = Math.toRadians(origin.lng);
                double lat2 = Math.asin(Math.sin(lat1) * Math.cos(delta) +
                        Math.cos(lat1) * Math.sin(delta) * Math.cos(arcsum));
                double lng2 = lng1 + Math.atan2(Math.sin(arcsum) * Math.sin(delta) * Math.cos(lat1),
                        Math.cos(delta) - Math.sin(lat1) * Math.sin(lat2));


                // convert latitude to decimal degrees (+ is N, - is S)
                lat2 = Math.toDegrees(lat2);
                // convert longitude to decimal degrees and normalize to +/- 180deg (+ is E, - is W)
                lng2 = Math.toDegrees(lng2);
                if (lng2 < -181)
                    lng2 = 360 + lng2;
                else if (lng2 > 180)
                    lng2 = lng2 - 360;

                LatLng pt = new LatLng(lat2, lng2);
                Destination querypt = new Destination();
                querypt.setQueryPt(pt);
                querypts[point++] = querypt;
                arcsum += arcangle;
            }
        }
        return querypts;
    }

}
