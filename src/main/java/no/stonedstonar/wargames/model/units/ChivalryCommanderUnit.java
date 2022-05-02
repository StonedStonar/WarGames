package no.stonedstonar.wargames.model.units;

/**
 * Represents a commander unit for the cavalry.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class ChivalryCommanderUnit extends CavalryUnit{

    /**
     * Makes an instance of the ChivalryCommanderUnit class.
     * @param unitName the name.
     * @param health the health.
     * @param attack the attack.
     * @param armour the armour.
     * @param attackBonus the attack bonus.
     * @param armourBonus the armour bonus
     */
    public ChivalryCommanderUnit(String unitName, int health, int attack, int armour, int attackBonus, int armourBonus) {
        super(unitName, health, attack, armour, attackBonus, armourBonus);
    }

    /**
     * Makes an instance of the ChivalryCommanderUnit class.
     * @param unitName the name.
     * @param health the health.
     */
    public ChivalryCommanderUnit(String unitName, int health){
        super(unitName, health, 25, 15, 2, 2);
    }
}
