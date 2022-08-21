package no.stonedstonar.wargames.model.units;

import no.stonedstonar.wargames.model.TerrainStyle;
import no.stonedstonar.wargames.model.UnitType;
import no.stonedstonar.wargames.model.items.armour.PlateArmour;
import no.stonedstonar.wargames.model.items.weapons.meele.ShortSword;
import no.stonedstonar.wargames.model.items.weapons.ranged.Bow;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a factory that can make units.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class UnitFactory {

    /**
     * Makes an instance of the UnitFactory class.
     */
    public UnitFactory() {

    }

    /**
     * Makes a list with n amount of the same unit.
     * @param amount the amount of units.
     * @param unitType the unit type.
     * @param unitName the unit name.
     * @param health the unit health.
     * @param terrainStyle the terrain style
     * @return the list with all the units.
     */
    public List<Unit> makeNAmountOfTypeUnit(int amount, UnitType unitType, String unitName, int health, TerrainStyle terrainStyle){
        List<Unit> units = new ArrayList<>();
        checkIfNumberIsValid(amount, "amount");
        for (int i = 0; i < amount; i++){
            units.add(makeSimpleUnit(unitType, unitName, health, terrainStyle));
        }
        return units;
    }

    /**
     * Makes a unit based on all the stats.
     * @param unitType the unit type.
     * @param unitName the unit name.
     * @param health the unit health.
     * @param armour the units armour.
     * @param attack the units attack.
     * @param attackBonus the units' bonus attack.
     * @param armourBonus the units armour bonus.
     * @param terrainStyle the terrain style.
     * @return the unit matching the input.
     */
    private Unit makeUnit(UnitType unitType, String unitName, int health, int armour, int attack , int attackBonus, int armourBonus, TerrainStyle terrainStyle) {
        return switch (unitType){
            case CAVALRY -> new CavalryUnit(unitName, health, new ShortSword(), new PlateArmour(50, armour), attackBonus, armourBonus, terrainStyle);
            case INFANTRY -> new InfantryUnit(unitName, health, new Bow(new LinkedList<>()), new PlateArmour(50, armour), attackBonus, armourBonus, terrainStyle);
            case RANGEDUNIT -> new RangedUnit(unitName, health, new Bow(new LinkedList<>()), new PlateArmour(50, armour), attackBonus, armourBonus, terrainStyle);
            case CAVALRYCOMMANDER -> new ChivalryCommanderUnit(unitName, health, new ShortSword(), new PlateArmour(50, armour), attackBonus, armourBonus, terrainStyle);
        };
    }

    /**
     * Makes a simple unit that only needs name and health.
     * @param unitType the unit type.
     * @param unitName the unit name.
     * @param health the unit health.
     * @param terrainStyle the style of the terrain.
     * @return the unit matching the input.
     */
    public Unit makeSimpleUnit(UnitType unitType, String unitName, int health, TerrainStyle terrainStyle) {
        checkIfObjectIsNull(unitType, "unit type");
        checkIfNumberIsValid(health, "health");
        checkString(unitName, "unit name");
        return switch (unitType){
            case CAVALRY -> new CavalryUnit(unitName, health, terrainStyle);
            case INFANTRY -> new InfantryUnit(unitName, health, terrainStyle);
            case RANGEDUNIT -> new RangedUnit(unitName, health, terrainStyle);
            case CAVALRYCOMMANDER -> new ChivalryCommanderUnit(unitName, health, terrainStyle);
        };
    }

    /**
     * Checks if a whole number is valid.
     * @param number the number to check.
     * @param prefix the prefix that the error should have.
     * @throws IllegalArgumentException gets thrown if the number is below zero.
     */
    protected void checkIfNumberIsValid(long number, String prefix){
        if (number <= 0){
            throw new IllegalArgumentException("The " + prefix + " cannot be shorter than 0.");
        }
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
