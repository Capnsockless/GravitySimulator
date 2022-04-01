package Utility;

import Objects.Space;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
@NoArgsConstructor
public abstract class GameObject {
    int instanceID;

    double x, y;
    double radius;

    public GameObject(double x, double y, double radius){
        this.x = x;
        this.y = y;
        this.radius = radius;
        instanceID = generateID();
    }

    private int generateID(){
        Random rand = new Random();
        int newID = rand.nextInt(999999);
        while (Space.findInstance(newID) != null){
            newID = rand.nextInt(999999);
        }
        Space.addInstance(this);
        return newID;
    }

    public void stepEvent(){ }

    public double pointDistance(double _x, double _y){
        return Math.sqrt(Math.pow(x-_x, 2)+Math.pow(y-_y, 2));
    }

    public double pointDirection(double _x, double _y){
        return Math.atan2(_y-y, _x-x);
    }
}
