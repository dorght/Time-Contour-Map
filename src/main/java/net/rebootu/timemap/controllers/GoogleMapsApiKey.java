package net.rebootu.timemap.controllers;

import java.util.Map;

/**
 * Created by Sean on 5/27/15.
 */
public class GoogleMapsApiKey {

    public static String getGoogleMapsApiKey() {
        String googleMapsApiKey = System.getenv("GOOGLE_API_KEY");

        if (googleMapsApiKey != null) {
            return googleMapsApiKey;
        }

        // TODO what to do if key not available?
        // exception? redirect to message on getting and setting API key?
        return "sorry no key available";   // place holder
    }
}
