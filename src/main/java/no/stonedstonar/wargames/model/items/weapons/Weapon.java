package no.stonedstonar.wargames.model.items.weapons;

import no.stonedstonar.wargames.model.items.Item;
import no.stonedstonar.wargames.model.units.Unit;

import java.util.List;

/**
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public interface Weapon extends Item {

    /**
     * Does damage to an opponent.
     */
    void doDamage(Unit opponent);

    /**
     * Gets the attack damage.
     * @return the attack damage.
     */
    int getAttackDamage();

    /**
     * Gets all the weapon effects.
     * @return the weapon effects.
     */
    List<WeaponEffect> getWeaponEffects();
}
