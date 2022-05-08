package no.stonedstonar.wargames.ui.elements;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import no.stonedstonar.wargames.model.units.Unit;

/**
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class ArmyTableBuilder {

    private TableView<Unit> tableView;

    /**
     * Makes an instance of the ArmyTableFactory class.
     * @param tableView the table view to add units to.
     */
    public ArmyTableBuilder(TableView<Unit> tableView) {
        checkIfObjectIsNull(tableView, "tableview");
        this.tableView = tableView;
    }

    /**
     * Adds a name column to the table.
     * @return the builder object.
     */
    public ArmyTableBuilder addNameColumn(){
        TableColumn<Unit, String> unitNameColumn = new TableColumn<>("Name");
        unitNameColumn.setCellValueFactory(unit -> new SimpleStringProperty(unit.getValue().getUnitName()));
        tableView.getColumns().add(unitNameColumn);
        return this;
    }

    /**
     * Adds a health column to the table.
     * @return the builder object.
     */
    public ArmyTableBuilder addHealthColumn(){
        TableColumn<Unit, Number> unitHealthColumn = new TableColumn<>("Health");
        unitHealthColumn.setCellValueFactory(unit -> new SimpleIntegerProperty(unit.getValue().getHealth()));
        tableView.getColumns().add(unitHealthColumn);
        return this;
    }

    /**
     * Adds an attack column to the table.
     * @return the builder object.
     */
    public ArmyTableBuilder addAttackColumn(){
        TableColumn<Unit, Number> attackCol = new TableColumn<>("Attack");
        attackCol.setCellValueFactory(unit -> new SimpleIntegerProperty(unit.getValue().getAttack()));
        tableView.getColumns().add(attackCol);
        return this;
    }

    /**
     * Adds a health column to the table.
     * @return the builder object.
     */
    public ArmyTableBuilder addArmourColumn(){
        TableColumn<Unit, Number> armourCol = new TableColumn<>("Attack");
        armourCol.setCellValueFactory(unit -> new SimpleIntegerProperty(unit.getValue().getArmour()));
        tableView.getColumns().add(armourCol);
        return this;
    }


    /**
     * Builds the tableview.
     * @return the table view.
     */
    public TableView<Unit> build(){
        return tableView;
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
