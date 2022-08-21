package no.stonedstonar.wargames;

import no.stonedstonar.wargames.model.TerrainStyle;
import no.stonedstonar.wargames.model.exception.CouldNotAddWeaponEffectException;
import no.stonedstonar.wargames.model.exception.CouldNotRemoveWeaponEffectException;
import no.stonedstonar.wargames.model.items.weapons.WeaponEffect;
import no.stonedstonar.wargames.model.items.weapons.meele.MeleeWeapon;
import no.stonedstonar.wargames.model.items.weapons.meele.ShortSword;
import no.stonedstonar.wargames.model.units.InfantryUnit;
import no.stonedstonar.wargames.model.units.Unit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests the basic melee weapon class.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class MeleeWeaponTests extends TestTemplate{

    private MeleeWeapon meleeWeapon;

    private final String addWeaponEffectPrefix;

    /**
     * Makes an instance of the MeleeWeaponTests class.
     */
    public MeleeWeaponTests() {
        addWeaponEffectPrefix = makeExceptionString("CouldNotADdWeaponEffectException");
    }

    /**
     * Sets up a basic test environment.
     */
    @BeforeEach
    public void setUpTests(){
        this.meleeWeapon = new ShortSword();
        resetTestClass();
    }

    /**
     * Makes a unit for testing.
     * @return the unit.
     */
    private Unit makeUnit(){
        return new InfantryUnit("Helge", 100, TerrainStyle.FOREST);
    }

    /**
     * Tests if constructor with two parameters works with invalid input.
     */
    @Test
    @DisplayName("Tests if constructor with two parameters works with invalid input.")
    public void testIfConstructorWithTwoParamWorksWithInvalidInput(){
        int durability = 50;
        int damage = 12;
        int bonusDamage = 2;
        try {
            meleeWeapon =  new ShortSword(-2, damage, bonusDamage);
            addError(getIllegalPrefix(), "the input durability is negative");
        }catch (IllegalArgumentException exception){}
        try {
            meleeWeapon = new ShortSword(0, damage, bonusDamage);
            addError(getIllegalPrefix(), "the input durability is zero");
        }catch (IllegalArgumentException exception){}
        try {
            meleeWeapon = new ShortSword(durability, -2, bonusDamage);
            addError(getIllegalPrefix(), "the input damage is negative");
        }catch (IllegalArgumentException exception){}
        try {
            meleeWeapon = new ShortSword(durability, 0, bonusDamage);
            addError(getIllegalPrefix(), "the input damage is zero");
        }catch (IllegalArgumentException exception){}
    }

    /**
     * Tests if constructor with two parameters works with valid input.
     */
    @Test
    @DisplayName("Tests if constructor with two parameters works with valid input.")
    public void testsIfConstructorWithTwoParamsWorksWithValidInput(){
        try {
            meleeWeapon = new ShortSword(50, 12, 3);
        }catch (IllegalArgumentException exception){
            addErrorWithException("Expected the sword to be made since the input", "is valid", exception);
        }
    }

    /**
     * Tests if doDamage works with invalid input.
     */
    @Test
    @DisplayName("Tests if doDamage works with invalid input.")
    public void testDoDamageWorksWithInvalidInput(){
        try {
            meleeWeapon.doDamage(null);
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
            meleeWeapon.doDamage(makeUnit());
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
            meleeWeapon.reduceDurability(0);
            addError(getIllegalPrefix(), "the input is 0");
        }catch (IllegalArgumentException exception){}
        try {
            meleeWeapon.reduceDurability(-2);
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
            meleeWeapon.reduceDurability(1);
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
            meleeWeapon.addDurability(-2);
            addError(getIllegalPrefix(), "the input is negative");
        }catch (IllegalArgumentException exception){}
        try{
            meleeWeapon.addDurability(0);
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
            meleeWeapon.addDurability(2);
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
            meleeWeapon.setCurrentDurability(-2);
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
            meleeWeapon.setCurrentDurability(0);
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
            meleeWeapon.addWeaponEffect(null);
            addError(getIllegalPrefix(), "the input is null");
        }catch (IllegalArgumentException exception){
        }catch (CouldNotAddWeaponEffectException e) {
            addErrorWithException(getIllegalPrefix(), "the input is null", e);
        }
        try {
            meleeWeapon.addWeaponEffect(WeaponEffect.DULL);
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
            meleeWeapon.addWeaponEffect(WeaponEffect.SHARP);
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
            meleeWeapon.removeWeaponEffect(null);
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
            meleeWeapon.removeWeaponEffect(WeaponEffect.DULL);
        }catch (IllegalArgumentException | CouldNotRemoveWeaponEffectException exception){
            addErrorWithException("Expected the effect to be removed", "the input is invalid", exception);
        }
    }



}
