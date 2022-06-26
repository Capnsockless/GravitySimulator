package Objects;

import Utility.Exceptions.GraphicsContextMissingException;
import Utility.GameObject;
import javafx.scene.canvas.GraphicsContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.ArrayList;

public class Space {
    static public ArrayList<GameObject> Instances = new ArrayList<>();
    static public double GravConstant = 0.00001D;
    static public boolean CollisionsActive = true;
    static public boolean pause = false;
    static public boolean end = false;
    static public boolean trail = false;
    static private int FPS = 30;
    public static GraphicsContext gc;
    static private boolean gcExists = false;

    private static final org.apache.logging.log4j.core.Logger logger = (Logger) LogManager.getLogger(Space.class);

    //Will be run each step to have every object take a step
    public static synchronized void runStep() throws GraphicsContextMissingException{
        if (!gcExists) throw new GraphicsContextMissingException(logger);
        logger.trace("Space step ran.");
        //System.out.println("Space step ran")

        //Clear canvas
        if (!trail) gc.clearRect(0, 0, 1400, 880);

        for (int i=0; i < Instances.size(); i++) {
            GameObject obj = Instances.get(i);
            if (obj == null) continue;

            //Collisions
            if (!pause) {
                for (int j = 0; j < Instances.size(); j++) {
                    GameObject obj2 = Instances.get(j);
                    if (obj2 == null || obj == obj2) continue;

                    //Destroy random planet once they collide
                    if (CollisionsActive && obj.pointDistance(obj2.getX(), obj2.getY()) <= obj.getRadius() + obj2.getRadius()) {
                        if (obj.getRadius() < obj2.getRadius()) removeInstance(obj.getInstanceID());
                        else removeInstance(obj2.getInstanceID());
                        j--;

                        System.gc();
                        logger.info("A planet was destroyed.");
                    }
                }
                obj.stepEvent();
            }
            gc.setFill(obj.getColor());
            gc.fillOval(obj.getX(), obj.getY(), obj.getRadius(), obj.getRadius());
        }
    }

    static public GameObject findInstance(int instanceID){
        for (GameObject obj : Instances) {
            if (obj.getInstanceID() == instanceID) return obj;
        }
        return null;
    }

    static public void addInstance(GameObject obj){
        if (obj == null) return;
        Instances.add(obj);
    }

    static public void removeInstance(int instanceID){
        GameObject toRemove = findInstance(instanceID);
        if (toRemove == null) return;
        toRemove.implode();
        Instances.remove(toRemove);
    }

    static public void togglePause(){ pause = !pause; }

    static public void toggleTrail(){ trail = !trail; }

    static public void endSimulation(){ end = true; }

    static public void resetSimulation(){
        for (int i=0; i < Instances.size(); i++) {
            GameObject obj = Instances.get(i);
            if (obj == null) continue;
            removeInstance(obj.getInstanceID());
        }
    }

    public static int getFPS() { return FPS; }
    public static void setGc(GraphicsContext _gc){
        gc = _gc;
        gcExists = true;
    }
}
