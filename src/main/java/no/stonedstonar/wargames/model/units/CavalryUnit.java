package no.stonedstonar.wargames.model.units;

import no.stonedstonar.wargames.model.TerrainStyle;
import no.stonedstonar.wargames.model.UnitType;

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
     * @param terrainStyle the terrain of the unit.
     */
    public CavalryUnit(String unitName, int health, int attack, int armour, int attackBonus, int armourBonus, TerrainStyle terrainStyle){
        super(unitName, health, attack, armour, UnitType.CAVALRY, terrainStyle);
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
     * @param attack the attack.
     * @param armour the armour.
     * @param attackBonus the attack bonus.
     * @param armourBonus the armour bonus.
     * @param unitType the unit type.
     * @param terrainStyle the terrain of the unit.
     */
    public CavalryUnit(String unitName, int health, int attack, int armour, int attackBonus, int armourBonus, UnitType unitType, TerrainStyle terrainStyle){
        super(unitName, health, attack, armour, unitType, terrainStyle);
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
        super(unitName, health, 20, 12, UnitType.CAVALRY, terrainStyle);
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
