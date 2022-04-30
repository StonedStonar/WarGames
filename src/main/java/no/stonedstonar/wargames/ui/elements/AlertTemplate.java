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
}
