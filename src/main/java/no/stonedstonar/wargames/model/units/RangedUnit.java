package no.stonedstonar.wargames.model.units;

import no.stonedstonar.wargames.model.TerrainStyle;
import no.stonedstonar.wargames.model.UnitType;
import no.stonedstonar.wargames.model.items.weapons.Weapon;
import no.stonedstonar.wargames.model.items.weapons.ranged.Bow;

import java.util.LinkedList;

/**
 * Represents a unit that uses ranged weapons.
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
     * @param weapon the weapon.
     * @param armour the armour.
     * @param attackBonus the attack bonus.
     * @param armourBonus the armour bonus.
     * @param terrainStyle the terrain of the unit.
     */
    public RangedUnit(String unitName, int health, Weapon weapon, int armour, int attackBonus, int armourBonus, TerrainStyle terrainStyle) {
        super(unitName, health, weapon, armour, UnitType.RANGEDUNIT, terrainStyle);
        //Todo: Hvis det er på avstand
        checkIfNumberIsValid(attackBonus, "attack bonus");
        checkIfNumberIsValid(armourBonus, "armour bonus");
        this.attackBonus = attackBonus;
        //Todo: Based on the distance to the enemy.
        this.armourBonus = armourBonus;
        timesAttacked = 0;
    }

    /**
     * Makes an instance of the ranged unit class.
     * @param unitName the name.
     * @param health the health.
     * @param terrainStyle the terrain of the unit.
     */
    public RangedUnit(String unitName, int health, TerrainStyle terrainStyle) {
        super(unitName, health, new Bow(new LinkedList<>()), 8, UnitType.RANGEDUNIT,terrainStyle);
        //Todo: If there is distance.
        this.attackBonus = 3;
        //Todo: Based on the distance to the enemy.
        this.armourBonus = 2;
        timesAttacked = 0;
    }

    /**
     * Gets the bonus of this unit.
     * @return the bonus.
     */
    private int getBonus(){
        int bonus = 0;
        if (getTerrainStyle() == TerrainStyle.FOREST){
            bonus = 2;
        }else if (getTerrainStyle() == TerrainStyle.HILL){
            bonus = -2;
        }
        return bonus;
    }

    @Override
    public void reduceHealth(int damage) {
        super.reduceHealth(damage);
        timesAttacked += 1;
    }

    @Override
    public int getAttackBonus() {
        return attackBonus - getBonus();
    }

    @Override
    public int getArmourBonus() {
        int extraArmour = switch (timesAttacked){
            case 0 -> armourBonus*2;
            case 1 -> armourBonus;
            default -> 0;
        };
        return extraArmour + armourBonus;
    }
}
