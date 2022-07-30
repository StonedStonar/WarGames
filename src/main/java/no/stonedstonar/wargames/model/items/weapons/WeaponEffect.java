package no.stonedstonar.wargames.model.items.weapons;

import no.stonedstonar.wargames.model.units.UnitEffects;

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
    DIRTY;

    /**
     * Gets the equivalent unit effect for the weapon effect.
     * @return the unit effect.
     */
    public UnitEffects getUnitEffect(){
        return switch (this){
            case FIRE -> UnitEffects.BURNING;
            case SHARP -> UnitEffects.BLEEDING;
            case DULL -> UnitEffects.SMALLWOUND;
            case POISON -> UnitEffects.POISONED;
            case DIRTY -> UnitEffects.INFECTION;
        };
    }
}
