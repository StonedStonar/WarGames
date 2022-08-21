package no.stonedstonar.wargames;

import no.stonedstonar.wargames.model.TerrainStyle;
import no.stonedstonar.wargames.model.items.armour.Armour;
import no.stonedstonar.wargames.model.items.armour.PlateArmour;
import no.stonedstonar.wargames.model.items.weapons.Weapon;
import no.stonedstonar.wargames.model.items.weapons.meele.ShortSword;
import no.stonedstonar.wargames.model.units.CavalryUnit;
import no.stonedstonar.wargames.model.units.InfantryUnit;
import no.stonedstonar.wargames.model.units.Unit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the Cavalry unit class.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class CavalryUnitTests extends TestTemplate{

    private TerrainStyle terrainStyle;

    /**
     * Makes an instance of the RangedUnitTests class.
     */
    public CavalryUnitTests() {
        super();
    }

    /**
     * Adds test data to this class.
     */
    @BeforeEach
    private void addTestData(){
        terrainStyle = TerrainStyle.FOREST;
        resetTestClass();
    }

    /**
     * Makes an opponent for the testing.
     * @return the opponent to attack.
     */
    private Unit makeOpponent(){
        return new InfantryUnit("Fjarne", 100, new ShortSword(), new PlateArmour(50, 10),2 ,3,terrainStyle);
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
        Armour armour = new PlateArmour(50, 10);
        int bonusAttack = 2;
        int bonusDefence = 3;
        try {
            Unit unit = new CavalryUnit(unitName, health, attack, armour, 0, bonusDefence, terrainStyle);
            addError(getIllegalPrefix(), "the input bonus attack bonus is 0");
        }catch (IllegalArgumentException exception){}
        try {
            Unit unit = new CavalryUnit(unitName, health, attack, armour, -5, bonusDefence, terrainStyle);
            addError(getIllegalPrefix(), "the input attack bonus -5");
        }catch (IllegalArgumentException exception){}
        try {
            Unit unit = new CavalryUnit(unitName, health, attack, armour, bonusAttack, 0, terrainStyle);
            addError(getIllegalPrefix(), "the input armour bonus is 0");
        }catch (IllegalArgumentException exception){}
        try {
            Unit unit = new CavalryUnit(unitName, health, attack, armour, bonusAttack, -5, terrainStyle);
            addError(getIllegalPrefix(), "the input armour bonus is -5");
        }catch (IllegalArgumentException exception){}
        try {
            Unit unit = new CavalryUnit(unitName, health, attack, armour, bonusAttack, bonusDefence, null);
            addError(getIllegalPrefix(), "the input terrain is null");
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
        Armour armour = new PlateArmour(50, 10);
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
