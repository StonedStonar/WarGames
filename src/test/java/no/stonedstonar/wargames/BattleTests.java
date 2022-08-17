package no.stonedstonar.wargames;

import no.stonedstonar.wargames.model.battle.Battle;
import no.stonedstonar.wargames.model.battle.OneToOneBattle;
import no.stonedstonar.wargames.model.TerrainStyle;
import no.stonedstonar.wargames.model.army.Army;
import no.stonedstonar.wargames.model.army.NormalArmy;
import no.stonedstonar.wargames.model.exception.CouldNotAddUnitException;
import no.stonedstonar.wargames.model.exception.CouldNotFinishBattleException;
import no.stonedstonar.wargames.model.units.InfantryUnit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Tests the battle class.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class BattleTests extends TestTemplate{

    private Battle battle;

    private TerrainStyle terrainStyle;

    /**
     * Makes an instance of the battle tests.
     */
    public BattleTests(){
        super();
    }


    /**
     * Sets up the test battle.
     */
    @BeforeEach
    private void setUpTest(){
        terrainStyle = TerrainStyle.FOREST;
        try {
            battle = new OneToOneBattle(makeTestArmy("Human"), makeTestArmy("Snails"), terrainStyle);
        } catch (IllegalArgumentException | CouldNotAddUnitException e) {
            fail("Could not make test battle");
        }
        resetTestClass();
    }


    /**
     * Makes a test army.
     * @param armyName the name of the army.
     * @return the army.
     * @throws CouldNotAddUnitException gets thrown if the unit could not be added.
     */
    private Army makeTestArmy(String armyName) throws CouldNotAddUnitException {
        Army army = new NormalArmy(armyName);
        army.addUnit(new InfantryUnit("Jan " + armyName, 100, terrainStyle));
        return army;
    }

    /**
     * Tests if constructor works with invalid input.
     */
    @Test
    @DisplayName("Tests if constructor works with invalid input.")
    public void testIfConstructorWorksWithInvalidInput(){
        Army armyOne = null;
        Army armyTwo = null;
        try {
            Army army = makeTestArmy("Human");
            Army army1 = makeTestArmy("Dwarf");
        }catch (CouldNotAddUnitException exception){
            fail("Expected the armies to be made since the input is valid.");
        }
        try {

            Battle battle = new OneToOneBattle(null, armyTwo, terrainStyle);
            addError(getIllegalPrefix(), "the input army one is null");
        } catch (IllegalArgumentException e) {}
        try {
            Battle battle = new OneToOneBattle(armyOne, null, terrainStyle);
            addError(getIllegalPrefix(), "the input army two is null");
        }catch (IllegalArgumentException exception){}
        try {
            Battle battle = new OneToOneBattle(armyOne, armyTwo, null);
            addError(getIllegalPrefix(), "the input terrain is null");
        }catch (IllegalArgumentException exception){}
    }

    /**
     * Tests if constructor works with valid input.
     */
    @Test
    @DisplayName("Tests if constructor works with valid input.")
    public void testIfConstructorWorksWithValidInput(){
        try {
            Battle battle = new OneToOneBattle(makeTestArmy("human"), makeTestArmy("dwarf"), terrainStyle);
        }catch (IllegalArgumentException | CouldNotAddUnitException exception){
            addErrorWithException("Expected the", "battle to be made since the input is valid", exception);
        }
    }

    /**
     * Tests if simulateBattle works.
     */
    @Test
    @DisplayName("Tests if simulateBattle works.")
    public void testIfSimulateBattleWorks(){
        try {
            Army army = makeTestArmy("Human");
            Army army1 = new NormalArmy("Dwarf");
            army1.addUnit(new InfantryUnit("Peter", 6000, terrainStyle));
            Battle battle = new OneToOneBattle(army, army1, terrainStyle);
            Army winningArmy = battle.simulateBattle();
            if (winningArmy != army1){
                addError("Expected the dwarf army to win since they have an overpowered unit", "");
            }
        } catch (IllegalArgumentException | CouldNotAddUnitException | CouldNotFinishBattleException exception) {
            addErrorWithException("Expected the dwarf army to win since they have an overpowered unit", "", exception);
        }
    }
}
