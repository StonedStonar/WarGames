package no.stonedstonar.wargames;

import no.stonedstonar.wargames.model.InfantryUnit;
import no.stonedstonar.wargames.model.Unit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * A class that tests the basic unit class.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class UnitTests {

    private Unit unit;

    private StringBuilder stringBuilder;

    private int errors;

    private String illegalPrefix;

    /**
     * Makes an instance of the UnitTests object.
     */
    public UnitTests(){
        illegalPrefix = makeExceptionString("IllegalArgumentException");
    }

    /**
     * Adds test data to this class.
     */
    @BeforeEach
    private void addTestData(){
        try {
            this.unit = new InfantryUnit("Bjarne", 100);
        }catch (IllegalArgumentException exception){
            fail("Expected the test items to be added since the input is valid.");
        }
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
        return new InfantryUnit("Fjarne", 100, 20, 10);
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
     * Tests if constructor works with invalid input.
     */
    @Test
    @DisplayName("Tests if constructor works with invalid input.")
    public void testIfConstructorWorksWithInvalidInput(){
        String unitName = "Fjell";
        int health = 100;
        int attack = 12;
        int armour = 10;
        try {
            Unit unit = new InfantryUnit("", health, attack, armour);
            addError(illegalPrefix, "the input unit name is empty.");
        }catch (IllegalArgumentException exception){}
        try {
            Unit unit = new InfantryUnit(null, health, attack, armour);
            addError(illegalPrefix, "the input unit name is null");
        }catch (IllegalArgumentException exception){}
        try {
            Unit unit = new InfantryUnit(unitName, 0, attack, armour);
            addError(illegalPrefix, "the input health is 0");
        }catch (IllegalArgumentException exception){}
        try {
            Unit unit = new InfantryUnit(unitName, -5, attack, armour);
            addError(illegalPrefix, "the input health is negative 5");
        }catch (IllegalArgumentException exception){}
        try {
            Unit unit = new InfantryUnit(unitName, health, 0, armour);
            addError(illegalPrefix, "the input attack is zero");
        }catch (IllegalArgumentException exception){}
        try {
            Unit unit = new InfantryUnit(unitName, health, -5, armour);
            addError(illegalPrefix, "the input attack is negative 5");
        }catch (IllegalArgumentException exception){}
        try {
            Unit unit = new InfantryUnit(unitName, health, attack, 0);
            addError(illegalPrefix, "the input armour is zero");
        }catch (IllegalArgumentException exception){}
        try {
            Unit unit = new InfantryUnit(unitName, health, attack, -5);
            addError(illegalPrefix, "the input armour is negative 5");
        }catch (IllegalArgumentException exception){}
    }

    /**
     * Tests if constructor works with valid input.
     */
    @Test
    @DisplayName("Tests if constructor works with valid input.")
    public void testIfConstructorWorksWithValidInput(){
        try {
            Unit unit = new InfantryUnit("Bjarne", 100, 20, 10);
        }catch (IllegalArgumentException exception){
            addErrorWithException("", "Expected the unit to be made since the input is valid", exception);
        }
    }

    /**
     * Tests if doAttack works with invalid input.
     */
    @Test
    @DisplayName("Tests if doAttack works with invalid input.")
    public void testIfDoAttackWorksWithInvalidInput(){
        try {
            unit.doAttack(null);
            addError(illegalPrefix, "the input opponent is null");
        }catch (IllegalArgumentException exception){

        }
    }

    /**
     * Tests if doAttack works with valid input.
     */
    @Test
    @DisplayName("Tests if doAttack works with valid input.")
    public void testIfDoAttackWorksWithValidInput(){
        String error = "Expected the opponent to be attacked since the opponent should take damage";
        try {
            Unit opponent = makeOpponent();
            int healthBefore = opponent.getHealth();
            unit.doAttack(opponent);
            if (healthBefore <= opponent.getHealth()){
                addError("", error);
            }
        }catch (IllegalArgumentException exception){
            addErrorWithException("", error, exception);
        }
    }

    /**
     * Tests if setHealth works with invalid input.
     */
    @Test
    @DisplayName("Tests if setHealth works with invalid input.")
    public void testIfSetHeathWorksWithInvalidInput(){
        try {
            unit.setHealth(-5);
            addError(illegalPrefix, "the input health is zero");
        }catch (IllegalArgumentException exception){}
    }

    /**
     * Tests if setHealth works with valid input.
     */
    @Test
    @DisplayName("Tests if setHealth works with valid input.")
    public void testIfSetHealthWorksWithValidInput(){
        try {
            unit.setHealth(0);
        }catch (IllegalArgumentException exception){
            addErrorWithException("", "Expected the health to be set to 0 since its a valid input", exception);
        }
    }

    /**
     * Tests if reduceHealth works with invalid input.
     */
    @Test
    @DisplayName("Tests if reduceHealth works with invalid input.")
    public void testIfReduceHealthWorksWithInValidInput(){
        try {
            unit.reduceHealth(-5);
            addError(illegalPrefix, "the input is negative");
        }catch (IllegalArgumentException exception){}
        try {
            unit.reduceHealth(0);
            addError(illegalPrefix, "the input is 0");
        }catch (IllegalArgumentException exception){}
    }

    /**
     * Tests if reduceHealth works with valid input.
     */
    @Test
    @DisplayName("Tests if reduceHealth works with valid input.")
    public void testIfReduceHealthWorksWithValidInput(){
        String error = "Expected the unit to take damage since the input is 50";
        try {
            int beforeHealth = unit.getHealth();
            unit.reduceHealth(50);
            if (beforeHealth < unit.getHealth()){
                addError(error, "");
            }
        }catch (IllegalArgumentException exception){
            addError(illegalPrefix, error);
        }
    }



}
