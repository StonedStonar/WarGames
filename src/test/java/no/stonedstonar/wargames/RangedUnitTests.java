package no.stonedstonar.wargames;

import no.stonedstonar.wargames.model.TerrainStyle;
import no.stonedstonar.wargames.model.items.weapons.Weapon;
import no.stonedstonar.wargames.model.items.weapons.meele.ShortSword;
import no.stonedstonar.wargames.model.items.weapons.ranged.Bow;
import no.stonedstonar.wargames.model.units.InfantryUnit;
import no.stonedstonar.wargames.model.units.RangedUnit;
import no.stonedstonar.wargames.model.units.Unit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Tests ranged units class.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class RangedUnitTests extends TestTemplate{

    private TerrainStyle terrainStyle;

    /**
     * Makes an instance of the RangedUnitTests class.
     */
    public RangedUnitTests() {
        resetTestClass();
    }

    /**
     * Adds test data to this class.
     */
    @BeforeEach
    private void addTestData(){
        this.terrainStyle = TerrainStyle.FOREST;
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
     * Tests if constructor works with invalid parameters.
     */
    @Test
    @DisplayName("Tests if constructor works with invalid parameters.")
    public void testIfConstructorWorksWithInvalidInput(){
        String unitName = "Fjell";
        int health = 100;
        Weapon weapon = new Bow(new LinkedList<>());
        int armour = 10;
        int bonusAttack = 2;
        int bonusDefence = 3;
        try {
            Unit unit = new RangedUnit(unitName, health, weapon, armour, 0, bonusDefence, terrainStyle);
            addError(getIllegalPrefix(), "the input bonus attack bonus is 0");
        }catch (IllegalArgumentException exception){}
        try {
            Unit unit = new RangedUnit(unitName, health, weapon, armour, -5, bonusDefence, terrainStyle);
            addError(getIllegalPrefix(), "the input attack bonus -5");
        }catch (IllegalArgumentException exception){}
        try {
            Unit unit = new RangedUnit(unitName, health, weapon, armour, bonusAttack, 0, terrainStyle);
            addError(getIllegalPrefix(), "the input armour bonus is 0");
        }catch (IllegalArgumentException exception){}
        try {
            Unit unit = new RangedUnit(unitName, health, weapon, armour, bonusAttack, -5, terrainStyle);
            addError(getIllegalPrefix(), "the input armour bonus is -5");
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
        Weapon weapon = new Bow(new LinkedList<>());
        int armour = 10;
        int bonusAttack = 2;
        int bonusDefence = 3;
        try {
            Unit unit = new RangedUnit(unitName, health, weapon, armour, bonusAttack, bonusDefence, terrainStyle);
        }catch (IllegalArgumentException exception){
            addError("Expected the", "unit to be made since the input value is valid.");
        }
    }

    /**
     * Tests if RangedUnit gets more attack on a hill.
     */
    @Test
    @DisplayName("Tests if RangedUnit gets more attack on a hill.")
    public void testIfRangedUnitGetsMoreAttackOnHillI(){
        try {
            Unit unit = new RangedUnit("pepe", 100, TerrainStyle.PLAINS);
            Unit unit1 = new RangedUnit("pogg", 100, TerrainStyle.HILL);
            if (unit.getAttackBonus() >= unit1.getAttackBonus()){
                addError("Expected the unit on the hill to have more attack bonus", "");
            }
        }catch (IllegalArgumentException exception){
            addError("Expected the", "unit to be made since the input value is valid.");
        }
    }

    /**
     * Tests if RangedUnit gets less attack in a forest.
     */
    @Test
    @DisplayName("Tests if RangedUnit gets less attack in a forest.")
    public void testIfRangedUnitGetsLessAttackInForest(){
        try {
            Unit unit = new RangedUnit("pepe", 100, TerrainStyle.PLAINS);
            Unit unit1 = new RangedUnit("pogg", 100, TerrainStyle.FOREST);
            if (unit.getAttackBonus() < unit1.getAttackBonus()){
                addError("Expected the unit in the forest to have less attack bonus", "");
            }
        }catch (IllegalArgumentException exception){
            addError("Expected the", "unit to be made since the input value is valid.");
        }
    }

    /**
     * Tests if RangedUnit gets different armour bonus for three attacks.
     */
    @Test
    @DisplayName("Tests if RangedUnit gets different armour bonus for three attacks.")
    public void testIfRangedUnitHasDifferentArmourBonusForThreeAttacks(){
        try {
            Unit unit = new RangedUnit("pepe", 100, TerrainStyle.PLAINS);
            Unit unit1 = new RangedUnit("pogg", 100, TerrainStyle.FOREST);
            int previousArmour = 0;
            for (int i = 1; i < 4; i++){
                if (previousArmour == unit.getArmourBonus()) {
                    addError("Execpted the unit to have less armour", "since its been attacked " + i + " times");
                }else {
                    previousArmour = unit.getArmourBonus();
                }
                unit1.doAttack(unit);
            }
        }catch (IllegalArgumentException exception){
            addError("Expected the", "unit to be made since the input value is valid.");
        }
    }
}
