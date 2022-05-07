package no.stonedstonar.wargames.model.army;

import no.stonedstonar.wargames.model.UnitType;
import no.stonedstonar.wargames.model.exception.CouldNotAddUnitException;
import no.stonedstonar.wargames.model.exception.CouldNotGetUnitException;
import no.stonedstonar.wargames.model.exception.CouldNotRemoveUnitException;
import no.stonedstonar.wargames.model.units.Unit;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a collection of units that together represent an army.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public interface Army {

    /**
     * Gets the name of the army.
     * @return the name of the army.
     */
    String getArmyName();

    /**
     * Sets the name of the army.
     * @param armyName the new army name.
     */
    void setArmyName(String armyName);

    /**
     * Adds a unit to the army.
     * @param unit the unit to add.
     * @throws CouldNotAddUnitException gets thrown if the unit could not be added.
     */
    void addUnit(Unit unit) throws CouldNotAddUnitException;

    /**
     * Adds a collection with many units.
     * @param units the units to add.
     * @throws CouldNotAddUnitException gets thrown if one of the units are already in the list.
     */
    void addAllUnits(Collection<Unit> units) throws CouldNotAddUnitException;

    /**
     * Removes a unit from the army.
     * @param unitToRemove the unit to remove.
     * @throws CouldNotRemoveUnitException gets thrown if the unit could not be found.
     */
    void removeUnit(Unit unitToRemove) throws CouldNotRemoveUnitException;

    /**
     * Deletes all the units in this army.
     */
    void clearAllUnits();

    /**
     * Checks if there is any units in the army.
     * @return <code>true</code> if the army has units.
     *         <code>false</code> if the army does not have any units.
     */
    boolean hasUnits();

    /**
     * Gets an Iterator with all the units.
     * @return the units.
     */
    List<Unit> getAllUnits();

    /**
     * Gets a random unit from this army.
     * @return the random unit.
     * @throws CouldNotGetUnitException gets thrown if the unit could not be found.
     */
    Unit getRandomUnit() throws CouldNotGetUnitException;

    /**
     * Gets a specific unit based on an enum.
     * @param unitType the wanted unit.
     * @return the unit with that type.
     */
    List<Unit> getSpecifiedUnit(UnitType unitType);

    /**
     * Gets the death toll of this army.
     * @return the amount of soldiers that died from this army.
     */
    int getDeathToll();
}
