package no.stonedstonar.wargames.model.items.weapons;

import no.stonedstonar.wargames.model.items.Item;
import no.stonedstonar.wargames.model.items.weapons.WeaponEffect;

import java.util.List;

/**
 * Represents something that can be shot from an item.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public interface Projectile extends Item {

    /**
     * Sets the ranged weapons bonus damage to the bullet.
     * @param bonusDamage the bonus damage.
     * @param newWeaponsEffects the weapons effects.
     */
    void setRangedWeaponEffects(int bonusDamage, List<WeaponEffect> newWeaponsEffects);

    /**
     * Clears all the effects a weapon has put on this projectile.
     */
    void clearAllRangedWeaponEffects();

    /**
     * Gets the damage the projectile does.
     * @return the damage.
     */
    int getDamage();

    /**
     * Gets the effect of the projectile. Like fire or something else.
     * @return a list with all the effects.
     */
    List<WeaponEffect> getWeaponEffects();

    /**
     * Gets the effects the projectile has like exploding.
     * @return all the effects of the projectile.
     */
    List<WeaponEffect> getProjectileEffects();
}
