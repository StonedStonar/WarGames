package no.stonedstonar.wargames;

import no.stonedstonar.wargames.model.exception.CouldNotAddProjectileException;
import no.stonedstonar.wargames.model.items.weapons.Projectile;
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
 * Tests if general methods in ranged weapon works.
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
    @Test
    @DisplayName("Tests if constructor with three parameters works with valid input.")
    public void testIfConstructorThreeParamWorksWithValidInput(){
        testValidConstructor();
    }

    /**
     * Tests if constructor with two parameters works with invalid input.
     */
    @Test
    @DisplayName("Tests if constructor with two parameters works with invalid input.")
    public void testIfConstructorWoksWithTwoParameters(){
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

    /**
     * Tests if constructor with two parameters works with valid input.
     */
    @Test
    @DisplayName("Tests if constructor with two parameters works with valid input.")
    public void testIfConstructorTwoParamWorksWithValidInput(){
        testValidConstructor();
    }

    /**
     * Tests if the ranged weapon can be made with valid input.
     */
    private void testValidConstructor(){
        int maxDurability = 50;
        List<Arrow> arrows = makeArrows();
        int meleeDamage = 2;
        int reloadTime = 1;
        int bonusDamage = 2;
        try {
            rangedWeapon = new Bow(maxDurability, arrows, meleeDamage, reloadTime, bonusDamage);
        }catch (IllegalArgumentException exception){
            addError("Expected the ", "bow to be made since input is valid");
        }
    }

    /**
     * Tests if addProjectiles works with invalid input.
     */
    @Test
    @DisplayName("Tests if addProjectiles works with invalid input.")
    public void testIfAddProjectilesWorksWithInvalidInput(){
        List<Projectile> arrows = new LinkedList<>(makeArrows());
        rangedWeapon.removeProjectiles(1);
        try {
            rangedWeapon.addProjectiles(null);
            addError(getIllegalPrefix(), "the projectile list is null");
        }catch (CouldNotAddProjectileException exception) {}
        try {
            try {
                rangedWeapon.addProjectiles(arrows);
            }catch (CouldNotAddProjectileException exception){
                addError(getIllegalPrefix(), "the input projectiles are not already in the weapon");
            }
            rangedWeapon.addProjectiles(arrows);
        }catch (CouldNotAddProjectileException exception){
            addError(getIllegalPrefix(), "arrows are already in the bow");
        }
    }

    /**
     * Tests if addProjectiles works with valid input.
     */
    @Test
    @DisplayName("Tests if addProjectiles works with valid input.")
    public void testIfAddProjectilesWorksWithValidInput(){
        try{
            rangedWeapon.removeProjectiles(1);
            rangedWeapon.addProjectiles(new LinkedList<>(makeArrows()));
        }catch (CouldNotAddProjectileException exception){
            addError("Expected the", "projectiles to be added since the input is valid");
        }
    }

    /**
     * Tests if changeProjectiles works with invalid input.
     */
    @Test
    @DisplayName("Tests if changeProjectiles works with invalid input.")
    public void testIfChangeProjectilesWorksWithInvalidInput(){
        try {
            rangedWeapon.changeProjectiles(null);
        }catch (CouldNotAddProjectileException exception){
            //Todo: Fullfør denne.
        }catch (IllegalArgumentException exception){

        }
    }

    /**
     * Tests if changeProjectiles works with valid input.
     */


    /**
     * Tests if removeProjectiles works with invalid input.
     */

    /**
     * Tests if removeProjectiles works with valid input.
     */

    /**
     * Tests if doDamage works with invalid input.
     */

    /**
     * Tests if doDamage works with valid input.
     */

    /**
     * Tests if reduceDurability works with invalid input.
     */

    /**
     * Tests if addDurability works with valid input.
     */

    /**
     * Tests if setCurrentDurability works with invalid input.
     */

    /**
     * Tests if setCurrentDurability works with valid input.
     */


}
