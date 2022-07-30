package no.stonedstonar.wargames.model.items.weapons;

import no.stonedstonar.wargames.model.units.UnitEffects;

/**
 * Represents different effects a projectile can have.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public enum ProjectileEffect {

    /**
     * Represents a projectile that is soft when it hits metal.
     */
    SOFT,

    /**
     * Represents the effect of a projectile with explosive properties.
     */
    EXPLOSIVE,

    /**
     * Represents the effect of a projectile that has armour penetration.
     */
    ARMOURPENETRATION;

    /**
     * The unit effect that is for that projectile effect.
     * @return the unit effect.
     */
    public UnitEffects getUnitEffect(){
        return switch (this){
            case SOFT, ARMOURPENETRATION -> UnitEffects.BLEEDING;
            case EXPLOSIVE -> UnitEffects.DEEPWOUND;
        };
    }
}
