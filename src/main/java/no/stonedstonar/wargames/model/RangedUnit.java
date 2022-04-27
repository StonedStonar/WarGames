package no.stonedstonar.wargames.model;

/**
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class RangedUnit extends Unit {

    private int attackBonus;

    private int armourBonus;

    private int timesAttacked;

    /**
     * Makes an instance of the ranged unit class.
     * @param unitName the name.
     * @param health the health.
     * @param attack the attack.
     * @param armour the armour.
     */
    public RangedUnit(String unitName, int health, int attack, int armour) {
        super(unitName, health, attack, armour);
        //Todo: Hvis det er på avstand
        this.attackBonus = 3;
        //Todo: Based on the distance to the enemy.
        this.armourBonus = 6;
        timesAttacked = 0;
    }

    /**
     * Makes an instance of the ranged unit class.
     * @param unitName the name.
     * @param health the health.
     */
    public RangedUnit(String unitName, int health) {
        super(unitName, health, 15, 8);
        //Todo: If there is distance.
        this.attackBonus = 3;
        //Todo: Based on the distance to the enemy.
        this.armourBonus = 2;
        timesAttacked = 0;
    }

    @Override
    public void reduceHealth(int damage) {
        super.reduceHealth(damage);
        timesAttacked += 1;
    }

    @Override
    public int getAttackBonus() {
        return attackBonus;
    }

    @Override
    public int getArmourBonus() {
        int extraArmour = switch (timesAttacked){
            case 0 -> 4;
            case 1 -> 2;
            default -> 0;
        };
        return extraArmour + armourBonus;
    }
}
