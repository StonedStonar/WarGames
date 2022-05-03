package no.stonedstonar.wargames.ui.windows;

import javafx.scene.Scene;
import no.stonedstonar.wargames.model.army.Army;
import no.stonedstonar.wargames.ui.controllers.Controller;
import no.stonedstonar.wargames.ui.controllers.EditUnitController;

/**
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class EditUnitWindow implements Window{

    private String title;

    private String fxmlName;

    private Scene scene;

    private static EditUnitWindow editUnitWindow;

    private EditUnitController editUnitController;

    /**
     * Makes an instance of the EditUnitWindow class.
     */
    public EditUnitWindow() {
        editUnitController = new EditUnitController();
        this.title = "Edit units";
        this.fxmlName = "Edit army";
    }

    /**
     * Sets the army to change in this edit pag.e
     * @param army the army.
     * @param lastWindow the last window
     */
    public void setArmy(Army army, Window lastWindow){
        checkIfObjectIsNull(army, "army");
        checkIfObjectIsNull(lastWindow, "last window");
        editUnitController.setArmyAndLastWindow(army, lastWindow);
    }

    /**
     * Gets the edit unit window.
     * @return the edit unit window.
     */
    public static EditUnitWindow getEditUnitWindow(){
        if (editUnitWindow == null){
            synchronized (EditUnitWindow.class){
                editUnitWindow = new EditUnitWindow();
            }
        }
        return editUnitWindow;
    }


    @Override
    public Controller getController() {
        return editUnitController;
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
}
