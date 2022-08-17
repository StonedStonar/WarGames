package no.stonedstonar.wargames.model.items.weapons.ranged;

import no.stonedstonar.wargames.model.items.weapons.Projectile;
import no.stonedstonar.wargames.model.items.weapons.WeaponEffect;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents an arrow that can be shot from a bow-like weapon.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class Arrow implements Projectile {

    private final int damage;

    private int durability;

    private int weaponDamageBonus;

    private final int maxDurability;

    private final List<WeaponEffect> weaponEffects;

    private final List<WeaponEffect> projectileEffects;

    /**
     * Makes an instance of the Arrow class.
     * @param damage the damage the arrow has.
     * @param maxDurability the max durability of the arrow.  Set to 1 for single use.
     */
    public Arrow(int damage, int maxDurability, List<WeaponEffect> projectileEffects) {
        weaponEffects = new LinkedList<>();
        this.projectileEffects = new LinkedList<>();
        checkIfNumberIsBelowN(0, damage, "damage");
        checkIfNumberIsBelowN(1, maxDurability, "durability");
        checkIfObjectIsNull(projectileEffects, "projectile effects");
        this.damage = damage;
        this.durability = maxDurability;
        this.maxDurability = maxDurability;
        weaponDamageBonus = 0;
        this.projectileEffects.addAll(projectileEffects);
    }

    @Override
    public void setRangedWeaponEffects(int bonusDamage, List<WeaponEffect> newWeaponsEffects) {
        checkIfNumberIsBelowN(0, bonusDamage, "bonus damage");
        checkIfObjectIsNull(weaponEffects, "weapons effects");
        clearAllRangedWeaponEffects();
        this.weaponDamageBonus = bonusDamage;
        this.weaponEffects.addAll(newWeaponsEffects);
    }

    @Override
    public void clearAllRangedWeaponEffects() {
        this.weaponDamageBonus = 0;
        weaponEffects.clear();
    }

    @Override
    public int getDamage() {
        return damage + weaponDamageBonus;
    }

    @Override
    public List<WeaponEffect> getWeaponEffects() {
        return weaponEffects;
    }

    @Override
    public List<WeaponEffect> getProjectileEffects() {
        return projectileEffects;
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
            throw new IllegalArgumentException("The amount cannot exceed maximum durability.");
        }
        this.durability = amount;
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
    public String getItemName() {
        return getClass().getSimpleName();
    }

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
