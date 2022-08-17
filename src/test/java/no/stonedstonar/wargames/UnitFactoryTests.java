package no.stonedstonar.wargames;

import no.stonedstonar.wargames.model.TerrainStyle;
import no.stonedstonar.wargames.model.UnitType;
import no.stonedstonar.wargames.model.items.weapons.meele.ShortSword;
import no.stonedstonar.wargames.model.units.InfantryUnit;
import no.stonedstonar.wargames.model.units.Unit;
import no.stonedstonar.wargames.model.units.UnitFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class UnitFactoryTests {

    private StringBuilder stringBuilder;

    private int errors;

    private String illegalPrefix;

    private TerrainStyle terrainStyle;

    /**
     * Makes an instance of the RangedUnitTests class.
     */
    public UnitFactoryTests() {
        illegalPrefix = makeExceptionString("IllegalArgumentException");
    }

    /**
     * Adds test data to this class.
     */
    @BeforeEach
    private void addTestData(){
        this.terrainStyle = TerrainStyle.HILL;
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
        return new InfantryUnit("Fjarne", 100, new ShortSword(), 10,2 ,3, terrainStyle);
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
     * Tests if makeSimpleUnit works with invalid input.
     */
    @Test
    @DisplayName("Tests if makeSimpleUnit works with invalid input.")
    public void  testIfMakeSimpleUnitWorksWithInvalidInput(){
        UnitType unitType = UnitType.INFANTRY;
        String unitName = "Fjell";
        int health = 100;
        UnitFactory unitFactory = new UnitFactory();
        try {
            unitFactory.makeSimpleUnit(null, unitName, health, terrainStyle);
            addError(illegalPrefix, "the input type is null");
        }catch (IllegalArgumentException exception){}
        try {
            unitFactory.makeSimpleUnit(unitType, null, health, terrainStyle);
            addError(illegalPrefix, "the input unit name is null");
        }catch (IllegalArgumentException exception){}
        try {
            unitFactory.makeSimpleUnit(unitType, "", health, terrainStyle);
            addError(illegalPrefix, "the input unit name is empty");
        }catch (IllegalArgumentException exception){}
        try {
            unitFactory.makeSimpleUnit(unitType, unitName, 0, terrainStyle);
            addError(illegalPrefix, "the input health is 0");
        }catch (IllegalArgumentException exception){}
        try {
            unitFactory.makeSimpleUnit(unitType, unitName, -5, terrainStyle);
            addError(illegalPrefix, "the input health is -5");
        }catch (IllegalArgumentException exception){}
        try {
            unitFactory.makeSimpleUnit(unitType, unitName, health, null);
            addError(illegalPrefix, "the input terrain is null");
        }catch (IllegalArgumentException exception){}
    }

    /**
     * Tests if makeSimpleUnit works with valid input.
     */
    @Test
    @DisplayName("Tests if makeSimpleUnit works with valid input.")
    public void testIfMakeSimpleUnitWorksWithValidInput(){
        UnitType unitType = UnitType.INFANTRY;
        String unitName = "Fjell";
        int health = 100;
        UnitFactory unitFactory = new UnitFactory();
        try{
            unitFactory.makeSimpleUnit(unitType, unitName, health, terrainStyle);
        }catch (IllegalArgumentException exception){
            addErrorWithException("The input values are valid so the unit", "should be added", exception);
        }
    }

    /**
     * Tests if makeNAmountOfTypeUnit works with invalid input.
     */
    @Test
    @DisplayName("Tests if makeNAmountOfTypeUnit works with invalid input.")
    public void testIfMakeNAmountOfTypeUnitWorksWithInvalidInput(){
        int amount = 5;
        UnitType unitType = UnitType.INFANTRY;
        String unitName = "Fjell";
        int health = 100;
        UnitFactory unitFactory = new UnitFactory();
        try {
            unitFactory.makeNAmountOfTypeUnit(0, unitType, unitName, health, terrainStyle);
            addError(illegalPrefix, "the amount is 0");
        }catch (IllegalArgumentException exception){}
        try {
            unitFactory.makeNAmountOfTypeUnit(-5, unitType, unitName, health, terrainStyle);
            addError(illegalPrefix, "the amount is -5");
        }catch (IllegalArgumentException exception){}
        try {
            unitFactory.makeNAmountOfTypeUnit(amount, null, unitName, health, terrainStyle);
            addError(illegalPrefix, "the unit type is null");
        }catch (IllegalArgumentException exception){}
        try {
            unitFactory.makeNAmountOfTypeUnit(amount, unitType, null, health, terrainStyle);
            addError(illegalPrefix, "the unit name is null");
        }catch (IllegalArgumentException exception){}
        try {
            unitFactory.makeNAmountOfTypeUnit(amount, unitType, "", health, terrainStyle);
            addError(illegalPrefix, "the unit name is empty");
        }catch (IllegalArgumentException exception){}
        try {
            unitFactory.makeNAmountOfTypeUnit(amount, unitType, unitName, 0, terrainStyle);
            addError(illegalPrefix, "health is 0");
        }catch (IllegalArgumentException exception){}
        try {
            unitFactory.makeNAmountOfTypeUnit(amount, unitType, unitName, -5, terrainStyle);
            addError(illegalPrefix, "health is -5");
        }catch (IllegalArgumentException exception){}
        try {
            unitFactory.makeNAmountOfTypeUnit(amount, unitType, unitName, health, null);
            addError(illegalPrefix, "the terrain is null");
        }catch (IllegalArgumentException exception){}
    }

    /**
     * Tests if makeNAmountOfTypeUnit works with valid input.
     */
    @Test
    @DisplayName("Tests if makeNAmountOfTypeUnit works with valid input.")
    public void testIfMakeNAmountOfTypeUnitWorksWithValidInput(){
        int amount = 5;
        UnitType unitType = UnitType.INFANTRY;
        String unitName = "Fjell";
        int health = 100;
        UnitFactory unitFactory = new UnitFactory();
        try {
            unitFactory.makeNAmountOfTypeUnit(5, unitType, unitName, health, terrainStyle);
        }catch (IllegalArgumentException exception){
            addErrorWithException("Expected the units to be made since the input is valid", "", exception);
        }
    }
}
