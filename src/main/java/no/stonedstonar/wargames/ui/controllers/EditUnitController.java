package no.stonedstonar.wargames.ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import no.stonedstonar.wargames.model.UnitType;
import no.stonedstonar.wargames.model.army.Army;
import no.stonedstonar.wargames.model.units.Unit;
import no.stonedstonar.wargames.ui.elements.ArmyTableBuilder;
import no.stonedstonar.wargames.ui.windows.Window;

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
    private Button addUnitsButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button backToBattleButton;

    @FXML
    private Button clearArmyButton;

    private boolean editUnit;

    private Window lastWindow;

    private Army army;

    /**
     * Makes an instance of the EditUnitController class.
     */
    public EditUnitController() {
        editUnit = false;
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
    }

    @Override
    public void updateContent() {
        if (armyTable.getColumns().isEmpty()){
            makeColumnsToTable(armyTable);
        }
        armyTable.getItems().clear();
        armyTable.getItems().setAll(makeObservableListFromArmy());
    }

    @Override
    public void emptyContent() {

    }

    private ObservableList<Unit> makeObservableListFromArmy(){
        List<Unit> unitList = new ArrayList<>();
        army.getAllUnits().forEachRemaining(unitList::add);
        return FXCollections.observableList(unitList);
    }

    /**
     * Makes a standard set of columns to the table.
     * @param tableView the table view to add columns to.
     */
    private void makeColumnsToTable(TableView<Unit> tableView){
        TableView<Unit> tableView1 = new ArmyTableBuilder(tableView).addNameColumn().addHealthColumn().build();
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
