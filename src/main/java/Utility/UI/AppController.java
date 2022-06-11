package Utility.UI;

import Objects.Planet;
import Objects.Space;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class AppController implements Initializable {
    private static final Logger logger = (Logger) LogManager.getLogger(AppController.class);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void onPause(ActionEvent event){
        Space.togglePause();
        logger.info("Pause toggled. Now Paused: {}", Space.pause);
    }
    @FXML
    public void onEnd(ActionEvent event){
        Space.endSimulation();
        logger.info("Simulation has ended.");
    }
}
