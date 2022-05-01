package no.stonedstonar.wargames.model.army;

import no.stonedstonar.wargames.model.UnitType;
import no.stonedstonar.wargames.model.units.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a class that makes basic armies.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class ArmyPresets {


    /**
     * Makes an instance of army presets.
     */
    private ArmyPresets(){
        throw new IllegalStateException("Utility class");
    }


    /**
     * Makes an army that has the Humans preset.
     * @return the army.
     */
    public static Army getHumanArmy(){
        return fillArmyWithUnits("Footman", "Knight", "Archer", "Mountian King", 100, "Humans");
    }

    /**
     * Makes an army that has the Orcish preset.
     * @return the army.
     */
    public static Army getOrcishArmy(){
        return fillArmyWithUnits("Grunt", "Raider", "Spearman", "Gul´dan", 100, "Orcish Horde");
    }

    /**
     * Fills an army with the wanted units.
     * @param infantryName the name of the infantry unit.
     * @param cavalryName the name of the cavalry unit.
     * @param rangedName the name of the ranged unit.
     * @param commanderName the name of the commander unit.
     * @param health the health most of the units should have.
     * @param armyName the name of the army.
     * @return the new army.
     */
    public static Army fillArmyWithUnits(String infantryName, String cavalryName, String rangedName, String commanderName, int health, String armyName){
        List<Unit> units = new ArrayList<>();
        UnitFactory unitFactory = new UnitFactory();
        units.add(unitFactory.makeSimpleUnit(UnitType.CAVALRYCOMMANDER, commanderName, health/100*180));
        for (int i = 0; i < 500; i++){
            if (i < 200){
                units.add(unitFactory.makeSimpleUnit(UnitType.RANGEDUNIT, rangedName, health));
            }
            if (i < 100){
                units.add(unitFactory.makeSimpleUnit(UnitType.CAVALRY, cavalryName, health));
            }
            units.add(unitFactory.makeSimpleUnit(UnitType.INFANTRY, infantryName, health));
        }
        return new NormalArmy(armyName, units);
    }
}
