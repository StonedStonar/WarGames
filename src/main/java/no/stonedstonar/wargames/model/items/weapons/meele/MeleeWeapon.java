package no.stonedstonar.wargames.model.items.weapons.meele;

import no.stonedstonar.wargames.model.exception.CouldNotAddWeaponEffectException;
import no.stonedstonar.wargames.model.exception.CouldNotRemoveWeaponEffectException;
import no.stonedstonar.wargames.model.items.weapons.Weapon;
import no.stonedstonar.wargames.model.items.weapons.WeaponEffect;
import no.stonedstonar.wargames.model.units.Unit;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a melee weapon.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public abstract class MeleeWeapon implements Weapon {

    private int durability;

    private final int maxDurability;

    private final int damage;

    private List<WeaponEffect> weaponEffects;

    /**
     * Makes an instance of the MeleeWeapon class.
     * @param durability the durability of the weapon.
     * @param damage the damage the weapon has.
     */
    protected MeleeWeapon(int durability, int damage) {
        checkIfNumberIsBelowN(1, durability, "durability");
        checkIfNumberIsBelowN(1, damage, "damage");
        this.maxDurability = durability;
        this.durability = durability;
        this.damage = damage;
        weaponEffects = new LinkedList<>();
    }

    @Override
    public void reduceDurability(int amount) {
        checkIfNumberIsBelowN(1, amount, "amount to reduce");
        int newTotal = durability - amount;
        if (amount < 0){
            throw new IllegalArgumentException("The amount cannot be larger than the current durability.");
        }
        this.durability = newTotal;
    }

    @Override
    public void addDurability(int amount) {
        checkIfNumberIsBelowN(1, amount, "amount to add");
        int newTotal = amount + durability;
        if (amount > maxDurability){
            throw new IllegalArgumentException("The amount cannot surpass the max durability.");
        }
        this.durability = newTotal;
    }

    @Override
    public void setCurrentDurability(int amount) {
        checkIfNumberIsBelowN(0, amount, "amount");
        if (amount > maxDurability){
            throw new IllegalArgumentException("The amount cannot exeede maximum durability.");
        }
        this.durability = amount;
    }

    @Override
    public void addWeaponEffect(WeaponEffect weaponEffect) throws CouldNotAddWeaponEffectException {
        checkIfObjectIsNull(weaponEffect, "weapon effect");
        if (!weaponEffects.contains(weaponEffect)){
            weaponEffects.add(weaponEffect);
        }else {
            throw new CouldNotAddWeaponEffectException("The weapon effect is already a part of this weapon.");
        }
    }

    @Override
    public void removeWeaponEffect(WeaponEffect weaponEffect) throws CouldNotRemoveWeaponEffectException{
        checkIfObjectIsNull(weaponEffect, "weapon effect");
        if(!weaponEffects.remove(weaponEffect)){
            throw new CouldNotRemoveWeaponEffectException("The weapon effect is not a part of this weapon.");
        }
    }

    @Override
    public int getMaxDurability() {
        return maxDurability;
    }

    @Override
    public int getCurrentDurability() {
        return durability;
    }

    @Override
    public void doDamage(Unit opponent) {
        opponent.reduceHealth(getAttackDamage());
    }

    @Override
    public int getAttackDamage() {
        return damage + getBonusDamage();
    }

    @Override
    public List<WeaponEffect> getWeaponEffects(){
        return weaponEffects;
    }

    /**
     * The bonus damage the weapon has.
     * @return the bonus damage.
     */
    public abstract int getBonusDamage();

    /**
     * Checks if the number is below a number N. Throws an error if the number is less than N.
     * @param n the minimum number.
     * @param number the number to check.
     * @param prefix the prefix of the error.
     */
    protected void checkIfNumberIsBelowN(int n, int number, String prefix){
        if (number < n){
            throw new IllegalArgumentException("The amount of " + prefix +  " must be above or equal to one.");
        }
    }

    /**
     * Checks if a string is of a valid format or not.
     *
     * @param stringToCheck the string you want to check.
     * @param errorPrefix   the error the exception should have if the string is invalid.
     * @throws IllegalArgumentException gets thrown if the string to check is empty or null.
     */
    protected void checkString(String stringToCheck, String errorPrefix) {
        checkIfObjectIsNull(stringToCheck, errorPrefix);
        if (stringToCheck.isEmpty()) {
            throw new IllegalArgumentException("The " + errorPrefix + " cannot be empty.");
        }
    }

    /**
     * Checks if an object is null.
     *
     * @param object the object you want to check.
     * @param error  the error message the exception should have.
     * @throws IllegalArgumentException gets thrown if the object is null.
     */
    protected void checkIfObjectIsNull(Object object, String error) {
        if (object == null) {
            throw new IllegalArgumentException("The " + error + " cannot be null.");
        }
    }


}
