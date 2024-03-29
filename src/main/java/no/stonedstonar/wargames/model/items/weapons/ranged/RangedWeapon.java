package no.stonedstonar.wargames.model.items.weapons.ranged;

import no.stonedstonar.wargames.model.exception.CouldNotAddProjectileException;
import no.stonedstonar.wargames.model.exception.CouldNotAddWeaponEffectException;
import no.stonedstonar.wargames.model.exception.CouldNotRemoveProjectileException;
import no.stonedstonar.wargames.model.exception.CouldNotRemoveWeaponEffectException;
import no.stonedstonar.wargames.model.items.RangedItem;
import no.stonedstonar.wargames.model.items.weapons.Projectile;
import no.stonedstonar.wargames.model.items.weapons.WeaponEffect;
import no.stonedstonar.wargames.model.units.Unit;
import no.stonedstonar.wargames.model.items.weapons.Weapon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a basic ranged weapon.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public abstract class RangedWeapon implements Weapon, RangedItem {

    private int damage;

    private int durability;

    private int maxDurability;

    private int maxProjectiles;

    private List<Projectile> projectiles;

    private List<WeaponEffect> weaponEffects;

    /**
     * Makes an instance of the RangedWeapon class.
     * @param maxProjectiles the amount of max projectiles.
     * @param maxDurability the durability of the bow.
     * @param damage the damage it should do in melee.
     */
    protected RangedWeapon(int maxProjectiles, int maxDurability, int damage) {
        makeRangedWeapon(maxProjectiles, maxDurability, damage);
    }

    /**
     * Makes an instance of the RangedWeapon class where there is only 1 projectile in the weapon.
     * @param maxDurability the durability of the bow.
     * @param damage the damage it should do in melee.
     */
    protected RangedWeapon(int maxDurability, int damage) {
        makeRangedWeapon(1, maxDurability, damage);
    }

    /**
     * Fills in all the fields for the ranged weapon.
     * @param maxProjectiles the amount of max projectiles.
     * @param durability the durability of the bow.
     * @param damage the damage it should do in melee.
     */
    private void makeRangedWeapon(int maxProjectiles, int durability, int damage){
        projectiles = new ArrayList<>(maxProjectiles);
        checkIfNumberIsBelowN(1, maxProjectiles, "max projectiles");
        checkIfNumberIsBelowN(1, durability, "durability");
        checkIfNumberIsBelowN(1, damage, "damage");
        this.maxProjectiles = maxProjectiles;
        this.durability = durability;
        this.maxDurability = durability;
        this.damage = damage;
        weaponEffects = new LinkedList<>();
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
    public void removeWeaponEffect(WeaponEffect weaponEffect) throws CouldNotRemoveWeaponEffectException {
        checkIfObjectIsNull(weaponEffect, "weapon effect");
        if(!weaponEffects.remove(weaponEffect)){
            throw new CouldNotRemoveWeaponEffectException("The weapon effect is not a part of this weapon.");
        }
    }

    @Override
    public void addProjectiles(List<Projectile> projectileList) throws CouldNotAddProjectileException {
        checkIfObjectIsNull(projectileList, "projectile list");
        boolean invalid = false;
        List<Projectile> newProjectiles = new LinkedList<>();
        int amount = maxProjectiles - projectiles.size();
        int i = 0;
        Iterator<? extends Projectile> it = projectileList.iterator();
        while (amount > i && it.hasNext() && !invalid){
            Projectile projectile = it.next();
            if (!projectiles.contains(projectile)){
                newProjectiles.add(projectile);
                projectiles.add(projectile);
                projectileList.remove(projectile);
            }else {
                invalid = true;
            }
            i++;
        }
        if (invalid){
            projectiles.removeAll(newProjectiles);
            projectileList.addAll(newProjectiles);
            throw new CouldNotAddProjectileException("There is a projectile that is already a part of this ranged weapon.");
        }

    }

    @Override
    public List<Projectile> changeProjectiles(List<Projectile> projectileList) throws CouldNotAddProjectileException {
        checkIfObjectIsNull(projectileList, "projectile list");
        List<Projectile> oldProjectiles;
        if (projectiles.stream().noneMatch(projectileList::contains)){
            oldProjectiles = new LinkedList<>(projectiles);
            projectiles.removeAll(oldProjectiles);
            addProjectiles(projectileList);
        }else {
            throw new CouldNotAddProjectileException("A projectile in the input list is already in the weapon.");
        }
        return oldProjectiles;
    }

    @Override
    public List<Projectile> removeProjectiles(int amountOfProjectiles) throws CouldNotRemoveProjectileException {
        checkIfNumberIsBelowN(1, amountOfProjectiles, "amount of projectiles");
        if (amountOfProjectiles > projectiles.size()){
            throw new CouldNotRemoveProjectileException("The amount of projectiles is larger than the weapon holds.");
        }
        List<Projectile> oldProjectiles = new LinkedList<>();
        for (int i = 0; i < amountOfProjectiles; i++){
            Projectile projectile = projectiles.get(i);
            oldProjectiles.add(projectile);
            projectiles.remove(projectile);
        }
        return oldProjectiles;
    }

    @Override
    public void doDamage(Unit opponent) {
        checkIfObjectIsNull(opponent, "opponent");
        int i = 0;
        List<Projectile> projectilesToFire = new LinkedList<>();
        while (i < maxProjectiles && !projectiles.isEmpty()){
            Projectile projectile = projectiles.get(0);
            projectilesToFire.add(projectile);
            projectiles.remove(projectile);
        }
        opponent.reduceHealth(projectilesToFire);
    }

    @Override
    public int getAttackDamage() {
        return projectiles.isEmpty() ? damage : projectiles.get(0).getDamage() + getBonusDamage();
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
    public int getMaxDurability() {
        return maxDurability;
    }

    @Override
    public int getCurrentDurability() {
        return durability;
    }

    @Override
    public boolean hasProjectiles(){
        return !projectiles.isEmpty();
    }

    /**
     * The time it takes to reload the weapon.
     * @return the time.
     */
    public abstract int getReloadTime();

    /**
     * The bonus damage the weapon.
     * @return the bonus damage.
     */
    public abstract int getBonusDamage();

    /**
     * Gets all the projectiles.
     * @return the list with projectiles.
     */
    public List<Projectile> getProjectiles(){
        return projectiles;
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
