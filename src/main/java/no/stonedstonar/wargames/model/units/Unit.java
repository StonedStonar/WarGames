package no.stonedstonar.wargames.model.units;

/**
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public abstract class Unit {

    private String unitName;

    private int health;

    private int attack;

    private int armour;


    /**
     * Makes an instance of the Unit class.
     * @param unitName the name.
     * @param health the health.
     * @param attack the attack.
     * @param armour the armour.
     */
    public Unit(String unitName, int health, int attack, int armour) {
        checkString(unitName, "unit name");
        checkIfNumberIsValid(health, "health");
        checkIfNumberIsValid(attack, "attack");
        checkIfNumberIsValid(armour, "armour");
        this.unitName = unitName;
        this.health = health;
        this.attack = attack;
        this.armour = armour;
    }

    /**
     * Attacks the input enemy.
     * @param opponent the unit to attack.
     */
    public void doAttack(Unit opponent){
        checkIfObjectIsNull(opponent, "unit");
        opponent.reduceHealth(attack + getAttackBonus());
    }

    /**
     * Sets the health to a new value.
     * @param health the new health
     */
    public void setHealth(int health){
        checkIfNumberIsNotUnderZero(health, "health");
        this.health = health;
    }

    /**
     * Reduces the health of the unit.
     * @param damage the amount that it should be reduced with.
     */
    public void reduceHealth(int damage){
        checkIfNumberIsValid(damage, "amount of health");
        int totalAttack = getArmour() - damage;
        if (-totalAttack > health){
            this.health = 0;
        }else if (totalAttack < 0){
            this.health = health + totalAttack;
        }
    }

    /**
     * Gets the name of the unit.
     * @return the name of the unit.
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * Gets the health of the unit.
     * @return the health of the unit.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Gets the attack of the unit.
     * @return the attack of the unit.
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Gets the armour of the unit.
     * @return the armour of the unit.
     */
    public int getArmour() {
        return armour;
    }

    /**
     * Checks if a whole number is valid.
     * @param number the number to check.
     * @param prefix the prefix that the error should have.
     * @throws IllegalArgumentException gets thrown if the number is below zero.
     */
    protected void checkIfNumberIsValid(long number, String prefix){
        if (number <= 0){
            throw new IllegalArgumentException("The " + prefix + " cannot be shorter than 0.");
        }
    }
    /**
     * Checks if a whole number is valid.
     * @param number the number to check.
     * @param prefix the prefix that the error should have.
     * @throws IllegalArgumentException gets thrown if the number is below zero.
     */
    protected void checkIfNumberIsNotUnderZero(long number, String prefix){
        if (number < 0){
            throw new IllegalArgumentException("The " + prefix + " cannot be shorter than 0.");
        }
    }

    /**
     * Gets the attack bonus of the unit.
     * @return the attack bonus of this unit.
     */
    public abstract int getAttackBonus();

    /**
     * Gets the amount of armour of this unit.
     * @return the armour bonus.
     */
    public abstract int getArmourBonus();

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
