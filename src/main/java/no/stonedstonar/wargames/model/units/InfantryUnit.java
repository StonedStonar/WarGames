package no.stonedstonar.wargames.model.units;

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
     * @param attackBonus the attack bonus.
     * @param armourBonus the armour bonus.
     */
    public InfantryUnit(String unitName, int health, int attack, int armour, int attackBonus, int armourBonus) {
        super(unitName, health, attack, armour);
        checkIfNumberIsValid(attackBonus, "attack bonus");
        checkIfNumberIsValid(armourBonus, "armour bonus");
        this.armourBonus = armourBonus;
        this.attackBonus = attackBonus;
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
