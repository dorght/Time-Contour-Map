import com.google.maps.model.LatLng;
import junit.framework.TestCase;
import net.rebootu.timemap.controllers.ContourMap;
import net.rebootu.timemap.controllers.Destination;
import org.junit.Test;

import java.util.ArrayList;

import static net.rebootu.timemap.controllers.GoogleQuery.getQueryString;

/**
 * Created by sean on 6/8/15.
 */
public class GoogleQueryTest extends TestCase {

    @Test
    public void testGetQueryString() {
        ContourMap map = new ContourMap();

        LatLng origin = new LatLng(Double.parseDouble(System.getenv("HOME_LAT")), Double.parseDouble(System.getenv("HOME_LNG")));
        ArrayList<Destination> querypts = map.getInitialQueryPts(origin, 46510, 4, 1);

        System.out.println(getQueryString(origin, querypts));
    }

    @Test
    public void test79QueryString() {
        ContourMap map = new ContourMap();

        LatLng origin = new LatLng(Double.parseDouble(System.getenv("HOME_LAT")), Double.parseDouble(System.getenv("HOME_LNG")));

        ArrayList<Destination> querypts = map.getInitialQueryPts(origin, 46510, 79, 4);
        System.out.println(getQueryString(origin, querypts));
    }

    @Test
    public void test100QueryString() {
        ContourMap map = new ContourMap();

        LatLng origin = new LatLng(Double.parseDouble(System.getenv("HOME_LAT")), Double.parseDouble(System.getenv("HOME_LNG")));

        ArrayList<Destination> querypts = map.getInitialQueryPts(origin, 46510, 100, 4);
        System.out.println(getQueryString(origin, querypts));
    }

}
