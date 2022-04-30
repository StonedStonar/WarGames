package no.stonedstonar.wargames.ui.windows;

import javafx.scene.Scene;
import no.stonedstonar.wargames.ui.controllers.Controller;
import no.stonedstonar.wargames.ui.controllers.GameModeController;
import no.stonedstonar.wargames.ui.controllers.OneToOneBattleController;

/**
 * Represents a window that hosts a OneToOne battle.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class OneToOneBattleWindow implements Window{

    private String title;

    private String fxmlName;

    private Scene scene;

    private static OneToOneBattleWindow oneToOneBattleWindow;

    private OneToOneBattleController oneToOneBattleController;

    /**
     * Makes an instance of the OneToOneBattleWindow class.
     */
    public OneToOneBattleWindow() {
        oneToOneBattleController = new OneToOneBattleController();
        title = "One to one battle";
        fxmlName = "OneToOneBattleWindow";
    }

    /**
     * Gets the one to one battle window.
     * @return the one to one battle window.
     */
    public static OneToOneBattleWindow getOneToOneBattleWindow(){
        if (oneToOneBattleWindow == null){
            synchronized (OneToOneBattleWindow.class){
                oneToOneBattleWindow = new OneToOneBattleWindow();
            }
        }

        return oneToOneBattleWindow;
    }

    /**
     * Checks if a string is of a valid format or not.
     *
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

    @Override
    public Controller getController() {
        return oneToOneBattleController;
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
        this.scene = scene;
    }
}
