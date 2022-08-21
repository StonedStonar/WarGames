package no.stonedstonar.wargames.model.items.armour;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class PlateArmour implements Armour{

    private int durability;

    private final int maxDurability;

    private final int protection;

    private List<ArmourEffects> armourEffects;

    /**
     * Makes an instance of plate armour.
     * @param durability the durability of the armour.
     * @param protection the protection the armour gives.
     */
    public PlateArmour(int durability, int protection){
        checkIfNumberIsBelowN(1, durability, "durability");
        checkIfNumberIsBelowN(0, protection, "durability");
        this.maxDurability = durability;
        this.durability = durability;
        this.protection = protection;
        this.armourEffects = new ArrayList<>();
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
    public String getItemName() {
        return getClass().getSimpleName();
    }

    @Override
    public int getProtection() {
        //Todo: Make this so that when the armour takes damage it decreases.
        return protection;
    }

    @Override
    public List<ArmourEffects> getArmourEffects() {
        return armourEffects;
    }

    /**
     * Checks if the number is below a number N. Throws an error if the number is less than N.
     * @param n the minimum number.
     * @param number the number to check.
     * @param prefix the prefix of the error.
     */
    private void checkIfNumberIsBelowN(int n, int number, String prefix){
        if (number < n){
            throw new IllegalArgumentException("The amount of " + prefix +  " must be above or equal to one.");
        }
    }

    /**
     * Checks if a string is of a valid format or not.
     *
     * @param stringToCheck the string you want to check.
     * @param errorPrefix   the error the exception should have if the string is invalid.
     */
    private void checkString(String stringToCheck, String errorPrefix) {
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
     */
    private void checkIfObjectIsNull(Object object, String error) {
        if (object == null) {
            throw new IllegalArgumentException("The " + error + " cannot be null.");
        }
    }
}
