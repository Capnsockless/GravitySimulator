import Objects.Space;
import Utility.GUI;
import Utility.threads.StepThread;

import static java.lang.Thread.MAX_PRIORITY;

public class Launcher {
    public static void main(String[] args) {
        Thread spaceThread = new StepThread();
        spaceThread.setDaemon(true);
        spaceThread.setPriority(MAX_PRIORITY*3/4);


    }
}
