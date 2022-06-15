package Utility;

import Objects.Space;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
@NoArgsConstructor
public abstract class GameObject {
    int instanceID;

    protected double x;
    protected double y;
    protected double radius;
    public Color color;

    public GameObject(double x, double y, double radius){
        this.x = x;
        this.y = y;
        this.radius = radius;
        instanceID = generateID();

        Random rand = new Random();
        int r = (int)((rand.nextFloat()*0.8F + 0.2F)*255);
        int g = (int)((rand.nextFloat()*0.8F + 0.2F)*255);
        int b = (int)((rand.nextFloat()*0.8F + 0.2F)*255);

        this.color = Color.rgb(r, g, b, 1);
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

    public synchronized void stepEvent(){ }

    public double pointDistance(double _x, double _y){
        return Math.sqrt(Math.pow(x-_x, 2)+Math.pow(y-_y, 2));
    }

    public double pointDirection(double _x, double _y){
        return Math.atan2(_y-y, _x-x);
    }

    //"Destroys" the object
    public void implode(){
        x = -300;
        y = -300;
        radius = 0;
    }

    public abstract Paint getColor();
}
