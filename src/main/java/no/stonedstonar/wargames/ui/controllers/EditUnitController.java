package no.stonedstonar.wargames.ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import no.stonedstonar.wargames.model.TerrainStyle;
import no.stonedstonar.wargames.model.UnitType;
import no.stonedstonar.wargames.model.army.Army;
import no.stonedstonar.wargames.model.army.ArmyFileHandler;
import no.stonedstonar.wargames.model.army.ArmyPresets;
import no.stonedstonar.wargames.model.exception.CouldNotAddUnitException;
import no.stonedstonar.wargames.model.exception.CouldNotRemoveUnitException;
import no.stonedstonar.wargames.model.exception.InvalidFormatException;
import no.stonedstonar.wargames.model.units.*;
import no.stonedstonar.wargames.ui.WarGamesApplication;
import no.stonedstonar.wargames.ui.elements.AlertTemplateFactory;
import no.stonedstonar.wargames.ui.elements.ArmyTableBuilder;
import no.stonedstonar.wargames.ui.windows.GameModeWindow;
import no.stonedstonar.wargames.ui.windows.Window;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class EditUnitController implements Controller{

    @FXML
    private MenuItem backToMenu;

    @FXML
    private MenuItem exitAppMenu;

    @FXML
    private MenuItem aboutApplicationMenu;

    @FXML
    private TableView<Unit> armyTable;

    @FXML
    private Button editUnitButton;

    @FXML
    private ComboBox<UnitType> unitCombo;

    @FXML
    private TextField unitNameField;

    @FXML
    private MenuItem saveToFileMenu;

    @FXML
    private MenuItem loadFromFileMenu;

    @FXML
    private TextField healthField;

    @FXML
    private TextField attackField;

    @FXML
    private TextField armourField;

    @FXML
    private TextField amountField;

    @FXML
    private Button saveUnitsButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button backToBattleButton;

    @FXML
    private Button clearArmyButton;

    @FXML
    private TextField armyNameField;

    @FXML
    private Button addPresetsButton;

    @FXML
    private MenuItem helpMenuItem;

    private AlertTemplateFactory alertTemplateFactory;

    private UnitFactory unitFactory;

    private Window lastWindow;

    private Army army;

    /**
     * Makes an instance of the EditUnitController class.
     */
    public EditUnitController() {
        unitFactory = new UnitFactory();
        alertTemplateFactory = new AlertTemplateFactory();

    }

    /**
     * Sets the army of this window.
     * @param army the army.
     * @param lastWindow the last window
     */
    public void setArmyAndLastWindow(Army army, Window lastWindow){
        checkIfObjectIsNull(army, "army");
        checkIfObjectIsNull(lastWindow, "last window");
        this.army = army;
        this.lastWindow = lastWindow;
        unitFactory = new UnitFactory();
    }

    private void setMenuFunctions(){
        exitAppMenu.setOnAction(event ->  WarGamesApplication.getWarGamesApplication().closeApplication());
        backToMenu.setOnAction(event -> {
            try {
                WarGamesApplication.getWarGamesApplication().setNewScene(GameModeWindow.getGameModeWindow());
            } catch (IOException e) {
                Alert alert = alertTemplateFactory.makeCouldNotChangeWindowAlert();
                alert.showAndWait();
            }
        });
        helpMenuItem.setOnAction(actionEvent -> {
            Alert alert = alertTemplateFactory.makeAlert(Alert.AlertType.INFORMATION, "Edit units", "Edit units is a window where you can edit a unit in an army. It is also possible to edit the armies name and different units.\nTo edit units in the table select one and click \"edit unit\" button. \nTo make new units use the \"clear\" button and fill in the details.");
            alert.showAndWait();
        });
        aboutApplicationMenu.setOnAction(actionEvent -> {
            Alert alert = alertTemplateFactory.makeAboutApplicationAlert();
            alert.showAndWait();
        });

        loadFromFileMenu.setOnAction(event -> loadArmyFromFile());
        saveToFileMenu.setOnAction(event -> saveArmyToFile());
    }

    /**
     * Saves an army to a file.
     */
    private void saveArmyToFile(){
        try {
            if (army.hasUnits()){
                ArmyFileHandler.writeArmyToFile(army, null, true);
                Alert alert = alertTemplateFactory.makeAlert(Alert.AlertType.INFORMATION, "Army has been saved", "The army has now been saved under userName/Documents/Wargames Saves");
                alert.showAndWait();
            }else {
                Alert alert = alertTemplateFactory.makeAlert(Alert.AlertType.ERROR, "Army has no units", "The army has no units and thus cannot be saved.");
                alert.showAndWait();
            }
        }catch (IOException exception){
            Alert  alert = alertTemplateFactory.makeAlert(Alert.AlertType.ERROR, "Could not save army", "The army could not be saved. Something has gone wrong in the writing to the file. \nPlease try again.");
            alert.showAndWait();
        }
    }

    /**
     * Loads an army from a file.
     */
    private void loadArmyFromFile(){
        ButtonType buttonType = null;
        if (army.hasUnits()){
            Alert alert = alertTemplateFactory.makeAlert(Alert.AlertType.CONFIRMATION, "Load army", "Are you sure you want to load a new army? \nThe current army will be overwritten and lost.");
            buttonType = alert.showAndWait().get();
        }
        if (buttonType == null || buttonType == ButtonType.OK){
            try {
                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV file","*.csv"));
                fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "\\Documents" + "\\WarGames Saves\\"));
                File file = fileChooser.showOpenDialog(null);
                if (file != null){
                    Army loadedArmy = ArmyFileHandler.readFromFile(file);
                    army.clearAllUnits();
                    army.addAllUnits(loadedArmy.getAllUnits());
                    army.setArmyName(loadedArmy.getArmyName());
                    armyTable.getItems().clear();
                    armyTable.getItems().addAll(army.getAllUnits());
                    armyNameField.setText(loadedArmy.getArmyName());
                }else {
                    Alert alert = alertTemplateFactory.makeAlert(Alert.AlertType.ERROR, "No file selected", "The army could not be loaded since no .csv file is selected.");
                    alert.showAndWait();
                }
            }catch (IOException | CouldNotAddUnitException exception){
                Alert alert = alertTemplateFactory.makeAlert(Alert.AlertType.ERROR, "Could not read file", "Something has gone wrong while reading the wanted file. \nPlease try again.");
                alert.showAndWait();
            }catch (InvalidFormatException exception){
                Alert alert = alertTemplateFactory.makeAlert(Alert.AlertType.ERROR, "Invalid file format", "The selected file has invalid file format.");
                alert.showAndWait();
            }
        }
    }

    /**
     * Sets the buttons and their functions.
     */
    private void setButtonsAndFunctions(){
        unitCombo.getItems().add(UnitType.INFANTRY);
        unitCombo.getItems().add(UnitType.CAVALRY);
        unitCombo.getItems().add(UnitType.CAVALRYCOMMANDER);
        unitCombo.getItems().add(UnitType.RANGEDUNIT);

        editUnitButton.setOnAction(event -> editUnit());
        cancelButton.setOnAction(event -> {
            emptyFields();
            amountField.setDisable(false);
        });
        clearArmyButton.setOnAction(event -> {
            armyTable.getItems().clear();
            army.clearAllUnits();
        });
        addPresetsButton.setOnAction(event -> {
            try {
                ArmyPresets.fillArmyWithUnits(army);
                armyTable.getItems().clear();
                armyTable.getItems().addAll(army.getAllUnits());
            } catch (CouldNotAddUnitException e) {
                alertTemplateFactory.makeAlert(Alert.AlertType.ERROR, "Could not add presets", "Could not add default presets. \nPlease try again").showAndWait();
            }
        });
        backToBattleButton.setOnAction(event -> {
            try {
                WarGamesApplication.getWarGamesApplication().setNewScene(lastWindow);
            } catch (IOException e) {
                alertTemplateFactory.makeCouldNotChangeWindowAlert().showAndWait();
            }
        });
        saveUnitsButton.setOnAction(event -> {
            addUnitOrUnits();
        });
    }

    /**
     * Adds a unit or units based on the input.
     */
    private void addUnitOrUnits(){
        try {
            int health = Integer.parseInt(healthField.getText());
            String unitName = unitNameField.getText();
            UnitType unitType = unitCombo.getSelectionModel().getSelectedItem();
            if (!amountField.isDisable() && amountField.getText().isEmpty()){
                int number = Integer.parseInt(amountField.textProperty().get());
                List<Unit> units = unitFactory.makeNAmountOfTypeUnit(number, unitType, unitName,health, TerrainStyle.PLAINS);
                army.addAllUnits(units);
                armyTable.getItems().addAll(units);
                emptyFields();
            }else {
                List<Unit> units = unitFactory.makeNAmountOfTypeUnit(Integer.parseInt(amountField.getText()), unitType, unitName, health, TerrainStyle.PLAINS);
                army.addAllUnits(units);
                armyTable.getItems().addAll(units);
                emptyFields();
            }
        }catch (CouldNotAddUnitException | IllegalArgumentException exception){
            Alert alert = alertTemplateFactory.makeCouldNotAddUnitAlert();
            alert.showAndWait();
        }
    }

    /**
     * Makes an observable list out of the units.
     * @param units the units.
     * @return the observable list.
     */
    private ObservableList<Unit> makeObservableUnits(List<Unit> units){
        return FXCollections.observableList(units);
    }

    @Override
    public void updateContent() {
        armyTable.getItems().clear();
        armyTable.getItems().addAll(army.getAllUnits());
        armyNameField.setText(army.getArmyName());
    }

    @Override
    public void emptyContent() {
        armyTable.getItems().clear();
        armyNameField.setText("");
        emptyFields();
    }

    @Override
    public void setFunctionsOnce() {
        if (armyTable.getColumns().isEmpty()){
            makeColumnsToTable(armyTable);
        }
        setButtonsAndFunctions();
        setMenuFunctions();
    }

    /**
     * Empties the fields.
     */
    private void emptyFields(){
        armourField.setText("");
        attackField.setText("");
        unitNameField.setText("");
        healthField.setText("");
        amountField.setText("");
        unitCombo.getSelectionModel().clearSelection();
    }

    /**
     * Makes the edit window go into edit unit mode.
     */
    private void editUnit(){
        Unit unit = armyTable.getSelectionModel().getSelectedItem();
        emptyFields();
        if (unit == null){
            Alert alert = alertTemplateFactory.makeCouldNotEditSelectUnit();
            alert.showAndWait();
        }else {
            amountField.setDisable(true);
            saveUnitsButton.setDisable(false);
            unitNameField.setText(unit.getUnitName());
            healthField.setText(Integer.toString(unit.getHealth()));
            attackField.setText(Integer.toString(unit.getAttack()));
            armourField.setText(Integer.toString(unit.getArmour()));
            UnitType unitType = unit.getUnitType();
            unitCombo.getSelectionModel().select(unitType);
            try {
                army.removeUnit(unit);
                armyTable.getItems().remove(unit);
                armyTable.refresh();
            }catch (CouldNotRemoveUnitException | IllegalArgumentException exception){
                System.err.println("The unit selected could not be removed.");
            }
        }
    }

    /**
     * Makes a standard set of columns to the table.
     * @param tableView the table view to add columns to.
     */
    private void makeColumnsToTable(TableView<Unit> tableView){
        TableView<Unit> tableView1 = new ArmyTableBuilder(tableView).addNameColumn().addHealthColumn().addAttackColumn().addArmourColumn().build();
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
