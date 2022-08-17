package no.stonedstonar.wargames.weapons;

import no.stonedstonar.wargames.TestTemplate;
import no.stonedstonar.wargames.model.items.weapons.WeaponEffect;
import no.stonedstonar.wargames.model.items.weapons.ranged.Arrow;
import no.stonedstonar.wargames.model.items.weapons.ranged.Bow;
import no.stonedstonar.wargames.model.items.weapons.ranged.RangedWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class RangedWeaponTests extends TestTemplate {

    private RangedWeapon rangedWeapon;

    /**
     * Makes an instance of the RangedWeapon class.
     */
    public RangedWeaponTests() {
        super();
    }

    /**
     * Sets up the tests for the bow.
     */
    @BeforeEach
    public void setUpTests(){
        try {
            this.rangedWeapon = new Bow(makeArrows());
        }catch (IllegalArgumentException exception){
            fail("Expected the test bow to be made since the input is valid.");
        }
        resetTestClass();
    }

    /**
     * Makes a list with arrows.
     * @return the arrow list.
     */
    private List<Arrow> makeArrows(){
        List<WeaponEffect> projectileEffects = new LinkedList<>();
        projectileEffects.add(WeaponEffect.ARMOURPENETRATION);
        List<Arrow> arrowList = new LinkedList<>();
        arrowList.add(new Arrow(10, 20, projectileEffects));
        return arrowList;
    }

    /**
     * Tests if constructor with three parameters works with invalid input.
     */
    @Test
    @DisplayName("Tests if constructor with three parameters works with invalid input.")
    public void testIfConstructorWithTwoParamsWorksWithInvalidInput(){
        int maxDurability = 50;
        List<Arrow> arrows = makeArrows();
        int meleeDamage = 2;
        int reloadTime = 1;
        int bonusDamage = 2;
        try {
            rangedWeapon = new Bow(-2, arrows, meleeDamage, reloadTime, bonusDamage);
            addError(getIllegalPrefix(), "the input durability is negative");
        }catch (IllegalArgumentException exception){}
        try {
            Bow bow = new Bow(0, arrows, meleeDamage, reloadTime, bonusDamage);
            addError(getIllegalPrefix(), "durability is 0");
        }catch (IllegalArgumentException exception){}
        try {
            Bow bow = new Bow(maxDurability, arrows, -2, reloadTime, bonusDamage);
            addError(getIllegalPrefix(), "melee damage is negative");
        }catch (IllegalArgumentException exception){}
        try {
            Bow bow = new Bow(maxDurability, arrows, 0, reloadTime, bonusDamage);
            addError(getIllegalPrefix(), "melee damage is 0");
        }catch (IllegalArgumentException exception){}
    }

    //Todo: Legg til en ting som kan holde flere enn 1 prosjektil.

    /**
     * Tests if constructor with three parameters works with valid input.
     */


    /**
     * Tests if constructor with two parameters works with invalid input.
     */

    /**
     * Tests if constructor with two parameters works with valid input.
     */
}
