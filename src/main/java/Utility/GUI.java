package Utility;

import java.util.Locale;


public class GUI {
    Locale locale = Locale.getDefault();

    public void changeLanguage(String newLang){
        Locale.setDefault(new Locale(newLang));
        locale = Locale.getDefault();
    }


}
