package no.stonedstonar.wargames.model;

import no.stonedstonar.wargames.model.units.Unit;

/**
 * An interface that represents a weapon that can be used by a unit.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public interface Weapon {

    /**
     * Makes the weapon deal damage to a unit.
     * @param opponent the unit to attack.
     */
    void attack(Unit opponent);
}
