package no.stonedstonar.wargames.model;

/**
 * Represents a infantry unit.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class InfantryUnit extends Unit{

    private int attackBonus;

    private int armourBonus;

    /**
     * Makes an instance of the InfantryUnit class.
     * @param unitName the name.
     * @param health the health.
     * @param attack the attack.
     * @param armour the armour.
     */
    public InfantryUnit(String unitName, int health, int attack, int armour) {
        super(unitName, health, attack, armour);
        attackBonus = 2;
        armourBonus = 1;
    }

    /**
     * Makes an instance of the InfantryUnit class that has set attack and armour.
     * @param unitName the name.
     * @param health the health.
     */
    public InfantryUnit(String unitName, int health){
        super(unitName, health, 15, 10);
        //Todo: Skal ha en bonus i nærkamp.
        attackBonus = 2;
        armourBonus = 1;
    }

    @Override
    public int getAttackBonus() {
        return attackBonus;
    }

    @Override
    public int getArmourBonus() {
        return armourBonus;
    }
}
