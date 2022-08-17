package no.stonedstonar.wargames;

import no.stonedstonar.wargames.model.TerrainStyle;
import no.stonedstonar.wargames.model.items.weapons.Weapon;
import no.stonedstonar.wargames.model.items.weapons.meele.ShortSword;
import no.stonedstonar.wargames.model.units.InfantryUnit;
import no.stonedstonar.wargames.model.units.Unit;
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
public class UnitTests extends TestTemplate{

    private Unit unit;

    private TerrainStyle terrainStyle;

    /**
     * Makes an instance of the UnitTests object.
     */
    public UnitTests(){
        super();
    }

    /**
     * Adds test data to this class.
     */
    @BeforeEach
    private void addTestData(){
        this.terrainStyle = TerrainStyle.FOREST;
        try {
            this.unit = new InfantryUnit("Bjarne", 100, terrainStyle);
        }catch (IllegalArgumentException exception){
            fail("Expected the test items to be added since the input is valid.");
        }
        resetTestClass();
    }


    /**
     * Makes an opponent for the testing.
     * @return the opponent to attack.
     */
    private Unit makeOpponent(){
        return new InfantryUnit("Fjarne", 100, new ShortSword(), 10,2 ,3, terrainStyle);
    }


    /**
     * Tests if constructor works with invalid input.
     */
    @Test
    @DisplayName("Tests if constructor works with invalid input.")
    public void testIfConstructorWorksWithInvalidInput(){
        String unitName = "Fjell";
        int health = 100;
        Weapon weapon = new ShortSword();
        int armour = 10;
        int bonusAttack = 2;
        int bonusDefence = 3;
        try {
            Unit unit = new InfantryUnit("", health, weapon, armour, bonusAttack, bonusDefence, terrainStyle);
            fail("Expected a IllegalArgumentException since the unit name is invalid");
            //addError(illegalPrefix, "the input unit name is empty.");
        }catch (IllegalArgumentException exception){

        }
        try {
            Unit unit = new InfantryUnit(null, health, weapon, armour, bonusAttack, bonusDefence, terrainStyle);
            addError(getIllegalPrefix(), "the input unit name is null");
        }catch (IllegalArgumentException exception){}
        try {
            Unit unit = new InfantryUnit(unitName, 0, weapon, armour, bonusAttack, bonusDefence, terrainStyle);
            addError(getIllegalPrefix(), "the input health is 0");
        }catch (IllegalArgumentException exception){}
        try {
            Unit unit = new InfantryUnit(unitName, -5, weapon, armour, bonusAttack, bonusDefence, terrainStyle);
            addError(getIllegalPrefix(), "the input health is negative 5");
        }catch (IllegalArgumentException exception){}
        try {
            Unit unit = new InfantryUnit(unitName, health, null, armour, bonusAttack, bonusDefence, terrainStyle);
            addError(getIllegalPrefix(), "the input attack is zero");
        }catch (IllegalArgumentException exception){}
        try {
            Unit unit = new InfantryUnit(unitName, health, weapon, 0, bonusAttack, bonusDefence, terrainStyle);
            addError(getIllegalPrefix(), "the input armour is zero");
        }catch (IllegalArgumentException exception){}
        try {
            Unit unit = new InfantryUnit(unitName, health, weapon, -5, bonusAttack, bonusDefence, terrainStyle);
            addError(getIllegalPrefix(), "the input armour is negative 5");
        }catch (IllegalArgumentException exception){}
        try {
            Unit unit = new InfantryUnit(unitName, health, weapon, armour, 0, bonusDefence, terrainStyle);
            addError(getIllegalPrefix(), "the input attack bonus is 0");
        }catch (IllegalArgumentException exception){}
        try {
            Unit unit = new InfantryUnit(unitName, health, weapon, armour, -5, bonusDefence, terrainStyle);
            addError(getIllegalPrefix(), "the input attack bonus is -5");
        }catch (IllegalArgumentException exception){}
        try {
            Unit unit = new InfantryUnit(unitName, health, weapon, armour, bonusAttack, 0, terrainStyle);
            addError(getIllegalPrefix(), "the input armour bonus is 0");
        }catch (IllegalArgumentException exception){}
        try {
            Unit unit = new InfantryUnit(unitName, health, weapon, armour, bonusAttack, -5, terrainStyle);
            addError(getIllegalPrefix(), "the input armour bonus is negative 5");
        }catch (IllegalArgumentException exception){}
        try {
            Unit unit = new InfantryUnit(unitName, health, weapon, armour, bonusAttack, bonusDefence, null);
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
            Unit unit = new InfantryUnit("Bjarne", 100, new ShortSword(), 10, 2, 3, terrainStyle);
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
            addError(getIllegalPrefix(), "the input opponent is null");
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
            addError(getIllegalPrefix(), "the input health is zero");
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
            addError(getIllegalPrefix(), "the input is negative");
        }catch (IllegalArgumentException exception){}
        try {
            unit.reduceHealth(0);
            addError(getIllegalPrefix(), "the input is 0");
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
            addError(getIllegalPrefix(), error);
        }
    }



}
