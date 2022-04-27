package no.stonedstonar.wargames.model;

import no.stonedstonar.wargames.model.exception.CouldNotFinishBattleException;
import no.stonedstonar.wargames.model.exception.CouldNotGetUnitException;
import no.stonedstonar.wargames.model.exception.CouldNotRemoveUnitException;
import no.stonedstonar.wargames.model.units.Unit;

import java.util.Collection;

/**
 * Represents a basic battle where to armies face each other one unit at the time.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class OneToOneBattle implements Battle {

    private Army armyOne;

    private Army armyTwo;

    public static void main(String[] args) {
        try {
            Army army = ArmyPresets.getHumanArmy();
            Army army1 = ArmyPresets.getOrcishArmy();
            Battle battle = new OneToOneBattle(army, army1);
            Army winningArmy = battle.simulateBattle();
            String winner = "either army";
            if (winningArmy != null){
                winner = winningArmy.getArmyName();
            }
            System.out.println("The winning army is " +  winner + ".");
        }catch (CouldNotFinishBattleException exception){
            System.err.println("The battle could not be simulated. \nSomething has gone wrong.\n" + exception.getMessage());
        }
    }

    /**
     * Makes an instance of the Battle class.
     * @param armyOne the first army.
     * @param armyTwo the second army.
     */
    public OneToOneBattle(Army armyOne, Army armyTwo) {
        checkIfArmyIsValid(armyOne);
        checkIfArmyIsValid(armyTwo);
        this.armyOne = armyOne;
        this.armyTwo = armyTwo;
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
        return getWinnerArmy(armyOne, armyTwo);
    }

    /**
     * Returns the army that is winning.
     * @param armyOne the first army.
     * @param armyTwo the second army.
     * @return the army that has won. Returns <code>null</code> if no army won.
     */
    private Army getWinnerArmy(Army armyOne, Army armyTwo){
        Army winnerArmy = null;
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
