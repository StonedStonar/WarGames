package no.stonedstonar.wargames.model.units;

import no.stonedstonar.wargames.model.TerrainStyle;
import no.stonedstonar.wargames.model.UnitType;
import no.stonedstonar.wargames.model.army.Army;
import no.stonedstonar.wargames.model.items.armour.Armour;
import no.stonedstonar.wargames.model.items.armour.PlateArmour;
import no.stonedstonar.wargames.model.items.weapons.Weapon;
import no.stonedstonar.wargames.model.items.weapons.meele.ShortSword;

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
     * @param weapon the weapon that the unit wields.
     * @param armour the armour that the unit wears.
     * @param attackBonus the attack bonus.
     * @param armourBonus the armour bonus.
     * @param terrainStyle the terrain of the unit.
     */
    public InfantryUnit(String unitName, int health, Weapon weapon, Armour armour, int attackBonus, int armourBonus, TerrainStyle terrainStyle) {
        super(unitName, health, weapon, armour, UnitType.INFANTRY, terrainStyle);
        checkIfNumberIsValid(attackBonus, "attack bonus");
        checkIfNumberIsValid(armourBonus, "armour bonus");
        this.armourBonus = armourBonus;
        this.attackBonus = attackBonus;
    }

    /**
     * Makes an instance of the InfantryUnit class that has set attack and armour.
     * @param unitName the name.
     * @param health the health.
     * @param terrainStyle the terrain of the unit.
     */
    public InfantryUnit(String unitName, int health, TerrainStyle terrainStyle){
        super(unitName, health, new ShortSword(), new PlateArmour(50, 10), UnitType.INFANTRY, terrainStyle);
        //Todo: Skal ha en bonus i nærkamp.
        attackBonus = 2;
        armourBonus = 1;
    }

    /**
     * Gets the bonus this unit should have.
     * @return the bonus.
     */
    private int getBonus(){
        int bonus = 0;
        if (getTerrainStyle() == TerrainStyle.FOREST){
            bonus = 2;
        }
        return bonus;
    }

    @Override
    public int getAttackBonus() {
        return (attackBonus + getBonus());
    }

    @Override
    public int getArmourBonus() {
        return (armourBonus + getBonus());
    }
}
