package no.stonedstonar.wargames.ui.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import no.stonedstonar.wargames.model.Battle;
import no.stonedstonar.wargames.model.OneToOneBattle;
import no.stonedstonar.wargames.model.TerrainStyle;
import no.stonedstonar.wargames.model.army.Army;
import no.stonedstonar.wargames.model.army.ArmyFileHandler;
import no.stonedstonar.wargames.model.army.NormalArmy;
import no.stonedstonar.wargames.model.exception.CouldNotFinishBattleException;
import no.stonedstonar.wargames.model.units.Unit;
import no.stonedstonar.wargames.ui.WarGamesApplication;
import no.stonedstonar.wargames.ui.elements.AlertTemplate;
import no.stonedstonar.wargames.ui.elements.ArmyTableBuilder;
import no.stonedstonar.wargames.ui.windows.EditUnitWindow;
import no.stonedstonar.wargames.ui.windows.GameModeWindow;
import no.stonedstonar.wargames.ui.windows.OneToOneBattleWindow;

import java.io.IOException;
import java.util.logging.FileHandler;

/**
 * Represents a controller of a one-to-one battle.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class OneToOneBattleController implements Controller{

    @FXML
    private Label armyOneLabel;

    @FXML
    private Label armyTwoLabel;

    @FXML
    private TableView<Unit> armyOneTable;

    @FXML
    private TableView<Unit> armyTwoTable;

    @FXML
    private Button editArmyOneButton;

    @FXML
    private Button editArmyTwoButton;

    @FXML
    private MenuItem saveToFileMenu;

    @FXML
    private MenuItem loadFromFileMenu;

    @FXML
    private MenuItem exitAppMenu;

    @FXML
    private MenuItem backToMenu;

    @FXML
    private MenuItem aboutApplicationMenu;

    @FXML
    private MenuItem howToPlayMenu;

    @FXML
    private Label winningArmyLabel;

    @FXML
    private Button startSimulationButton;

    @FXML
    private ComboBox<TerrainStyle> terrainComboBox;

    private Army armyOne;

    private Army armyTwo;


    /**
     * Makes an instance of the OneToOneBattleController class.
     */
    public OneToOneBattleController() {
        armyOne = new NormalArmy("Human");
        armyTwo = new NormalArmy("Orcs");
    }

    /**
     * Sets the functions of the buttons.
     */
    private void setButtonsFunctions(){
        terrainComboBox.getItems().add(TerrainStyle.FOREST);
        terrainComboBox.getItems().add(TerrainStyle.HILL);
        terrainComboBox.getItems().add(TerrainStyle.PLAINS);

        editArmyOneButton.setOnAction(event -> editArmy(armyOne));
        editArmyTwoButton.setOnAction(event -> editArmy(armyTwo));
        startSimulationButton.setOnAction(event -> simulateBattle());
    }

    /**
     * Sets the menu items functions.
     */
    private void setMenuItemFunctions(){
        exitAppMenu.setOnAction(event -> Platform.exit());
        backToMenu.setOnAction(event -> {
            try {
                WarGamesApplication.getWarGamesApplication().setNewScene(GameModeWindow.getGameModeWindow());
            } catch (IOException e) {
                AlertTemplate.makeCouldNotChangeWindowAlert().showAndWait();
            }
        });
    }

    /**
     * Simulates a battle between two armies.
     */
    private void simulateBattle(){
        TerrainStyle terrainStyle = terrainComboBox.getSelectionModel().getSelectedItem();
        if (terrainStyle == null){
            Alert alert = AlertTemplate.makeSelectTerrainAlert();
            alert.showAndWait();
        }else {
            try {
                if (armyTwo.hasUnits() && armyOne.hasUnits()){
                    Battle battle = new OneToOneBattle(armyOne, armyTwo, terrainStyle);
                    Army army = battle.simulateBattle();
                    if (army != null){
                        winningArmyLabel.setText(army.getArmyName());
                    }else {
                        winningArmyLabel.setText("Draw");
                    }
                    startSimulationButton.setDisable(true);
                    armyTwoTable.refresh();
                    armyOneTable.refresh();
                }else {
                    Alert alert = AlertTemplate.makeCouldNotSimulateBattle();
                    alert.showAndWait();
                }
            }catch (CouldNotFinishBattleException | IllegalArgumentException exception){
                Alert alert = AlertTemplate.makeAlert(Alert.AlertType.WARNING, "Could not simulate battle", "Could not simulate battle", "Could not simulate battle. Something has gone wrong in the simulation. \nPlease try again");
                alert.showAndWait();
            }
        }
    }

    /**
     * Makes it possible to edit an army.
     * @param army the army to edit.
     */
    private void editArmy(Army army){
        EditUnitWindow editUnitWindow = EditUnitWindow.getEditUnitWindow();
        editUnitWindow.setArmy(army, OneToOneBattleWindow.getOneToOneBattleWindow());
        try {
            WarGamesApplication.getWarGamesApplication().setNewScene(editUnitWindow);
        } catch (IOException e) {
            AlertTemplate.makeCouldNotChangeWindowAlert().showAndWait();
            e.printStackTrace();
        }
    }

    @Override
    public void updateContent() {
        startSimulationButton.setDisable(!(armyOne.hasUnits() && armyTwo.hasUnits()));
        armyOneTable.getItems().clear();
        armyTwoTable.getItems().clear();
        armyTwoTable.getItems().addAll(armyTwo.getAllUnits());
        armyOneTable.getItems().addAll(armyOne.getAllUnits());
        armyTwoLabel.setText(armyTwo.getArmyName());
        armyOneLabel.setText(armyOne.getArmyName());
    }

    @Override
    public void emptyContent() {
        armyOneTable.getItems().clear();
        armyTwoTable.getItems().clear();
        winningArmyLabel.setText("");
        armyOneLabel.setText("");
        armyTwoLabel.setText("");
    }

    @Override
    public void setFunctionsOnce() {
        setButtonsFunctions();
        setMenuItemFunctions();
        if (armyTwoTable.getColumns().isEmpty()){
            makeColumnsToTable(armyTwoTable);
        }
        if (armyOneTable.getColumns().isEmpty()){
            makeColumnsToTable(armyOneTable);
        }
    }

    /**
     * Makes a standard set of columns to the table.
     * @param tableView the table view to add columns to.
     */
    private void makeColumnsToTable(TableView<Unit> tableView){
        TableView<Unit> tableView1 = new ArmyTableBuilder(tableView).addNameColumn().addHealthColumn().addArmourColumn().addAttackColumn().build();
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
