package Utility.threads;

import Objects.Space;
import Utility.GUI;

public class StepThread extends Thread{
    Space activeSpace = new Space();
    GUI userInterface = new GUI();

    @Override
    public void run(){
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
