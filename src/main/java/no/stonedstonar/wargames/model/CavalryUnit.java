package no.stonedstonar.wargames.model;

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
     */
    public CavalryUnit(String unitName, int health, int attack, int armour){
        super(unitName, health, attack, armour);
        //Todo: Bonus fra avstand.
        this.attackBonus = 2;
        this.armourBonus = 2;
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
            case 0 -> 4;
            case 1 -> 2;
            default -> 0;
        };
        return extraAttack + attackBonus;
    }

    @Override
    public int getArmourBonus() {
        return armourBonus;
    }
}
