import Objects.Planet;
import Objects.Space;
import Utility.Exceptions.GraphicsContextMissingException;
import Utility.UI.AppController;
import Utility.threads.StepRunnable;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.IOException;

import static java.lang.Thread.MAX_PRIORITY;

public class AppLauncher extends Application {

    static StackPane root = new StackPane();
    static Scene scene = new Scene(root, 1366, 800);
    private static final org.apache.logging.log4j.core.Logger logger = (Logger) LogManager.getLogger(AppLauncher.class);
    static GraphicsContext gc;

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppLauncher.class.getResource("UIDesign/CanvasPane.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        Pane mainPane;
        Node board = scene.lookup("#spaceBoard");
        Canvas canvas = ((Canvas)board);
        gc = canvas.getGraphicsContext2D();
        Space.setGc(gc);

        AppController controller = fxmlLoader.getController();

        primaryStage.setTitle("Gravity Simulator");
        primaryStage.setScene(scene);

        canvas.setOnMouseClicked(e -> Space.addInstance(new Planet(e.getX(), e.getY(), 5)));

        primaryStage.show();

        Space activeSpace = new Space();
        Runnable spaceRunnable = new StepRunnable(gc);
        Thread spaceThread = new Thread(spaceRunnable, "Physics");
        spaceThread.setDaemon(true);
        spaceThread.setPriority(MAX_PRIORITY * 3 / 4);

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(Space.getFPS()), e -> run(gc, spaceThread)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        logger.info("Simulation has started.");
    }

    @SneakyThrows
    private void run(GraphicsContext gc, Thread thr){
        if (!Space.end) {
            try {
                Space.runStep();
            } catch (GraphicsContextMissingException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        logger.info("Application has started.");
        launch();

        logger.info("Simulation has ended.");
    }
}
