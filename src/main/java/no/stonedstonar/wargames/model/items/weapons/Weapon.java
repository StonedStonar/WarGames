package no.stonedstonar.wargames.model.items.weapons;

import no.stonedstonar.wargames.model.exception.CouldNotAddWeaponEffectException;
import no.stonedstonar.wargames.model.exception.CouldNotRemoveWeaponEffectException;
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

    /**
     * Adds a weapon effect to the weapon.
     * @param weaponEffect the weapon effect.
     * @throws CouldNotAddWeaponEffectException gets thrown if the weapon effect could not be added.
     */
    void addWeaponEffect(WeaponEffect weaponEffect) throws CouldNotAddWeaponEffectException;

    /**
     * Removes a weapon effect from the weapon.
     * @param weaponEffect the weapon effect to remove.
     * @throws CouldNotRemoveWeaponEffectException gets thrown if the weapon effect could not be removed.
     */
    void removeWeaponEffect(WeaponEffect weaponEffect) throws CouldNotRemoveWeaponEffectException;
}
