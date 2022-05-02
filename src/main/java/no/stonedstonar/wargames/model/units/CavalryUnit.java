package no.stonedstonar.wargames.model.units;

/**
 * Represents a riding unit.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class CavalryUnit extends Unit{

    private int attackBonus;

    private int armourBonus;

    private int amountOfAttacks;

    /**
     * Makes an instance of the CavarlyUnit.
     * @param unitName the unit name.
     * @param health the health.
     * @param attack the attack.
     * @param armour the armour.
     * @param attackBonus the attack bonus.
     * @param armourBonus the armour bonus.
     */
    public CavalryUnit(String unitName, int health, int attack, int armour, int attackBonus, int armourBonus){
        super(unitName, health, attack, armour);
        //Todo: Bonus fra avstand.
        checkIfNumberIsValid(attackBonus, "attack bonus");
        checkIfNumberIsValid(armourBonus, "armour bonus");
        this.attackBonus = attackBonus;
        this.armourBonus = armourBonus;
        this.amountOfAttacks = 0;
    }
    /**
     * Makes an instance of the CavarlyUnit.
     * @param unitName the unit name.
     * @param health the health.
     */
    public CavalryUnit(String unitName, int health){
        super(unitName, health, 20, 12);
        //Todo: Bonus fra avstand.
        this.attackBonus = 2;
        this.armourBonus = 2;
        this.amountOfAttacks = 0;
    }

    @Override
    public void doAttack(Unit opponent) {
        super.doAttack(opponent);
        amountOfAttacks += 1;
    }

    @Override
    public int getAttackBonus() {
        int extraAttack = switch (amountOfAttacks){
            case 0 -> attackBonus*2;
            case 1 -> attackBonus;
            default -> 0;
        };
        return extraAttack + attackBonus;
    }

    @Override
    public int getArmourBonus() {
        return armourBonus;
    }
}
