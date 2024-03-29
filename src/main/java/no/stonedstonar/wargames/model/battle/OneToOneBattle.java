package no.stonedstonar.wargames.model.battle;

import no.stonedstonar.wargames.model.TerrainStyle;
import no.stonedstonar.wargames.model.army.Army;
import no.stonedstonar.wargames.model.battle.Battle;
import no.stonedstonar.wargames.model.exception.CouldNotFinishBattleException;
import no.stonedstonar.wargames.model.exception.CouldNotGetArmyException;
import no.stonedstonar.wargames.model.exception.CouldNotGetUnitException;
import no.stonedstonar.wargames.model.exception.CouldNotRemoveUnitException;
import no.stonedstonar.wargames.model.units.Unit;

/**
 * Represents a basic battle where to armies face each other one unit at the time.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class OneToOneBattle implements Battle {

    private final Army armyOne;

    private final Army armyTwo;

    private Army winnerArmy;

    /**
     * Makes an instance of the Battle class.
     * @param armyOne the first army.
     * @param armyTwo the second army.
     * @param terrainStyle the style of the terrain.
     */
    public OneToOneBattle(Army armyOne, Army armyTwo, TerrainStyle terrainStyle) {
        checkIfArmyIsValid(armyOne);
        checkIfArmyIsValid(armyTwo);
        checkIfObjectIsNull(terrainStyle, "terrain style");
        this.armyOne = armyOne;
        this.armyTwo = armyTwo;
        armyOne.getAllUnits().forEach(unit -> unit.setTerrainStyle(terrainStyle));
        armyTwo.getAllUnits().forEach(unit -> unit.setTerrainStyle(terrainStyle));
    }

    @Override
    public Army simulateBattle() throws CouldNotFinishBattleException {
        try {
            do {
                Unit unitArmyOne = armyOne.getRandomUnit();
                Unit unitArmyTwo = armyTwo.getRandomUnit();
                unitsFight(unitArmyOne, unitArmyTwo);
                checkUnitsHealth(unitArmyOne, armyOne);
                checkUnitsHealth(unitArmyTwo, armyTwo);
            }while (armyOne.hasUnits() && armyTwo.hasUnits());
        }catch (CouldNotGetUnitException | CouldNotRemoveUnitException exception){
            throw new CouldNotFinishBattleException(exception.getMessage());
        }
        return findWinnerArmy(armyOne, armyTwo);
    }

    @Override
    public Army getWinnerArmy() throws CouldNotGetArmyException {
        if (winnerArmy == null){
            throw new CouldNotGetArmyException("There is no winning army.");
        }
        return winnerArmy;
    }

    /**
     * Returns the army that is winning.
     * @param armyOne the first army.
     * @param armyTwo the second army.
     * @return the army that has won. Returns <code>null</code> if no army won.
     */
    private Army findWinnerArmy(Army armyOne, Army armyTwo){
        if (armyTwo.hasUnits()){
            winnerArmy = armyTwo;
        }else if (armyOne.hasUnits()){
            winnerArmy = armyOne;
        }
        return winnerArmy;
    }

    /**
     * A method that makes two units attack another.
     * @param unitOne the first unit.
     * @param unitTwo the second unit.
     */
    private void unitsFight(Unit unitOne, Unit unitTwo){
        unitOne.doAttack(unitTwo);
        unitTwo.doAttack(unitOne);
    }

    /**
     * Checks the units health and if its 0 the unit is removed from the army.
     * @param unit the unit to check.
     * @param army the army the unit belongs too.
     */
    private void checkUnitsHealth(Unit unit, Army army) throws CouldNotRemoveUnitException {
        if (unit.getHealth() == 0){
            army.removeUnit(unit);
        }
    }

    /**
     * Checks if one army is not null or empty.
     * @param army the army to check.
     */
    private void checkIfArmyIsValid(Army army){
        checkIfObjectIsNull(army, "army");
        if (!army.hasUnits()){
            throw new IllegalArgumentException("The army must have more than 0 units.");
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
