import Utility.MetaObject;
import Utility.MomentumDir;
import Utility.MomentumXY;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class TestMain {
    public static void main(String[] args) {
        /*
        MomentumXY vec = new MomentumXY(2, -5);
        MomentumDir vecDir = vec.ToMomentumDir();

        System.out.println(Math.toDegrees(vecDir.getDir()));
        System.out.println(vecDir.getLen());

        MomentumXY vec2 = vecDir.ToMomentumXY();
        System.out.println(vec2.getX());
        System.out.println(vec2.getY());
        */

        MetaObject GameObj = new MetaObject();



        try (var inputStream = MetaObject.class.getResourceAsStream("/config.properties")) {
            if (inputStream != null) {
                // Reading properties
                var props = new Properties();
                props.load(inputStream);
                String localeString = props.getProperty("locale", "en");
                Locale.setDefault(new Locale(localeString));
            } else {
                throw new IllegalStateException("No configuration file found!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ResourceBundle message = ResourceBundle.getBundle("i18n/text");
        System.out.println(message.getString("lang_test"));
    }
}
