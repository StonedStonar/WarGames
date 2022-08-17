package no.stonedstonar.wargames.ui.elements;

import javafx.scene.control.Alert;

/**
 * A template class that can be used to make various alerts.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class AlertTemplateFactory {

    /**
     * Makes an instance of this template factory.
     */
    public AlertTemplateFactory(){

    }

    /**
     * Makes the "About application" alert.
     * @return the alert.
     */
    public Alert makeAboutApplicationAlert(){
        return makeAlert(Alert.AlertType.INFORMATION, "About wargames application", "Application version 0.1v\nThis application is made by a teacher assistant at NTNU Ålesund named Steinar Hjelle Midthus.\nThe task is taken from the Programming 2 course at NTNU Ålesund in 2022 and is an example of how the task could be solved.\nLater iterations will hopefully include a 2D map.");
    }


    /**
     * Makes an alert that matches the could not change window format.
     * @return the alert.
     */
    public Alert makeCouldNotChangeWindowAlert(){
        return makeAlert(Alert.AlertType.ERROR, "Could not change window.", "An error has occurred in switching between windows.\n Please try again or restart the app.");
    }

    /**
     * Makes an alert based on the input.
     * @param alertType the alert type.
     * @param title the title of the alert.
     * @param contents the contents of the alert.
     * @return the alert that was made.
     */
    public Alert makeAlert(Alert.AlertType alertType, String title, String contents){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(contents);
        return alert;
    }

    /**
     * Makes a CouldNotEditSelectedUnit alert.
     * @return the alert.
     */
    public Alert makeCouldNotEditSelectUnit(){
        return makeAlert(Alert.AlertType.ERROR, "Could not edit unit", "No unit is selected from the table before editing. \nPlease try again");
    }

    /**
     * Makes a CouldNotAddUnit alert.
     * @return the alert.
     */
    public Alert makeCouldNotAddUnitAlert(){
        return makeAlert(Alert.AlertType.ERROR, "Could not add unit", "Could not add wanted unit(s). \nInput is invalid please try again.");
    }

    /**
     * Makes a CouldNotSimulateBattle alert.
     * @return the alert.
     */
    public Alert makeCouldNotSimulateBattle(){
        return makeAlert(Alert.AlertType.INFORMATION, "Could not start battle", "Could not start battle since one army does not have any units.");
    }

    /**
     * Makes a Select terrain alert.
     * @return the alert.
     */
    public Alert makeSelectTerrainAlert(){
        return makeAlert(Alert.AlertType.INFORMATION, "Select terrain", "Please select terrain before continuing.");
    }
}
