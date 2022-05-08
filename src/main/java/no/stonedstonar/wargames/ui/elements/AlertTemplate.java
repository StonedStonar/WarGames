package no.stonedstonar.wargames.ui.elements;

import javafx.scene.control.Alert;

/**
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class AlertTemplate {


    /**
     * Makes an alert that matches the could not change window format.
     * @return the alert.
     */
    public static Alert makeCouldNotChangeWindowAlert(){
        return makeAlert(Alert.AlertType.ERROR, "Could not change window", "Could not change window.", "An error has occurred in switching between windows.\n Please try again or restart the app.");
    }

    /**
     * Makes an alert based on the input.
     * @param alertType the alert type.
     * @param title the title of the alert.
     * @param headerText the header text of the alert.
     * @param contents the contents of the alert.
     * @return the alert that was made.
     */
    public static Alert makeAlert(Alert.AlertType alertType, String title, String headerText, String contents){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contents);
        return alert;
    }

    /**
     * Makes a CouldNotEditSelectedUnit alert.
     * @return the alert.
     */
    public static Alert makeCouldNotEditSelectUnit(){
        return makeAlert(Alert.AlertType.ERROR, "Could not edit unit", "Could not edit unit", "No unit is selected from the table before editing. \nPlease try again");
    }

    /**
     * Makes a CouldNotAddUnit alert.
     * @return the alert.
     */
    public static Alert makeCouldNotAddUnitAlert(){
        return makeAlert(Alert.AlertType.ERROR, "Could not add unit", "Could not add unit", "Could not add wanted unit(s). \nInput is invalid please try again.");
    }

    /**
     * Makes a CouldNotSimulateBattle alert.
     * @return the alert.
     */
    public static Alert makeCouldNotSimulateBattle(){
        return makeAlert(Alert.AlertType.INFORMATION, "Could not start battle", "Could not start battle", "Could not start battle since one army does not have any units.");
    }
}
