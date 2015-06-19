import com.google.maps.model.LatLng;
import junit.framework.TestCase;
import junit.framework.TestResult;
import net.rebootu.timemap.controllers.NavFormulas;
import org.junit.Test;

/**
 * Created by sean on 6/19/15.
 */
public class NavFormulasTest extends TestCase {
    @Test
    public void testInitBearing() {
        // test due N on prime meridian
        LatLng origin = new LatLng(0.0, 0.0);
        LatLng dest = new LatLng(10.0, 0.0);
        assertEquals(0.0, NavFormulas.initBearing(origin, dest), 0.00001);

        // test due W
        origin = new LatLng(0.0, 0.0);
        dest = new LatLng(0.0, 10.0);
        assertEquals(90.0, NavFormulas.initBearing(origin, dest), 0.00001);

        // test due S
        origin = new LatLng(0.0, 0.0);
        dest = new LatLng(-10.0, 0.0);
        assertEquals(180.0, NavFormulas.initBearing(origin, dest), 0.00001);

        // test due W
        origin = new LatLng(0.0, 0.0);
        dest = new LatLng(0.0, -10.0);
        assertEquals(270.0, NavFormulas.initBearing(origin, dest), 0.00001);

        // cross N pole
        origin = new LatLng(-90.0, 85.0);
        dest = new LatLng(90, 85.0);
        assertEquals(0.0, NavFormulas.initBearing(origin, dest), 0.00001);

        // cross S pole
        origin = new LatLng(90.0, -85.0);
        dest = new LatLng(-90, -85.0);
        assertEquals(180.0, NavFormulas.initBearing(origin, dest), 0.00001);
    }
}
