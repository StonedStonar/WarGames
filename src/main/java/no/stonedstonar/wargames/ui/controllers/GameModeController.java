package no.stonedstonar.wargames.ui.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import no.stonedstonar.wargames.model.army.Army;
import no.stonedstonar.wargames.ui.WarGamesApplication;
import no.stonedstonar.wargames.ui.elements.AlertTemplateFactory;
import no.stonedstonar.wargames.ui.windows.OneToOneBattleWindow;

import java.io.IOException;

/**
 * Represents the controller of the game mode window.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class GameModeController implements Controller{

    @FXML
    private Button oneToOneButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button anotherModeButton;

    private Army armyOne;

    private Army armyTwo;

    private AlertTemplateFactory alertTemplateFactory;

    /**
     * Makes an instance of the GameModeController class.
     */
    public GameModeController() {
        alertTemplateFactory = new AlertTemplateFactory();
    }

    /**
     * Sets the functions of the buttons.
     */
    private void setButtonsFunctions(){
        WarGamesApplication warGamesApplication = WarGamesApplication.getWarGamesApplication();
        oneToOneButton.setOnAction(event -> {
            try {
                warGamesApplication.setNewScene(OneToOneBattleWindow.getOneToOneBattleWindow());
            } catch (IOException e){
                Alert alert = alertTemplateFactory.makeCouldNotChangeWindowAlert();
                alert.showAndWait();
            }
        });
        anotherModeButton.setOnAction(actionEvent -> {
            Alert alert = alertTemplateFactory.makeAlert(Alert.AlertType.INFORMATION, "Another game mode", "This button is only here to illustrate that there will be more than one gamemode in the future.");
            alert.showAndWait();
        });
        exitButton.setOnAction(event -> WarGamesApplication.getWarGamesApplication().closeApplication());
    }

    @Override
    public void updateContent() {

    }

    @Override
    public void emptyContent() {

    }

    @Override
    public void setFunctionsOnce() {
        setButtonsFunctions();
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
