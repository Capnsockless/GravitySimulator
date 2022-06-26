package Objects;

import Utility.GameObject;
import Utility.MomentumDir;
import Utility.MomentumXY;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

@Getter
@Setter
public class Planet extends GameObject {
    private static final Logger logger = (Logger) LogManager.getLogger(Planet.class);

    public double mass;
    private MomentumXY currMomentum = new MomentumXY(0, 0);

    public Planet(double x, double y, double radius) {
        super(x, y, radius);
        this.mass = radius*radius*Math.PI;

        logger.info("New Planet was created. ID:{}", getInstanceID());
    }

    //Calculates a = G*(m1+m2)/R1+R2 and multiplies the MomentumDir object to the value
    private MomentumDir calculateMomentumDir(Planet otherplanet){
        double distance = pointDistance(otherplanet.getX(), otherplanet.getY());
        double force = Space.GravConstant*((mass + otherplanet.mass)/distance);

        MomentumDir rawDirection = new MomentumDir(pointDirection(otherplanet.getX(), otherplanet.getY()), distance);
        rawDirection.MultiplyDistance(force);

        return rawDirection;
    }

    //"Destroys" the planet
    @Override
    public void implode(){
        logger.info("Planet ID:{} has imploded", getInstanceID());
        x = -300;
        y = -300;
        radius = 0;
        mass = 0;
        currMomentum = new MomentumXY(0, 0);
    }

    @Override
    public synchronized void stepEvent() {
        super.stepEvent();

        //Bounce off walls
        if (x < 0 || x > 1385.0-radius) currMomentum.multMomentumX(-1);
        if (y < 0 || y > 797.0-radius) currMomentum.multMomentumY(-1);

        x += currMomentum.getX();
        y += currMomentum.getY();

        for (GameObject obj: Space.Instances) {
            if (obj instanceof Planet && getInstanceID() != obj.getInstanceID()) {
                //Add momentum from each gravitational force
                MomentumDir mDir = calculateMomentumDir((Planet) obj);
                currMomentum.SumMomentum(mDir.ToMomentumXY());
            }
        }
    }

    public Color getColor() {
        return color;
    }
}
