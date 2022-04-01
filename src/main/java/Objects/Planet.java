package Objects;

import Utility.GameObject;
import Utility.MomentumDir;
import Utility.MomentumXY;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Planet extends GameObject {

    public double mass;
    private MomentumXY currMomentum = new MomentumXY(0, 0);

    public Planet(double x, double y, double radius) {
        super(x, y, radius);
        this.mass = radius*radius*Math.PI;
    }

    //Calculates a = G*(m1+m2)/R1+R2 and multiplies the MomentumDir object to the value
    private MomentumDir calculateMomentumDir(Planet otherplanet){
        double distance = pointDistance(otherplanet.getX(), otherplanet.getY());
        double force = Space.GravConstant*((mass + otherplanet.mass)/distance);

        MomentumDir rawDirection = new MomentumDir(pointDirection(otherplanet.getX(), otherplanet.getY()), distance);
        rawDirection.MultiplyDistance(force);
        return rawDirection;
    }


    @Override
    public void stepEvent() {
        super.stepEvent();

        for (GameObject obj: Space.Instances) {
            if (obj instanceof Planet && this != obj) {
                //Add momentum from each gravitational force
                MomentumDir mDir = calculateMomentumDir((Planet) obj);
                currMomentum.SumMomentum(mDir.ToMomentumXY());

                //Collision
                if (pointDistance(obj.getX(), obj.getY()) <= (getRadius() + obj.getRadius())){

                }
            }
        }
    }
}
