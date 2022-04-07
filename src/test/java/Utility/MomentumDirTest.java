package Utility;

import junit.framework.TestCase;
import org.junit.Test;

public class MomentumDirTest extends TestCase {

    @Test
    public void testToMomentumXY() {
        MomentumDir testMomentum = new MomentumDir(0, 5);

        assertEquals(5, testMomentum.ToMomentumXY().getX(), 0.0001);
        assertEquals(0, testMomentum.ToMomentumXY().getY(), 0.0001);

        testMomentum.len = 6;
        assertEquals(6, testMomentum.ToMomentumXY().getX(), 0.0001);
        assertEquals(0, testMomentum.ToMomentumXY().getY(), 0.0001);

        testMomentum.dir = Math.PI;
        assertEquals(-6, testMomentum.ToMomentumXY().getX(), 0.0001);
        assertEquals(0, testMomentum.ToMomentumXY().getY(), 0.0001);

        testMomentum.dir = Math.PI/2;
        assertEquals(0, testMomentum.ToMomentumXY().getX(), 0.0001);
        assertEquals(6, testMomentum.ToMomentumXY().getY(), 0.0001);

    }

    @Test
    public void testMultiplyDistance() {
        MomentumDir testMomentum = new MomentumDir(4, 3);

        testMomentum.MultiplyDistance(2);
        assertEquals(6.0, testMomentum.getLen());

        testMomentum.MultiplyDistance(0.5);
        assertEquals(3.0, testMomentum.getLen());

        testMomentum.MultiplyDistance(-3);
        assertEquals(-9.0, testMomentum.getLen());

        testMomentum.MultiplyDistance(6.4);
        assertEquals(-57.6, testMomentum.getLen());
    }
}