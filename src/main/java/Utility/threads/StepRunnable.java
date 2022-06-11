package Utility.threads;

import Objects.Space;
import Utility.Exceptions.GraphicsContextMissingException;

import javafx.scene.canvas.GraphicsContext;

public class StepRunnable implements Runnable{
    GraphicsContext gc;

    public StepRunnable(GraphicsContext gc) {
        this.gc = gc;
    }

    @Override
    public void run(){
        try {
            Space.runStep();
        } catch (GraphicsContextMissingException e) {
            e.printStackTrace();
        }
    }
}
