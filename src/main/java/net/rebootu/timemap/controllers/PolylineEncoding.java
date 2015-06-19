package net.rebootu.timemap.controllers;

import com.google.maps.model.LatLng;

/**
 * Created by Sean on 6/15/15.
 */
public class PolylineEncoding {

    /**
     * String encodePolyline(LatLng[]) Creates a string of characters that encode the lat & lng for each end point of a polyline
     * @param endpts and array of LatLng values for each end point in the polyline
     * @return String with the encoded characters for all lat/lng pairs in the polyline
     */
    public static String encodePolyline (LatLng[] endpts) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(encode(endpts[0].lat));
        stringBuilder.append(encode(endpts[0].lng));

        for (int i = 1, n = endpts.length; i < n; i++) {
            double lat = endpts[i].lat - endpts[i-1].lat;
            double lng = endpts[i].lng - endpts[i-1].lng;
            stringBuilder.append(encode(lat));
            stringBuilder.append(encode(lng));
        }

        return stringBuilder.toString();
    }

    /**
     * String encode(double) Encodes an angle (lat, lng, or delta) as a character string using the method described at:
     *     https://developers.google.com/maps/documentation/utilities/polylinealgorithm
     * @param angle double which is the lat/lng angle or angle difference to be encoded
     * @return String with the encoded characters for single angle or delta value
     */
    public static String encode (double angle) {
        // converts lat/lng angle to 5 decimal places to an signed two-compliments integer
        int temp = (int) Math.round(angle * 1.0e5);

        // bitwise left shift
        temp = (temp << 1);

        // invert bits if angle is negative
        if (angle < 0)
            temp = ~temp;

        char[] letters = new char[6];
        int i = 0;

        // used do-while so loop stops when no more chunks with information in them after the first
         do {
                // chunk out a 5 bit segment, this also reverses order of chunks
                letters[i] = (char) (temp & (0x1F));
                // right shift without sign extension to remove lowest 5 bits
                temp = temp >>> 5;

                 // set 6th bit high to signal if another chunk follows this one
                 if (temp > 0)
                    letters[i] = (char) (letters[i] | (0x20));

                // add 63
                letters[i] += '?';
                i++;
        } while (temp != 0);

        // return string made first i characters of the letters char array
        return new String(letters, 0, i);
    }

    /**
     * String escapeBackslash(String) escapes any backslash characters in the argument string
     * @param string String to have backslash characters escaped
     * @return String with any '\' characters escaped to "\\"
     */
    public static String escapeBackslash (String string) {
        return string.replace("\\", "\\\\");
    }
}
