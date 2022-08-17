package no.stonedstonar.wargames;

import no.stonedstonar.wargames.TestTemplate;
import no.stonedstonar.wargames.model.items.weapons.WeaponEffect;
import no.stonedstonar.wargames.model.items.weapons.ranged.Arrow;
import no.stonedstonar.wargames.model.items.weapons.ranged.Bow;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Tests the bow class.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class BowTests extends TestTemplate {

    private Bow bow;

    /**
     * Makes an instance of the BowTests class.
     */
    public BowTests(){
        super();
    }

    /**
     * Sets up the tests for the bow.
     */
    @BeforeEach
    public void setUpTests(){
        try {
            this.bow = new Bow(makeArrows());
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
     * Tests if constructor with one parameter works with invalid input.
     */
    @Test
    @DisplayName("Tests if constructor with one parameter works with invalid input.")
    public void testIfConstructorOneParamWorksWithInvalidInput(){
        try {
            bow = new Bow(null);
            addError(getIllegalPrefix(), "the input is valid");
        }catch (IllegalArgumentException exception){}
    }


    /**
     * Tests if constructor with one parameter works with valid input.
     */
    @Test
    @DisplayName("Tests if constructor with one parameter works with valid input.")
    public void testIfConstructorOneParamWorksWithValidInput(){
        try {
            bow = new Bow(makeArrows());
        }catch (IllegalArgumentException exception){
            addError(getIllegalPrefix(), "the input is valid");
        }
    }

    /**
     * Tests if constructor with five parameters works with invalid input.
     */
    @Test
    @DisplayName("Tests if constructor with five parameters works with invalid input.")
    public void testIfConstructorFiveParamWorksWithInvalidInput(){
        int maxDurability = 50;
        List<Arrow> arrows = makeArrows();
        int meleeDamage = 2;
        int reloadTime = 1;
        int bonusDamage = 2;
        try {
            bow = new Bow(-2, arrows, meleeDamage, reloadTime, bonusDamage);
            addError(getIllegalPrefix(), "the input durability is negative");
        }catch (IllegalArgumentException exception){}
        try {
            bow = new Bow(0, arrows, meleeDamage, reloadTime, bonusDamage);
            addError(getIllegalPrefix(), "durability is 0");
        }catch (IllegalArgumentException exception){}
        try {
            bow = new Bow(maxDurability, null, meleeDamage, reloadTime, bonusDamage);
            addError(getIllegalPrefix(), "arrows is null");
        }catch (IllegalArgumentException exception){}

        try {
            bow = new Bow(maxDurability, arrows, meleeDamage, -2, bonusDamage);
            addError(getIllegalPrefix(),"reload time is 0");
        }catch (IllegalArgumentException exception){}
        try {
            bow = new Bow(maxDurability, arrows, meleeDamage, reloadTime, -2);
            addError(getIllegalPrefix(), "bonus damage is negative");
        }catch (IllegalArgumentException exception){}
    }

    /**
     * Test if constructor with five parameters works with valid input.
     */
    @Test
    @DisplayName("Test if constructor with five parameters works with valid input.")
    public void testIfConstructorWithFiveParametersWorksWithValidInput(){
        int maxDurability = 50;
        List<Arrow> arrows = makeArrows();
        int meleeDamage = 2;
        int reloadTime = 1;
        int bonusDamage = 2;
        try {
            bow = new Bow(maxDurability, arrows, meleeDamage, reloadTime, bonusDamage);
        }catch (IllegalArgumentException exception){
            addErrorWithException(getIllegalPrefix(), "the input is valid", exception);
        }
    }
}