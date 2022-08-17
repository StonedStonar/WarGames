package no.stonedstonar.wargames;

import no.stonedstonar.wargames.model.TerrainStyle;
import no.stonedstonar.wargames.model.items.weapons.Weapon;
import no.stonedstonar.wargames.model.items.weapons.meele.ShortSword;
import no.stonedstonar.wargames.model.units.CavalryUnit;
import no.stonedstonar.wargames.model.units.InfantryUnit;
import no.stonedstonar.wargames.model.units.Unit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Represents tests for the Cavalry unit class.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class CavalryUnitTests {


    private StringBuilder stringBuilder;

    private int errors;

    private String illegalPrefix;

    private TerrainStyle terrainStyle;

    /**
     * Makes an instance of the RangedUnitTests class.
     */
    public CavalryUnitTests() {
        illegalPrefix = makeExceptionString("IllegalArgumentException");
    }

    /**
     * Adds test data to this class.
     */
    @BeforeEach
    private void addTestData(){
        terrainStyle = TerrainStyle.FOREST;
        stringBuilder = new StringBuilder();
        errors = 0;
    }

    /**
     * Checks if the tests failed and displays the results.
     */
    @AfterEach
    private void checkIfTestsFailedAndDisplayResult(){
        if(stringBuilder.length() == 0){
            assertTrue(true);
        }else {
            fail("\nAmount of errors " + errors + " listed errors: " + stringBuilder.toString());
        }
    }

    /**
     * Makes an opponent for the testing.
     * @return the opponent to attack.
     */
    private Unit makeOpponent(){
        return new InfantryUnit("Fjarne", 100, new ShortSword(), 10,2 ,3,terrainStyle);
    }

    /**
     * Adds an error with an exception in the title.
     * @param errorPrefix what it should say before the main error.
     * @param error what it should say after the error.
     * @param exception the exception that was not expected.
     */
    private void addErrorWithException(String errorPrefix, String error, Exception exception){
        addError(errorPrefix, error);
        stringBuilder.append(" and not a ").append(exception.getClass().getSimpleName());
    }

    /**
     * Makes an exception into the wanted string.
     * @param exceptionName the name of the exception.
     * @return the full exception string.
     */
    private String makeExceptionString(String exceptionName){
        return "Expected to get a " +  exceptionName + " since";
    }

    /**
     * Adds a new error to the stringbuilder.
     * @param errorPrefix what it should say before the error.
     * @param error the error to append.
     */
    private void addError(String errorPrefix, String error){
        stringBuilder.append("\n").append(errorPrefix).append(error);
        errors++;
    }

    /**
     * Tests if constructor works with invalid parameters.
     */
    @Test
    @DisplayName("Tests if constructor works with invalid parameters.")
    public void testIfConstructorWorksWithInvalidInput(){
        String unitName = "Fjell";
        int health = 100;
        Weapon attack = new ShortSword();
        int armour = 10;
        int bonusAttack = 2;
        int bonusDefence = 3;
        try {
            Unit unit = new CavalryUnit(unitName, health, attack, armour, 0, bonusDefence, terrainStyle);
            addError(illegalPrefix, "the input bonus attack bonus is 0");
        }catch (IllegalArgumentException exception){}
        try {
            Unit unit = new CavalryUnit(unitName, health, attack, armour, -5, bonusDefence, terrainStyle);
            addError(illegalPrefix, "the input attack bonus -5");
        }catch (IllegalArgumentException exception){}
        try {
            Unit unit = new CavalryUnit(unitName, health, attack, armour, bonusAttack, 0, terrainStyle);
            addError(illegalPrefix, "the input armour bonus is 0");
        }catch (IllegalArgumentException exception){}
        try {
            Unit unit = new CavalryUnit(unitName, health, attack, armour, bonusAttack, -5, terrainStyle);
            addError(illegalPrefix, "the input armour bonus is -5");
        }catch (IllegalArgumentException exception){}
        try {
            Unit unit = new CavalryUnit(unitName, health, attack, armour, bonusAttack, bonusDefence, null);
            addError(illegalPrefix, "the input terrain is null");
        }catch (IllegalArgumentException exception){}
    }

    /**
     * Tests if constructor works with valid parameters.
     */
    @Test
    @DisplayName("Tests if constructor works with valid parameters. ")
    public void testIfConstuctorWorksWithValidInput(){
        String unitName = "Fjell";
        int health = 100;
        Weapon attack = new ShortSword();
        int armour = 10;
        int bonusAttack = 2;
        int bonusDefence = 3;
        try {
            Unit unit = new CavalryUnit(unitName, health, attack, armour, bonusAttack, bonusDefence, terrainStyle);
        }catch (IllegalArgumentException exception){
            addError("Expected the", "unit to be made since the input value is valid.");
        }
    }

    /**
     * Tests if cavalry gets attack bonus on plains.
     */
    @Test
    @DisplayName("Tests if cavalry gets attack bonus on plains.")
    public void testIfCavalryGetsBonusOnPlains(){
        try {
            Unit unit = new CavalryUnit("Lars", 100, TerrainStyle.PLAINS);
            Unit unit1 = new CavalryUnit("Lars", 100, TerrainStyle.HILL);
            if (unit.getAttackBonus() <= unit1.getAttackBonus()){
                addError("Expected the first unit to have more attack bonus since its on plains.", "");
            }
        }catch (IllegalArgumentException exception){

        }
    }

    /**
     * Tests if cavalry gets zero defence in forest.
     */
    @Test
    @DisplayName("Tests if cavalry gets zero defence in forest.")
    public void testIfCavalryGetsZeroDefenceInForest(){
        try {
            Unit unit = new CavalryUnit("Lars", 100, TerrainStyle.FOREST);
            if (unit.getArmourBonus() != 0){
                addError("Expected the unit to have zero armour since its in the forest.", "");
            }
        }catch (IllegalArgumentException exception){
            addErrorWithException("Expected the unit to be made since", "the format is valid", exception);
        }
    }
}
