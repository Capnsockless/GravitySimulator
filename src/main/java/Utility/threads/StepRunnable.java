package Utility.threads;

import Objects.Space;
import Utility.GUI;
import Objects.Planet;

public class StepRunnable implements Runnable{
    Space activeSpace = new Space();
    GUI userInterface = new GUI();


    @Override
    public void run(){
        //Testing
        Planet Mars = new Planet(20, 30, 20);
        Planet Venus = new Planet(10, 20, 10);
        activeSpace.addInstance(Mars);


        //Engine loop
        while (!userInterface.end) {
            if (!userInterface.pause) activeSpace.runStep();

            try {
                Thread.sleep(1000 / userInterface.getFPS());
            } catch (InterruptedException e) {
            }
        }
    }
}
