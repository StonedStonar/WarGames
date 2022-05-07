package no.stonedstonar.wargames.ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import no.stonedstonar.wargames.model.UnitType;
import no.stonedstonar.wargames.model.army.Army;
import no.stonedstonar.wargames.model.army.ArmyPresets;
import no.stonedstonar.wargames.model.exception.CouldNotAddUnitException;
import no.stonedstonar.wargames.model.exception.CouldNotRemoveUnitException;
import no.stonedstonar.wargames.model.units.*;
import no.stonedstonar.wargames.ui.WarGamesApplication;
import no.stonedstonar.wargames.ui.elements.AlertTemplate;
import no.stonedstonar.wargames.ui.elements.ArmyTableBuilder;
import no.stonedstonar.wargames.ui.windows.Window;

import java.io.IOException;
import java.util.ArrayList;
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

    private UnitFactory unitFactory;

    private Window lastWindow;

    private Army army;

    /**
     * Makes an instance of the EditUnitController class.
     */
    public EditUnitController() {
        unitFactory = new UnitFactory();

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
                AlertTemplate.makeAlert(Alert.AlertType.ERROR, "Could not add presets", "Could not add presets", "Could not add default presets. \nPlease try again").showAndWait();
            }
        });
        backToBattleButton.setOnAction(event -> {
            try {
                WarGamesApplication.getWarGamesApplication().setNewScene(lastWindow);
            } catch (IOException e) {
                AlertTemplate.makeCouldNotChangeWindowAlert().showAndWait();
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
                List<Unit> units = unitFactory.makeNAmountOfTypeUnit(number, unitType, unitName,health);
                army.addAllUnits(units);
                armyTable.getItems().addAll(units);
                emptyFields();
            }else {
                Unit unit = unitFactory.makeSimpleUnit(unitType, unitName, health);
                army.addUnit(unit);
                armyTable.getItems().add(unit);
                emptyFields();
            }
        }catch (CouldNotAddUnitException | IllegalArgumentException exception){
            Alert alert = AlertTemplate.makeCouldNotAddUnitAlert();
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
        if (armyTable.getColumns().isEmpty()){
            makeColumnsToTable(armyTable);
        }
        armyTable.getItems().clear();
        armyTable.getItems().addAll(army.getAllUnits());
        setButtonsAndFunctions();
        armyNameField.setText(army.getArmyName());
    }

    @Override
    public void emptyContent() {
        armyTable.getItems().clear();
        armyNameField.setText("");
        emptyFields();
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
            Alert alert = AlertTemplate.makeCouldNotEditSelectUnit();
            alert.showAndWait();
        }else {
            amountField.setDisable(true);
            saveUnitsButton.setDisable(false);
            unitNameField.setText(unit.getUnitName());
            healthField.setText(Integer.toString(unit.getHealth()));
            attackField.setText(Integer.toString(unit.getAttack()));
            armourField.setText(Integer.toString(unit.getArmour()));
            UnitType unitType = switch (unit.getClass().getSimpleName()){
                case "InfantryUnit" -> UnitType.INFANTRY;
                case "CavalryUnit" -> UnitType.CAVALRY;
                case "RangedUnit" -> UnitType.RANGEDUNIT;
                case "ChivalryCommanderUnit" -> UnitType.CAVALRYCOMMANDER;
                default -> null;
            };
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

    private ObservableList<Unit> makeObservableListFromArmy(){
        return FXCollections.observableList(army.getAllUnits());
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
