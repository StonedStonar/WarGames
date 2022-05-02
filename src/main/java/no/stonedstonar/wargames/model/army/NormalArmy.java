package no.stonedstonar.wargames.model.army;

import no.stonedstonar.wargames.model.UnitType;
import no.stonedstonar.wargames.model.army.Army;
import no.stonedstonar.wargames.model.exception.CouldNotAddUnitException;
import no.stonedstonar.wargames.model.exception.CouldNotGetUnitException;
import no.stonedstonar.wargames.model.exception.CouldNotRemoveUnitException;
import no.stonedstonar.wargames.model.units.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Represents a normal army on the battleground.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class NormalArmy implements Army {

    private String armyName;

    private List<Unit> units;

    private Random random;

    private int deathToll;

    /**
     * Makes an instance of the Army class.
     * @param armyName the name of the army.
     * @param unitList the list of units.
     */
    public NormalArmy(String armyName, Collection<Unit> unitList) {
        checkArmyName(armyName);
        checkListWithUnits(unitList);
        this.armyName = armyName;
        this.units = new ArrayList<>();
        this.units.addAll(unitList);
        random = new Random();
    }

    /**
     * Makes an instance of the Army class.
     * @param armyName the name of the army.
     */
    public NormalArmy(String armyName) {
        checkArmyName(armyName);
        this.armyName = armyName;
        this.units = new ArrayList<>();
    }

    @Override
    public String getArmyName() {
        return armyName;
    }

    @Override
    public void addUnit(Unit unit) throws CouldNotAddUnitException {
        checkIfUnitIsValid(unit);
        if (!units.contains(unit)){
            units.add(unit);
        }else {
            throw new CouldNotAddUnitException("The unit is already in this army.");
        }
    }

    @Override
    public void addAllUnits(Collection<Unit> unitCollection) throws CouldNotAddUnitException {
        checkListWithUnits(unitCollection);
        boolean nonMatchingUnits = unitCollection.stream().noneMatch(unit -> this.units.contains(unit));
        if (nonMatchingUnits){
            this.units.addAll(unitCollection);
        }else {
            throw new CouldNotAddUnitException("One unit in the input list is already in the system.");
        }
    }

    @Override
    public void removeUnit(Unit unitToRemove) throws CouldNotRemoveUnitException {
        checkIfUnitIsValid(unitToRemove);
        if (!units.remove(unitToRemove)){
            throw new CouldNotRemoveUnitException("The unit is not in this army.");
        }else{
            deathToll += 1;
        }

    }

    @Override
    public boolean hasUnits(){
        return !this.units.isEmpty();
    }

    @Override
    public Iterator<Unit> getAllUnits() {
        return units.iterator();
    }

    @Override
    public Unit getRandomUnit() throws CouldNotGetUnitException {
        if (units.isEmpty()){
            throw new CouldNotGetUnitException("There are no units left in this army.");
        }
        int nextPos = random.nextInt(units.size());
        return units.get(nextPos);
    }

    @Override
    public List<Unit> getSpecifiedUnit(UnitType unitType) {
        checkIfObjectIsNull(unitType, "unit type");
        return units.stream().filter(unit -> switch (unitType){
            case INFANTRY -> unit instanceof InfantryUnit;
            case CAVALRYCOMMANDER -> unit instanceof ChivalryCommanderUnit;
            case CAVALRY -> (unit instanceof CavalryUnit) && !(unit instanceof ChivalryCommanderUnit);
            case RANGEDUNIT -> unit instanceof RangedUnit;
        }).toList();
    }

    @Override
    public int getDeathToll() {
        return deathToll;
    }

    /**
     * Checks if a unit is not null.
     * @param unit the unit to check.
     */
    private void checkIfUnitIsValid(Unit unit){
        checkIfObjectIsNull(unit, "unit");
    }

    /**
     * Checks if a list with units is null.
     * @param unitCollection the units to check.
     */
    private void checkListWithUnits(Collection<Unit> unitCollection){
        checkIfObjectIsNull(unitCollection, null);
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
