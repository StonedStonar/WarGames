package no.stonedstonar.wargames.model.items.weapons;

import no.stonedstonar.wargames.model.units.UnitEffects;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents different effects a weapon can have.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public enum WeaponEffect {

    /**
     * Represents the effect of a weapon on fire or that causes fire.
     */
    FIRE,

    /**
     * Represents the effect of a weapon that makes the person bleed.
     */
    SHARP,

    /**
     * Represents the effect of a weapon that is not sharp.
     */
    DULL,

    /**
     * Represents the effect of a weapon with poison on it.
     */
    POISON,

    /**
     * Represents the effect of a weapon that is dirty.
     */
    DIRTY,

    /**
     * Represents a weapon that has an explosive effect on units.
     */
    EXPLOSIVE,

    /**
     * Represents a weapon that is sharp and can slice through armour.
     */
    ARMOURPENETRATION;

    /**
     * Gets the equivalent unit effect for the weapon effect.
     * @return the unit effect.
     */
    public List<UnitEffects> getUnitEffects(){
        List<UnitEffects> unitEffects = new LinkedList<>();
        switch (this){
            case FIRE -> unitEffects.add(UnitEffects.BURNING);
            case EXPLOSIVE -> {
                unitEffects.add(UnitEffects.BIGWOUND);
                unitEffects.add(UnitEffects.BLEEDING);
            }
            case SHARP -> {
                unitEffects.add(UnitEffects.BLEEDING);
                unitEffects.add(UnitEffects.SMALLWOUND);
            }
            case DULL -> unitEffects.add(UnitEffects.BRUISE);
            case POISON -> unitEffects.add(UnitEffects.POISONED);
            case DIRTY -> unitEffects.add(UnitEffects.INFECTION);
            case ARMOURPENETRATION -> {
                unitEffects.add(UnitEffects.DEEPWOUND);
                unitEffects.add(UnitEffects.BLEEDING);
            }
        }
        return unitEffects;
    }
}
