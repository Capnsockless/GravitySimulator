package Utility;

import java.util.Locale;


public class GUI {
    public boolean pause = false;
    public boolean end = false;
    private int FPS = 30;


    Locale locale = Locale.getDefault();


    public void changeLanguage(String newLang){
        Locale.setDefault(new Locale(newLang));
        locale = Locale.getDefault();
    }

    public void togglePause(){ pause = !pause; }

    public void endSimulation(){ end = true; }

    public void setFPS(int FPS) {
        this.FPS = FPS;
    }

    public int getFPS() {
        return FPS;
    }
}
