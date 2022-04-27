package no.stonedstonar.wargames.model;

/**
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
     */
    public ChivalryCommanderUnit(String unitName, int health, int attack, int armour) {
        super(unitName, health, attack, armour);
    }

    /**
     * Makes an instance of the ChivalryCommanderUnit class.
     * @param unitName the name.
     * @param health the health.
     */
    public ChivalryCommanderUnit(String unitName, int health){
        super(unitName, health, 25, 15);
    }
}
