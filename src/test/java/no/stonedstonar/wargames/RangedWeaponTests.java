package no.stonedstonar.wargames;

import no.stonedstonar.wargames.model.TerrainStyle;
import no.stonedstonar.wargames.model.exception.CouldNotAddProjectileException;
import no.stonedstonar.wargames.model.exception.CouldNotAddWeaponEffectException;
import no.stonedstonar.wargames.model.exception.CouldNotRemoveProjectileException;
import no.stonedstonar.wargames.model.exception.CouldNotRemoveWeaponEffectException;
import no.stonedstonar.wargames.model.items.weapons.Projectile;
import no.stonedstonar.wargames.model.items.weapons.WeaponEffect;
import no.stonedstonar.wargames.model.items.weapons.ranged.Arrow;
import no.stonedstonar.wargames.model.items.weapons.ranged.Bow;
import no.stonedstonar.wargames.model.items.weapons.ranged.RangedWeapon;
import no.stonedstonar.wargames.model.units.InfantryUnit;
import no.stonedstonar.wargames.model.units.Unit;
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

    private final String addProjectilePrefix;

    private final String removeProjectilePrefix;

    private final String removeWeaponEffectPrefix;

    private final String addWeaponEffectPrefix;

    /**
     * Makes an instance of the RangedWeapon class.
     */
    public RangedWeaponTests() {
        super();
        addProjectilePrefix = makeExceptionString("CouldNotAddProjectileException");
        removeProjectilePrefix = makeExceptionString("CouldNotRemoveProjectileException");
        removeWeaponEffectPrefix = makeExceptionString("CouldNotRemoveWeaponEffectException");
        addWeaponEffectPrefix = makeExceptionString("CouldNotAddWeaponEffectException");
    }

    /**
     * Sets up the tests for the bow.
     */
    @BeforeEach
    public void setUpTests(){
        try {
            this.rangedWeapon = new Bow(makeArrows());
            rangedWeapon.addWeaponEffect(WeaponEffect.DULL);
        }catch (IllegalArgumentException | CouldNotAddWeaponEffectException exception){
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
     * Makes a unit for testing.
     * @return the unit.
     */
    private Unit makeUnit(){
        return new InfantryUnit("Helge", 100, TerrainStyle.FOREST);
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
        try {
            rangedWeapon.removeProjectiles(1);
            rangedWeapon.addProjectiles(null);
            addError(getIllegalPrefix(), "the projectile list is null");
        }catch (CouldNotAddProjectileException | CouldNotRemoveProjectileException exception) {
            addErrorWithException(getIllegalPrefix(), "the projectile list is null", exception);
        }catch (IllegalArgumentException exception){}
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
        }catch (CouldNotAddProjectileException | IllegalArgumentException | CouldNotRemoveProjectileException exception){
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
            addError(getIllegalPrefix(), "the input list is null");
        }catch (CouldNotAddProjectileException exception){
            addErrorWithException(getIllegalPrefix(), "the input list is null", exception);
        }catch (IllegalArgumentException exception){}
        try {
            rangedWeapon.changeProjectiles(rangedWeapon.getProjectiles());
            addError(addProjectilePrefix, "the input projectile is already in the weapon");
        }catch (CouldNotAddProjectileException exception){

        }catch (IllegalArgumentException exception){
            addErrorWithException(addProjectilePrefix, "the input projectiles is already in the weapon", exception);
        }
    }

    /**
     * Tests if changeProjectiles works with valid input.
     */
    @Test
    @DisplayName("Tests if changeProjectiles works with valid input.")
    public void testIfChangeProjectielsWorksWithValidInput(){
        List<Projectile> arrows = new LinkedList<>(makeArrows());
        try {
            rangedWeapon.changeProjectiles(arrows);
        } catch (CouldNotAddProjectileException | IllegalArgumentException exception) {
            addErrorWithException("Expected the projectiles to be changed", "the input is valid.", exception);
        }
    }


    /**
     * Tests if removeProjectiles works with invalid input.
     */
    @Test
    @DisplayName("Tests if removeProjectiles works with invalid input.")
    public void testIfRemoveProjectilesWorksWithInvalidInput(){
        try {
            rangedWeapon.removeProjectiles(-2);
            addError(getIllegalPrefix(), "input is invalid format");
        }catch (IllegalArgumentException exception){

        } catch (CouldNotRemoveProjectileException exception){
            addErrorWithException(removeProjectilePrefix, "input is invalid format", exception);
        }
        try {
            rangedWeapon.removeProjectiles(0);
            addError(getIllegalPrefix(), "input is zero");
        }catch (IllegalArgumentException exception){}
        catch (CouldNotRemoveProjectileException exception) {
            addErrorWithException(removeProjectilePrefix, "input is zero", exception);
        }
    }

    /**
     * Tests if removeProjectiles works with valid input.
     */
    @Test
    @DisplayName("Tests if removeProjectiles works with valid input.")
    public void testIfRemoveProjectilesWorksWithValidInput(){
        try {
            rangedWeapon.removeProjectiles(rangedWeapon.getProjectiles().size());
        }catch (IllegalArgumentException | CouldNotRemoveProjectileException exception){
            addErrorWithException("Expected the projectiles to be removed", "the input is valid", exception);
        }
    }

    /**
     * Tests if doDamage works with invalid input.
     */
    @Test
    @DisplayName("Tests if doDamage works with invalid input.")
    public void testDoDamageWorksWithInvalidInput(){
        try {
            rangedWeapon.doDamage(null);
            addError(getIllegalPrefix(), "the input is null");
        }catch (IllegalArgumentException exception){}
    }

    /**
     * Tests if doDamage works with valid input.
     */
    @Test
    @DisplayName("Tests if doDamage works with valid input.")
    public void testDoDamageWorksWithValidInput(){
        try {
            rangedWeapon.doDamage(makeUnit());
        }catch (IllegalArgumentException exception){
            addErrorWithException("Expected the unit to take damamge", "since its valid format", exception);
        }
    }

    /**
     * Tests if reduceDurability works with invalid input.
     */
    @Test
    @DisplayName("Tests if reduceDurability works with invalid input.")
    public void testReduceDurabilityWorksWithInvalidInput(){
        try {
            rangedWeapon.reduceDurability(0);
            addError(getIllegalPrefix(), "the input is 0");
        }catch (IllegalArgumentException exception){}
        try {
            rangedWeapon.reduceDurability(-2);
            addError(getIllegalPrefix(), "the input is -2");
        }catch (IllegalArgumentException exception){}
    }

    /**
     * Tests if reduceDurability works with valid input.
     */
    @Test
    @DisplayName("Tests if reduceDurability works with valid input.")
    public void testReduceDurabilityWorksWithValidInput(){
        try {
            rangedWeapon.reduceDurability(1);
        }catch (IllegalArgumentException exception){
            addErrorWithException("Expected the durability to be reduced", "the input is valid", exception);
        }
    }

    /**
     * Tests if addDurability works with invalid input.
     */
    @Test
    @DisplayName("Tests if addDurability works with valid input.")
    public void testAddDurabilityWorksWithInvalidInput(){
        try {
            rangedWeapon.addDurability(-2);
            addError(getIllegalPrefix(), "the input is negative");
        }catch (IllegalArgumentException exception){}
        try{
            rangedWeapon.addDurability(0);
            addError(getIllegalPrefix(), "the input is zero");
        }catch (IllegalArgumentException exception){}
    }

    /**
     * Tests if addDurability works with valid input.
     */
    @Test
    @DisplayName("Tests if addDurability works with valid input.")
    public void testAddDurabilityWorksWithValidInput(){
        try {
            rangedWeapon.addDurability(2);
        }catch (IllegalArgumentException exception){
            addErrorWithException("Expected the durability to be added", "the input is valid", exception);
        }
    }

    /**
     * Tests if setCurrentDurability works with invalid input.
     */
    @Test
    @DisplayName("Tests if setCurrentDurability works with invalid input.")
    public void testSetCurrentDurabilityWorksWithInvalidInput(){
        try {
            rangedWeapon.setCurrentDurability(-2);
            addError(getIllegalPrefix(), "the input durability is negative");
        }catch (IllegalArgumentException exception){}
    }

    /**
     * Tests if setCurrentDurability works with valid input.
     */
    @Test
    @DisplayName("Tests if setCurrentDurability works with valid input.")
    public void testSetCurrentDurabilityWorksWithValidInput(){
        try {
            rangedWeapon.setCurrentDurability(0);
        }catch (IllegalArgumentException exception){
            addErrorWithException("Expected the durability to be set", "the input is valid", exception);
        }
    }

    /**
     * Tests if addWeaponEffect works with invalid input.
     */
    @Test
    @DisplayName("Tests if addWeaponEffect works with invalid input.")
    public void testIfAddWeaponEffectWorksWithInvalidInput(){
        try{
            rangedWeapon.addWeaponEffect(null);
            addError(getIllegalPrefix(), "the input is null");
        }catch (IllegalArgumentException exception){
        }catch (CouldNotAddWeaponEffectException e) {
            addErrorWithException(getIllegalPrefix(), "the input is null", e);
        }
        try {
            rangedWeapon.addWeaponEffect(WeaponEffect.DULL);
            addError(addWeaponEffectPrefix, "the effect is already a part of the weapon");
        } catch (CouldNotAddWeaponEffectException e) {
        } catch (IllegalArgumentException exception){
            addErrorWithException(addWeaponEffectPrefix, "the effect is already a part of the weapon", exception);
        }
    }

    /**
     * Tests if addWeaponEffect works with valid input.
     */
    @Test
    @DisplayName("Tests if addWeaponEffect works with valid input.")
    public void testIfAddWeaponEffectWorksWithValidInput(){
        try {
            rangedWeapon.addWeaponEffect(WeaponEffect.SHARP);
        }catch (IllegalArgumentException | CouldNotAddWeaponEffectException exception){
            addErrorWithException("Expected the effect to be added", "the input is valid", exception);
        }
    }

    /**
     * Tests if removeWeaponEffect works with invalid input.
     */
    @Test
    @DisplayName("Tests if removeWeaponEffect works with invalid input.")
    public void testIfRemoveWeaponEffectWorksWithInvalidInput(){
        try {
            rangedWeapon.removeWeaponEffect(null);
            addError(getIllegalPrefix(), "the input is null");
        }catch (IllegalArgumentException exception){

        }catch (CouldNotRemoveWeaponEffectException exception){
            addErrorWithException(getIllegalPrefix(), "the input is null", exception);
        }
    }

    /**
     * Test if removeWeaponEffect works with valid input.
     */
    @Test
    @DisplayName("Test if removeWeaponEffect works with valid input.")
    public void testIfRemoveWeaponEffectWorksWithValidInput(){
        try{
            rangedWeapon.removeWeaponEffect(WeaponEffect.DULL);
        }catch (IllegalArgumentException | CouldNotRemoveWeaponEffectException exception){
            addErrorWithException("Expected the effect to be removed", "the input is invalid", exception);
        }
    }
}
