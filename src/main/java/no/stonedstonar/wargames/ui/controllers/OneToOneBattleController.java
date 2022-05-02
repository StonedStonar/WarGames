package no.stonedstonar.wargames.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import no.stonedstonar.wargames.model.units.Unit;

/**
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class OneToOneBattleController implements Controller{

    @FXML
    private TableView<Unit> armyOneTable;

    @FXML
    private TableView<Unit> armyTwoTable;

    @FXML
    private Button editArmyOneButton;

    @FXML
    private Button editArmyTwoButton;

    /**
     * Makes an instance of the OneToOneBattleController class.
     */
    public OneToOneBattleController() {

    }

    /**
     * Sets the functions of the buttons.
     */
    private void setButtonsFunctions(){
    }

    @Override
    public void updateContent() {

    }

    @Override
    public void emptyContent() {
        armyOneTable.getItems().clear();
        armyTwoTable.getItems().clear();
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
