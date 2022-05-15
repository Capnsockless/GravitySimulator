import Objects.Space;
import Utility.GUI;
import Utility.threads.StepRunnable;

import static java.lang.Thread.MAX_PRIORITY;

public class Launcher {
    public static void main(String[] args) {
        Runnable spaceRunnable = new StepRunnable();
        Thread spaceThread = new Thread(spaceRunnable, "Physics");
        spaceThread.setDaemon(true);
        spaceThread.setPriority(MAX_PRIORITY*3/4);

        spaceThread.start();
    }
}
