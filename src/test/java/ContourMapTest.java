import com.google.maps.model.LatLng;
import junit.framework.TestCase;
import junit.framework.TestResult;
import net.rebootu.timemap.controllers.ContourMap;
import net.rebootu.timemap.controllers.Destination;
import org.junit.Test;


/**
 * Created by sean on 6/4/15.
 */
public class ContourMapTest extends TestCase {
    @Test
    public void testOrdinalsFrom0_0() {
        ContourMap map = new ContourMap();
        LatLng origin = new LatLng(0.0, 0.0);
        // 111195 meters in deg of arc @ mean Radius
        Destination[] querypts = map.getInitialQueryPts(origin, 111195, 4, 1);

        assertEquals(1.0, querypts[0].getQueryPt().lat, 0.001);
        assertEquals(0.0, querypts[0].getQueryPt().lng, 0.001);
        assertEquals(0.0, querypts[1].getQueryPt().lat, 0.001);
        assertEquals(1.0, querypts[1].getQueryPt().lng, 0.001);
        assertEquals(-1.0,querypts[2].getQueryPt().lat, 0.001);
        assertEquals(0.0, querypts[2].getQueryPt().lng, 0.001);
        assertEquals(0.0, querypts[3].getQueryPt().lat, 0.001);
        assertEquals(-1.0, querypts[3].getQueryPt().lng, 0.001);
    }

    @Test
    public void testOrdinalsFrom0_180() {
        ContourMap map = new ContourMap();
        LatLng origin = new LatLng(0.0, 180.0);
        // 111195 meters in deg of arc @ mean Radius
        Destination[] querypts = map.getInitialQueryPts(origin, 111195, 4, 1);

        assertEquals(1.0, querypts[0].getQueryPt().lat, 0.001);
        assertEquals(180.0, querypts[0].getQueryPt().lng, 0.001);
        assertEquals(0.0, querypts[1].getQueryPt().lat, 0.001);
        assertEquals(-179.0, querypts[1].getQueryPt().lng, 0.001);
        assertEquals(-1.0,querypts[2].getQueryPt().lat, 0.001);
        assertEquals(180.0, querypts[2].getQueryPt().lng, 0.001);
        assertEquals(0.0, querypts[3].getQueryPt().lat, 0.001);
        assertEquals(179.0, querypts[3].getQueryPt().lng, 0.001);
    }

    @Test
    public void testCrossingNPole() {
        ContourMap map = new ContourMap();
        LatLng origin = new LatLng(89.0, 0.0);
        // 2 degs * 111195 meters in deg of arc @ mean Radius
        Destination[] querypts = map.getInitialQueryPts(origin, 222390, 4, 1);

        // formula used assumed great circle navigation and given an initial bearing
        assertEquals(89.0, querypts[0].getQueryPt().lat, 0.001);
        assertEquals(180.0, querypts[0].getQueryPt().lng, 0.001);
        assertTrue(89.0 > querypts[1].getQueryPt().lat);
        assertEquals(87.0,querypts[2].getQueryPt().lat, 0.001);
        assertEquals(0.0, querypts[2].getQueryPt().lng, 0.001);
        assertTrue(89.0 > querypts[3].getQueryPt().lat);
    }

    @Test
    public void testCrossingSPole() {
        ContourMap map = new ContourMap();
        LatLng origin = new LatLng(-89.0, 0.0);
        // 2 degs * 111195 meters in deg of arc @ mean Radius
        Destination[] querypts = map.getInitialQueryPts(origin, 222390, 4, 1);

        // formula used assumed great circle navigation and given an initial bearing
        assertEquals(-87.0, querypts[0].getQueryPt().lat, 0.001);
        assertEquals(0.0, querypts[0].getQueryPt().lng, 0.001);
        assertTrue(-89.0 < querypts[1].getQueryPt().lat);
        assertEquals(-89.0,querypts[2].getQueryPt().lat, 0.001);
        assertEquals(180.0, querypts[2].getQueryPt().lng, 0.001);
        assertTrue(-89.0 < querypts[3].getQueryPt().lat);
    }

    @Test
    public void testInitialSize() {
        ContourMap map = new ContourMap();
        LatLng origin = new LatLng(38.6, -90.0);
        // use defaults and 30km radius
        Destination[] querypts = map.getInitialQueryPts(origin, 30000);

        // formula used assumed great circle navigation and given an initial bearing
        assertEquals(100, querypts.length);
    }
}
