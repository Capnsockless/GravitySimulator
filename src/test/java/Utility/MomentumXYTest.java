package Utility;

import junit.framework.TestCase;
import org.junit.Test;

public class MomentumXYTest extends TestCase {

    @Test
    public void testToMomentumDir() {
        MomentumXY testMomentum = new MomentumXY(0, 6);

        assertEquals(Math.PI/2, testMomentum.ToMomentumDir().getDir(), 0.0001);
        assertEquals(6, testMomentum.ToMomentumDir().getLen(), 0.0001);

        testMomentum.x = 6;
        assertEquals(Math.PI/4, testMomentum.ToMomentumDir().getDir(), 0.0001);
        assertEquals(8.485281374, testMomentum.ToMomentumDir().getLen(), 0.0001);

        testMomentum.y = 0;
        assertEquals(0, testMomentum.ToMomentumDir().getDir(), 0.0001);
        assertEquals(6, testMomentum.ToMomentumDir().getLen(), 0.0001);
    }

    @Test
    public void testSumMomentum() {
        MomentumXY testMomentum1 = new MomentumXY(5, 6);
        MomentumXY testMomentum2 = new MomentumXY(2.5, -3);
        MomentumXY testMomentum3 = new MomentumXY(-5, 10);

        testMomentum1.SumMomentum(testMomentum2);
        assertEquals(7.5, testMomentum1.getX());
        assertEquals(3.0, testMomentum1.getY());

        testMomentum2.SumMomentum(testMomentum3);
        assertEquals(-2.5, testMomentum2.getX());
        assertEquals(7.0, testMomentum2.getY());

        testMomentum1.SumMomentum(testMomentum3);
        assertEquals(2.5, testMomentum1.getX());
        assertEquals(13.0, testMomentum1.getY());
    }
}