import com.google.maps.model.LatLng;
import junit.framework.TestCase;
import junit.framework.TestResult;
import net.rebootu.timemap.controllers.PolylineEncoding;
import org.junit.Test;

/**
 * Created by sean on 6/16/15.
 */
public class PolylineEncodingTest extends TestCase {
    @Test
    public void testEncodeGoogle() {
        assertEquals("_p~iF", PolylineEncoding.encode(38.5));
        assertEquals("~ps|U", PolylineEncoding.encode(-120.2));
        assertEquals("_ulL", PolylineEncoding.encode(2.2));
        assertEquals("nnqC", PolylineEncoding.encode(-0.75));
        assertEquals("_mqN", PolylineEncoding.encode(2.552));
        assertEquals("vxq`@", PolylineEncoding.encode(-5.503));
    }

    public void testEncode0offset() {
        assertEquals("?", PolylineEncoding.encode(0.0));
    }

    public void testEncodeBackslash() {
        assertEquals("\\", PolylineEncoding.encode(-0.00015));
    }

    public void testEscapeBackslash() {
        String teststr = PolylineEncoding.encode(-0.00015);
        assertEquals("\\\\", PolylineEncoding.escapeBackslash("\\"));
        assertEquals("\\\\", PolylineEncoding.escapeBackslash(teststr));
    }

    public void testEncodePolyline() {
        LatLng[] endpts = new LatLng[3];
        endpts[0] = new LatLng(38.5, -120.2);
        endpts[1] = new LatLng(40.7, -120.95);
        endpts[2] = new LatLng(43.252, -126.453);

        assertEquals("_p~iF~ps|U_ulLnnqC_mqNvxq`@", PolylineEncoding.encodePolyline(endpts));
    }
}
