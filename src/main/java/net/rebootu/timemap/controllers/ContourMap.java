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

    /**
     * getInitialQueryPts(LatLng, int, int, int)
     * @param origin Map point that all travel times are measured from
     * @param mapradius half of smallest map dimension to be considered (meters)
     * @param points number of initial query points to be generated
     * @param levels number of rings to divide map area into
     * @return an array of initial Destination points filled with beginning query points to submit to Google
     */
    public static Destination[] getInitialQueryPts(LatLng origin, int mapradius, int points, int levels) {

        Destination[] querypts = new Destination[points];
        int point = 0;

        // evenly distribute the total number of points among all the rings
        // a desired arc length between points is calculated from the total length of the circumferences
        // of all the rings divided by the number of points
        double sumcircumferences = Math.PI * (mapradius) * (levels + 1);
        double arclength = sumcircumferences / points;

        for (int ring = 0; ring < levels; ring++) {
            // navigation formulas adapted from www.movable-type.co.uk/scripts/latlong.html
            double dist = mapradius / levels * (ring + 1);
            double arcangle = arclength / dist;      // 2D subtended angle in radians
            double arcsum = 0;

            while (arcsum < 2.0 * Math.PI) {
                // find latitude and longitude of point given start point, distance, and initial bearing
                LatLng destpt = NavFormulas.destination(origin, dist, arcsum);
                Destination querypt = new Destination();
                querypt.setQueryPt(destpt);

                // add query point to the array and increment the sum of arc angles
                querypts[point++] = querypt;
                arcsum += arcangle;
            }
        }
        return querypts;
    }


    /**
     * getInitialQueryPts(LatLng, int) overloaded version to use a set default number of points and levels
     * @param origin Map point that all travel times are measured from
     * @param mapradius half of smallest map dimension to be considered (meters)
     * @return an array of initial Destination points filled with beginning query points to submit to Google
     */
    public static Destination[] getInitialQueryPts(LatLng origin, int mapradius) {
        final int POINTS = 100;
        final int LEVELS = 4;

        return getInitialQueryPts(origin, mapradius, POINTS, LEVELS);
    }
}
