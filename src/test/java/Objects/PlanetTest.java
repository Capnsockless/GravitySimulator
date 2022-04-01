package Objects;

import junit.framework.TestCase;
import org.junit.Test;

public class PlanetTest extends TestCase {

    @Test
    public void testpointDistance() {
        Planet Earth = new Planet(0, 0, 0);

        assertEquals(5.0, Earth.pointDistance(3, 4));
        assertEquals(5.0, Earth.pointDistance(4, 3));
        assertEquals(10.0, Earth.pointDistance(6, 8));
        assertEquals(13.0, Earth.pointDistance(12, 5));
        assertEquals(13.0, Earth.pointDistance(5, 12));

        Earth.setX(3);
        Earth.setY(3);

        assertEquals(5.0, Earth.pointDistance(6, 7));
        assertEquals(5.0, Earth.pointDistance(7, 6));
        assertEquals(10.0, Earth.pointDistance(9, 11));
        assertEquals(13.0, Earth.pointDistance(15, 8));
        assertEquals(13.0, Earth.pointDistance(8, 15));

        Earth.setY(-3);

        assertEquals(5.0, Earth.pointDistance(6, 1));
        assertEquals(5.0, Earth.pointDistance(7, 0));
        assertEquals(10.0, Earth.pointDistance(9, 5));
        assertEquals(13.0, Earth.pointDistance(15, 2));
        assertEquals(13.0, Earth.pointDistance(8, 9));
    }

    @Test
    public void testpointDirection(){
        Planet Earth = new Planet(0, 0, 0);

        assertEquals(1.047198, Earth.pointDirection(1, 1.7320508), 0.0001);
        assertEquals(2.0943951024, Earth.pointDirection(-1, 1.7320508), 0.0001);
        assertEquals(-1.047198, Earth.pointDirection(1, -1.7320508), 0.0001);
        assertEquals(-2.0943951024, Earth.pointDirection(-1, -1.7320508), 0.0001);

    }

}