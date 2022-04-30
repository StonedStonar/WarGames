package no.stonedstonar.wargames.ui.windows;

import javafx.scene.Scene;
import no.stonedstonar.wargames.ui.controllers.Controller;
import no.stonedstonar.wargames.ui.controllers.GameModeController;

/**
 * Represents a window where the user chooses one of the preconfigured battles.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class GameModeWindow implements Window{

    private String title;

    private String fxmlName;

    private Scene scene;

    private static GameModeWindow gameModeWindow;

    private GameModeController gameModeController;

    /**
     * Makes an instance of the GameModeWindow class.
     */
    public GameModeWindow() {
        gameModeController = new GameModeController();
        fxmlName = "GameModeWindow";
        title = "Game mode";
    }

    /**
     * Gets the game mode window.
     * @return the game mode window.
     */
    public static GameModeWindow getGameModeWindow(){
        if (gameModeWindow == null){
            synchronized (GameModeWindow.class){
                gameModeWindow = new GameModeWindow();
            }
        }
        return gameModeWindow;
    }

    @Override
    public Controller getController() {
        return gameModeController;
    }

    @Override
    public Scene getScene() {
        return scene;
    }

    @Override
    public String getFXMLName() {
        return fxmlName;
    }

    @Override
    public String getTitleName() {
        return title;
    }

    @Override
    public void setScene(Scene scene) {
        checkIfObjectIsNull(scene, "scene");
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
