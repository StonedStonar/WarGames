package no.stonedstonar.wargames.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import no.stonedstonar.wargames.ui.controllers.Controller;
import no.stonedstonar.wargames.ui.windows.GameModeWindow;
import no.stonedstonar.wargames.ui.windows.Window;

import java.io.IOException;

/**
 * The main class of the WarGames application. Used to switch scenes.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class WarGamesApplication extends Application {

    private Stage stage;

    private static WarGamesApplication warGamesApplication;

    /**
     * Makes an instance of the WarGamesApplication class.
     */
    public WarGamesApplication() {
        synchronized (WarGamesApplication.class){
            warGamesApplication = this;
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        setNewScene(GameModeWindow.getGameModeWindow());
        stage.show();
    }

    /**
     * Changes the scene to a new scene.
     * @param window the window you want to change the scene to.
     * @throws IOException gets thrown if the scene could not be loaded.
     */
    public void setNewScene(Window window) throws IOException {
        checkIfObjectIsNull(window, "window");
        Controller controller = window.getController();
        checkIfObjectIsNull(controller, "controller");
        Scene scene = window.getScene();
        if (scene == null){
            String fileName = window.getFXMLName();
            checkString(fileName, "file name");
            String fullFilename = fileName + ".fxml";
            scene = loadScene(fullFilename , controller);
            window.setScene(scene);
            controller.setFunctionsOnce();
        }
        String title = window.getTitleName();
        checkString(title, "title");
        String windowTitle = "Chat application 0.1v - " + title;
        stage.setTitle(windowTitle);
        stage.setScene(scene);
        controller.emptyContent();
        controller.updateContent();
    }

    /**
     * Loads a scene and returns the scene after its loaded.
     * @param fullNameOfFile the full name of the FXML file you want to load with the .fxml part.
     * @param controller the controller you want this scene to have.
     * @return a scene that is loaded and ready to be displayed.
     * @throws IOException gets thrown if the scene is not loaded correctly or is missing.
     */
    private Scene loadScene(String fullNameOfFile, Controller controller) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fullNameOfFile));
        loader.setController(controller);
        return new Scene(loader.load());
    }

    /**
     * Gets the philosopher application.
     * @return the philosopher application.
     */
    public static WarGamesApplication getWarGamesApplication(){
        if (warGamesApplication == null){
            synchronized (WarGamesApplication.class){
                warGamesApplication = new WarGamesApplication();
            }
        }
        return warGamesApplication;
    }


    /**
     * Checks if a string is of a valid format or not.
     * @param stringToCheck the string you want to check.
     * @param errorPrefix   the error the exception should have if the string is invalid.
     */
    private void checkString(String stringToCheck, String errorPrefix) {
        checkIfObjectIsNull(stringToCheck, errorPrefix);
        if (stringToCheck.isEmpty()) {
            throw new IllegalArgumentException("The " + errorPrefix + " cannot be empty.");
        }
    }

    /**
     * Checks if an object is null.
     *
     * @param object the object you want to check.
     * @param error  the error message the exception should have.
     */
    private void checkIfObjectIsNull(Object object, String error) {
        if (object == null) {
            throw new IllegalArgumentException("The " + error + " cannot be null.");
        }
    }
}
