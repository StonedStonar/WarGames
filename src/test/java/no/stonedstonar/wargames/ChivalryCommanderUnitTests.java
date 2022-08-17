package no.stonedstonar.wargames;

import no.stonedstonar.wargames.model.TerrainStyle;
import no.stonedstonar.wargames.model.items.weapons.Weapon;
import no.stonedstonar.wargames.model.items.weapons.meele.ShortSword;
import no.stonedstonar.wargames.model.units.ChivalryCommanderUnit;
import no.stonedstonar.wargames.model.units.InfantryUnit;
import no.stonedstonar.wargames.model.units.Unit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Tests the Chivalry commander unit.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class ChivalryCommanderUnitTests extends TestTemplate {

    private TerrainStyle terrainStyle;

    /**
     * Makes an instance of the RangedUnitTests class.
     */
    public ChivalryCommanderUnitTests() {
        super();
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
        Weapon weapon = new ShortSword();
        int armour = 10;
        int bonusAttack = 2;
        int bonusDefence = 3;
        try {
            Unit unit = new ChivalryCommanderUnit(unitName, health, weapon, armour, 0, bonusDefence, terrainStyle);
            addError(getIllegalPrefix(), "the input bonus attack bonus is 0");
        }catch (IllegalArgumentException exception){}
        try {
            Unit unit = new ChivalryCommanderUnit(unitName, health, weapon, armour, -5, bonusDefence, terrainStyle);
            addError(getIllegalPrefix(), "the input attack bonus -5");
        }catch (IllegalArgumentException exception){}
        try {
            Unit unit = new ChivalryCommanderUnit(unitName, health, weapon, armour, bonusAttack, 0, terrainStyle);
            addError(getIllegalPrefix(), "the input armour bonus is 0");
        }catch (IllegalArgumentException exception){}
        try {
            Unit unit = new ChivalryCommanderUnit(unitName, health, weapon, armour, bonusAttack, -5, terrainStyle);
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
        int armour = 10;
        int bonusAttack = 2;
        int bonusDefence = 3;
        try {
            Unit unit = new ChivalryCommanderUnit(unitName, health, new ShortSword(), armour, bonusAttack, bonusDefence, terrainStyle);
        }catch (IllegalArgumentException exception){
            addError("Expected the", "unit to be made since the input value is valid.");
        }
    }
}
