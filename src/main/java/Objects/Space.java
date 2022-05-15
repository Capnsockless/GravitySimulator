package Objects;

import Utility.GameObject;

import java.util.ArrayList;
import java.util.Random;

public class Space {

    static public ArrayList<GameObject> Instances = new ArrayList();
    static public double GravConstant = 1;
    static public boolean CollisionsActive = true;

    Random rand = new Random();

    //Will be run each step to have every object take a step
    public synchronized void runStep(){
        for (int i=0; i < Instances.size(); i++) {
            GameObject obj = Instances.get(i);
            if (obj == null) continue;

            //Collisions
            for (int j=0; j < Instances.size(); j++){
                GameObject obj2 = Instances.get(j);
                if (obj2 == null || obj == obj2) continue;

                //Destroy random planet once they collide
                if (obj.pointDistance(obj2.getX(), obj2.getY()) <= obj.getRadius() + obj2.getRadius()){
                    if (rand.nextBoolean()){
                        obj.implode();
                        obj = null;
                        i++;
                    }else{
                        obj2.implode();
                        obj2 = null;
                        j++;
                    }

                    System.gc();
                }
            }
            obj.stepEvent();
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
        Instances.remove(toRemove);
    }
}
