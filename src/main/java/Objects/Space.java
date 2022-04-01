package Objects;

import Utility.GameObject;

import java.util.ArrayList;
import java.util.List;

public class Space {

    static public List<GameObject> Instances = new ArrayList();
    static public double GravConstant = 1;
    static public int FPS = 30;

    //Will be run each step to have every object take a step
    public void runStep(){
        for (GameObject obj : Instances) {
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
        Instances.add(obj);
    }

    static public void removeInstance(int instanceID){
        Instances.remove(findInstance(instanceID));
    }
}
