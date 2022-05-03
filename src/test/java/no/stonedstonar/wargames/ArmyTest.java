package no.stonedstonar.wargames;

import no.stonedstonar.wargames.model.army.Army;
import no.stonedstonar.wargames.model.army.NormalArmy;
import no.stonedstonar.wargames.model.UnitType;
import no.stonedstonar.wargames.model.exception.CouldNotAddUnitException;
import no.stonedstonar.wargames.model.exception.CouldNotGetUnitException;
import no.stonedstonar.wargames.model.exception.CouldNotRemoveUnitException;
import no.stonedstonar.wargames.model.units.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Tests the army class.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class ArmyTest {

    private Army army;

    private List<Unit> units;

    private StringBuilder stringBuilder;

    private int errors;

    private String illegalPrefix;

    private String addPrefix;

    private String removePrefix;

    private String getPrefix;

    /**
     * Makes an army test instance.
     */
    public ArmyTest(){
        illegalPrefix = makeExceptionString("IllegalArgumentException");
        addPrefix = makeExceptionString("CouldNotAddUnitException");
        removePrefix = makeExceptionString("CouldNotRemoveUnitException");
        getPrefix = makeExceptionString("CouldNotGetUnitException");
    }

    /**
     * Adds test data to this class.
     */
    @BeforeEach
    private void addTestData(){
        try {
            this.units = makeUnits();
            army = new NormalArmy("hei", units);
        }catch (IllegalArgumentException exception){
            fail("Expected the test items to be added since the input is valid.");
        }
        stringBuilder = new StringBuilder();
        errors = 0;
    }

    /**
     * Makes a list with predefined units.
     * @return the list with the predefined units.
     */
    private List<Unit> makeUnits(){
        List<Unit> units = new ArrayList<>();
        units.add(new InfantryUnit("Bjarne", 100));
        units.add(new CavalryUnit("Fjell", 100));
        units.add(new ChivalryCommanderUnit("pepe",200));
        units.add(new RangedUnit("Robin", 100));
        return units;
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
        return new InfantryUnit("Fjarne", 100, 20, 10, 2, 3);
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
        String armyName = "Hei";
        try {
            army = new NormalArmy("", units);
            addError(illegalPrefix, "the input army name is empty");
        }catch (IllegalArgumentException exception){}
        try {
            army = new NormalArmy(null, units);
            addError(illegalPrefix, "the input army name is null");
        }catch (IllegalArgumentException exception){}
        try {
            army = new NormalArmy(armyName, null);
            addError(illegalPrefix, "the input unit list is null");
        }catch (IllegalArgumentException exception){}
    }

    /**
     * Tests if constructor works with valid input.
     */
    @Test
    @DisplayName("Tests if constructor works with valid input.")
    public void testIfConstructorWorksWithValidInput(){
        String armyName = "HEi";
        try {
            army = new NormalArmy(armyName, units);
        }catch (IllegalArgumentException exception){
            addErrorWithException("Expected the", "army to be made since the input is valid", exception);
        }
        try{
            army = new NormalArmy(armyName, new ArrayList<>());
        }catch (IllegalArgumentException exception){
            addErrorWithException("Expected the", "army to be made since the input is valid even the list has no elements", exception);
        }
    }

    /**
     * Tests if setArmyName works with invalid input.
     */
    @Test
    @DisplayName("Tests if setArmyName works with invalid input.")
    public void testIfSetArmyNameWorksWithInvalidInput(){
        try {
            army.setArmyName("");
            addError(illegalPrefix, "the input name is empty");
        }catch (IllegalArgumentException exception){}
        try {
            army.setArmyName(null);
            addError(illegalPrefix, "the input name is null");
        }catch (IllegalArgumentException exception){}
    }

    /**
     * Tests if setArmyName works with valid input.
     */
    @Test
    @DisplayName("Tests if setArmyName works with valid input.")
    public void testIfSetArmyNameWorksWithValidInput(){
        try {
            army.setArmyName("Hei");
        }catch (IllegalArgumentException exception){
            addErrorWithException("Expected the army to be made since", "the input is valid", exception);
        }
    }

    /**
     * Tests if addUnit works with invalid input.
     */
    @Test
    @DisplayName("Tests if addUnit works with invalid input.")
    public void testIfAddUnitWorksWithInvalidInput(){
        try {
            army.addUnit(null);
            addError(illegalPrefix, "the input unit is null");
        }catch (IllegalArgumentException exception){}
        catch (CouldNotAddUnitException exception){
            addErrorWithException(illegalPrefix, "the input unit is null", exception);
        }
        try {
            army.addUnit(units.get(0));
            addError(addPrefix, "the input unit is already in the army");
        }  catch (IllegalArgumentException exception){
            addErrorWithException(addPrefix, "the input unit is already in the army", exception);
        }catch (CouldNotAddUnitException exception) {
        }
    }

    /**
     * Tests if addUnit works with valid input.
     */
    @Test
    @DisplayName("Tests if addUnit works with valid input.")
    public void testIfAddUnitWorksWithValidInput(){
        try {
            army.addUnit(new InfantryUnit("Bjarne 2", 100));
        } catch (CouldNotAddUnitException | IllegalArgumentException exception) {
            addErrorWithException("Expeted the ", "unit to be added sience the input is valid", exception);
        }
    }

    /**
     * Tests if addAllUnits works with invalid input.
     */
    @Test
    @DisplayName("Tests if addAllUnits works with invalid input.")
    public void testIfAddAllUnitsWorksWithInvalidInput(){
        try {
            army.addAllUnits(null);
            addError(illegalPrefix, "since the input is null");
        }catch (IllegalArgumentException exception){

        }catch (CouldNotAddUnitException exception){
            addErrorWithException(illegalPrefix, "since the input is null", exception);
        }
        try {
            army.addAllUnits(units);
            addError(addPrefix, "the input units is already in the army");
        }catch (IllegalArgumentException exception){
            addErrorWithException(addPrefix, "the input units is already in the army", exception);
        }catch (CouldNotAddUnitException exception){}
    }

    /**
     * Tests if addAllUnits works with valid input.
     */
    @Test
    @DisplayName("Tests if addAllUnits works with valid input.")
    public void testIfAddAllUnitsWorksWithValidInput(){
        try {
            army.addAllUnits(makeUnits());
        }catch (IllegalArgumentException | CouldNotAddUnitException exception){
            addErrorWithException("Expected the", "unit to be added since the input is valid and there are no duplicates", exception);
        }
    }

    /**
     * Tests if removeUnit works with invalid input.
     */
    @Test
    @DisplayName("Tests if removeUnit works with invalid input.")
    public void testIfRemoveUnitWorksWithInvalidInput(){
        try {
            army.removeUnit(null);
            addError(illegalPrefix, "the input unit is null");
        }catch (IllegalArgumentException exception){

        }catch (CouldNotRemoveUnitException exception){
            addErrorWithException(illegalPrefix, "the input unit is null", exception);
        }
        try {
            army.removeUnit(new InfantryUnit("Bjarne 3", 100));
            addError(removePrefix, "the unit is not in the army");
        }catch (IllegalArgumentException exception){
            addErrorWithException(removePrefix, "the unit is not in the army", exception);
        }catch (CouldNotRemoveUnitException exception){

        }
    }

    /**
     * Tests if removeUnit works with valid input.
     */
    @Test
    @DisplayName("Tests if removeUnit works with valid input.")
    public void testIfRemoveUnitWorksWithValidInput(){
        try {
            army.removeUnit(units.get(0));
        }catch (IllegalArgumentException | CouldNotRemoveUnitException exception){
            addErrorWithException("Expected the", "unit to be removed since the input is valid", exception);
        }
    }

    /**
     * Tests if getSpecifiedUnit works with invalid input.
     */
    @Test
    @DisplayName("Tests if getSpecifiedUnit works with invalid input.")
    public void testIfGetSpecifiedUnitWorksWithInvalidInput(){
        try {
            army.getSpecifiedUnit(null);
            addError(illegalPrefix, "since the input is null");
        }catch (IllegalArgumentException exception){}
    }

    /**
     * Tests if getSpecifiedUnit works with valid input.
     */
    @Test
    @DisplayName("Tests if getSpecifiedUnit works with valid input.")
    public void testIfGetSpecifiedUnitWorksWithValidInput(){
        try {
            army.getSpecifiedUnit(UnitType.CAVALRY);
        }catch (IllegalArgumentException exception){
            addErrorWithException("Expected the", "list to be returned since the input is valid", exception);
        }
    }

    /**
     * Tests if getRandomUnit works on an empty army.
     */
    @Test
    @DisplayName("Tests if getRandomUnit works on an empty army.")
    public void testIfGetRandomUnitWorksOnEmptyArmy(){
        try {
            Army army = new NormalArmy("hei");
            army.getRandomUnit();
            addError(getPrefix, "the army is empty");
        }catch (CouldNotGetUnitException exception){}
    }
}
