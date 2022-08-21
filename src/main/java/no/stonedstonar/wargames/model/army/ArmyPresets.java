package no.stonedstonar.wargames.model.army;

import no.stonedstonar.wargames.model.TerrainStyle;
import no.stonedstonar.wargames.model.UnitType;
import no.stonedstonar.wargames.model.exception.CouldNotAddUnitException;
import no.stonedstonar.wargames.model.units.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a class that makes basic armies.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class ArmyPresets {


    private static TerrainStyle terrainStyle = TerrainStyle.FOREST;

    /**
     * Makes an instance of army presets.
     */
    private ArmyPresets(){
        throw new IllegalStateException("Utility class");
    }


    /**
     * Makes an army that has the Humans preset.
     * @return the army.
     * @throws CouldNotAddUnitException gets thrown if a unit could not be added.
     */
    public static Army getHumanArmy() throws CouldNotAddUnitException {
        return fillArmyWithUnits("Footman", "Knight", "Archer", "Mountian King", 100, "Humans");
    }

    /**
     * Makes an army that has the Orcish preset.
     * @return the army.
     * @throws CouldNotAddUnitException gets thrown if a unit could not be added.
     */
    public static Army getOrcishArmy() throws CouldNotAddUnitException {
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
     * @throws CouldNotAddUnitException gets thrown if a unit could not be added.
     */
    public static Army fillArmyWithUnits(String infantryName, String cavalryName, String rangedName, String commanderName, int health, String armyName) throws CouldNotAddUnitException {
        Army army = new NormalArmy(armyName);
        return fillArmy(infantryName, cavalryName, rangedName, commanderName, health, army);

    }

    /**
     * Adds units to an army.
     * @param army the army to add units too.
     * @throws CouldNotAddUnitException gets thrown if a unit could not be added.
     */
    public static void fillArmyWithUnits(Army army) throws CouldNotAddUnitException {
        fillArmy("Infantry", "Cavalry", "Ranged", "Commander", 100, army);
    }

    /**
     * Fills an army with units.
     * @param infantryName the name of the infantry unit.
     * @param cavalryName the name of the cavalry unit.
     * @param rangedName the name of the ranged unit.
     * @param commanderName the name of the commander unit.
     * @param health the health most of the units should have.
     * @param army army to add units to.
     * @return the new army.
     * @throws CouldNotAddUnitException gets thrown if the unit could not be added.
     */
    private static Army fillArmy(String infantryName, String cavalryName, String rangedName, String commanderName, int health, Army army) throws CouldNotAddUnitException {
        UnitFactory unitFactory = new UnitFactory();
        army.addUnit(unitFactory.makeSimpleUnit(UnitType.CAVALRYCOMMANDER, commanderName, health/100*180, terrainStyle));
        for (int i = 0; i < 500; i++){
            if (i < 200){
                army.addUnit(unitFactory.makeSimpleUnit(UnitType.RANGEDUNIT, rangedName, health, terrainStyle));
            }
            if (i < 100){
                army.addUnit(unitFactory.makeSimpleUnit(UnitType.CAVALRY, cavalryName, health, terrainStyle));
            }
            army.addUnit(unitFactory.makeSimpleUnit(UnitType.INFANTRY, infantryName, health, terrainStyle));
        }
        return army;
    }
}
