import Objects.Planet;
import Objects.Space;
import Utility.I18N;
import Utility.UI.AppController;
import Utility.threads.TimelineRunnable;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;

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

        Node board = scene.lookup("#spaceBoard");
        Canvas canvas = ((Canvas)board);
        gc = canvas.getGraphicsContext2D();
        Space.setGc(gc);

        AppController controller = fxmlLoader.getController();

        primaryStage.titleProperty().bind(I18N.createStringBinding("title"));
        primaryStage.setScene(scene);

        Button pauseBtn = (Button) scene.lookup("#pauseBtn");
        pauseBtn.textProperty().bind(I18N.createStringBinding("pause"));
        Button resetBtn = (Button) scene.lookup("#resetBtn");
        resetBtn.textProperty().bind(I18N.createStringBinding("reset"));
        Button endBtn = (Button) scene.lookup("#endBtn");
        endBtn.textProperty().bind(I18N.createStringBinding("end"));

        ChoiceBox<String> langSelect = (ChoiceBox) scene.lookup("#langSelect");
        langSelect.getItems().addAll("English", "ქართული");
        langSelect.setValue("English");
        langSelect.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                String newLang = langSelect.getItems().get((Integer) number2);
                if (newLang == "English") I18N.setLocale(Locale.ENGLISH);
                else I18N.setLocale(new Locale("KA"));
                System.out.println();
            }
        });

        AtomicReference<Double> planetRadius = new AtomicReference<>((double) 5);

        Slider sizeSlider = (Slider) scene.lookup("#sizeSlider");
        sizeSlider.setMin(1.0D);
        sizeSlider.setMax(20.0D);
        sizeSlider.setValue(5.0D);
        sizeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
                    planetRadius.set(newValue.doubleValue());
                });

        canvas.setOnMouseClicked(e -> Space.addInstance(new Planet(e.getX(), e.getY(), planetRadius.get())));

        primaryStage.show();

        Runnable spaceRunnable = new TimelineRunnable();
        Thread spaceThread = new Thread(spaceRunnable, "Physics");
        spaceThread.setDaemon(true);
        spaceThread.setPriority(MAX_PRIORITY * 3 / 4);

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(Space.getFPS()), e -> run(spaceThread)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        logger.info("Simulation has started.");
    }

    @SneakyThrows
    private void run(Thread thr){
        if (!Space.end){
            thr.run();
            thr.join();
        }
        //Threadless version
        /*
        if (!Space.end){
            try {
                Space.runStep();
            } catch (GraphicsContextMissingException e) {
                e.printStackTrace();
            }
        }
        */
    }

    public static void main(String[] args){
        logger.info("Application has started.");
        launch();

        logger.info("Simulation has ended.");
    }
}
