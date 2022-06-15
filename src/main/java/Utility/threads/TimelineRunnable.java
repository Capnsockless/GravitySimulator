package Utility.threads;

import Objects.Space;
import Utility.Exceptions.GraphicsContextMissingException;

public class TimelineRunnable implements Runnable{

    public TimelineRunnable() { }

    @Override
    public void run(){
        try {
            Space.runStep();
        } catch (GraphicsContextMissingException e) {
            e.printStackTrace();
        }
    }
}
