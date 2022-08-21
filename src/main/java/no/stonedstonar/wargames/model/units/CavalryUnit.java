package no.stonedstonar.wargames.model.units;

import no.stonedstonar.wargames.model.TerrainStyle;
import no.stonedstonar.wargames.model.UnitType;
import no.stonedstonar.wargames.model.items.armour.Armour;
import no.stonedstonar.wargames.model.items.armour.PlateArmour;
import no.stonedstonar.wargames.model.items.weapons.Weapon;
import no.stonedstonar.wargames.model.items.weapons.meele.ShortSword;

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
     * @param weapon the weapon.
     * @param armour the armour that the unit wears.
     * @param attackBonus the attack bonus.
     * @param armourBonus the armour bonus.
     * @param terrainStyle the terrain of the unit.
     */
    public CavalryUnit(String unitName, int health, Weapon weapon, Armour armour, int attackBonus, int armourBonus, TerrainStyle terrainStyle){
        super(unitName, health, weapon, armour, UnitType.CAVALRY, terrainStyle);
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
     * @param weapon the weapon that the unit wields.
     * @param armour the armour that the unit wears.
     * @param attackBonus the attack bonus.
     * @param armourBonus the armour bonus.
     * @param unitType the unit type.
     * @param terrainStyle the terrain of the unit.
     */
    public CavalryUnit(String unitName, int health, Weapon weapon, Armour armour, int attackBonus, int armourBonus, UnitType unitType, TerrainStyle terrainStyle){
        super(unitName, health, weapon, armour, unitType, terrainStyle);
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
     * @param terrainStyle the terrain of the unit.
     */
    public CavalryUnit(String unitName, int health, TerrainStyle terrainStyle){
        super(unitName, health, new ShortSword(), new PlateArmour(50, 10), UnitType.CAVALRY, terrainStyle);
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
        int extraAttack = 0;
        if (getTerrainStyle() == TerrainStyle.PLAINS){
            extraAttack += 2;
        }
        extraAttack += switch (amountOfAttacks){
            case 0 -> attackBonus*2;
            case 1 -> attackBonus;
            default -> 0;
        };
        return extraAttack + attackBonus;
    }

    @Override
    public int getArmourBonus() {
        int totalArmourBonus = armourBonus;
        if (getTerrainStyle() == TerrainStyle.FOREST){
            totalArmourBonus = 0;
        }
        return totalArmourBonus;
    }
}
