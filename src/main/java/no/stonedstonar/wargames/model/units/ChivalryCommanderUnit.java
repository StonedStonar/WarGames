package no.stonedstonar.wargames.model.units;

import no.stonedstonar.wargames.model.TerrainStyle;
import no.stonedstonar.wargames.model.UnitType;
import no.stonedstonar.wargames.model.items.weapons.Weapon;
import no.stonedstonar.wargames.model.items.weapons.meele.ShortSword;

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
     * @param weapon the weapon.
     * @param armour the armour.
     * @param attackBonus the attack bonus.
     * @param armourBonus the armour bonus
     * @param terrainStyle the terrain style
     */
    public ChivalryCommanderUnit(String unitName, int health, Weapon weapon, int armour, int attackBonus, int armourBonus, TerrainStyle terrainStyle) {
        super(unitName, health, weapon, armour, attackBonus, armourBonus, terrainStyle);
    }

    /**
     * Makes an instance of the ChivalryCommanderUnit class.
     * @param unitName the name.
     * @param health the health.
     * @param terrainStyle the terrain style
     */
    public ChivalryCommanderUnit(String unitName, int health, TerrainStyle terrainStyle){
        super(unitName, health, new ShortSword(), 15, 2, 2, UnitType.CAVALRYCOMMANDER, terrainStyle);
    }
}
