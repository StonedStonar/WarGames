package no.stonedstonar.wargames.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Represents an army on the battleground.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class Army {

    private String armyName;

    private List<Unit> units;

    /**
     * Makes an instance of the Army class.
     * @param armyName the name of the army.
     * @param unitList the list of units.
     */
    public Army(String armyName, List<Unit> unitList) {
        checkArmyName(armyName);
        checkListWithUnits(unitList);
        this.armyName = armyName;
        this.units = unitList;
    }

    /**
     * Makes an instance of the Army class.
     * @param armyName the name of the army.
     */
    public Army(String armyName) {
        checkArmyName(armyName);
        this.armyName = armyName;
        this.units = new ArrayList<>();
    }

    public void addUnit(Unit unit){

    }

    public void addAllUnits(Collection<Unit> units){

    }

    public void removeUnit(Unit unitToRemove){

    }

    public boolean hasUnits(){
        return !units.isEmpty();
    }

    public Unit getRandomUnit(){
        return null;
    }

    /**
     * Checks if a list with units is null.
     * @param units the units to check.
     */
    private void checkListWithUnits(List<Unit> units){
        checkIfObjectIsNull(units, null);
    }

    /**
     * Checks if the input army name is not null or empty.
     * @param armyName the army name
     */
    private void checkArmyName(String armyName){
        checkString(armyName, "army name");
    }

    /**
     * Checks if a string is of a valid format or not.
     *
     * @param stringToCheck the string you want to check.
     * @param errorPrefix   the error the exception should have if the string is invalid.
     * @throws IllegalArgumentException gets thrown if the string to check is empty or null.
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
     * @throws IllegalArgumentException gets thrown if the object is null.
     */
    private void checkIfObjectIsNull(Object object, String error) {
        if (object == null) {
            throw new IllegalArgumentException("The " + error + " cannot be null.");
        }
    }
}
