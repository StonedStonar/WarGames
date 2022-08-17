package no.stonedstonar.wargames.model.items;

import no.stonedstonar.wargames.model.exception.CouldNotAddProjectileException;
import no.stonedstonar.wargames.model.exception.CouldNotRemoveProjectileException;
import no.stonedstonar.wargames.model.items.weapons.Projectile;

import java.util.List;

/**
 * Represents an item that has some sort of projectile.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public interface RangedItem{

    /**
     * Adds projectiles until the
     * @param projectileList the list with the projectiles.
     * @throws CouldNotAddProjectileException gets thrown if the projectile(s) could not be added.
     */
    void addProjectiles(List<Projectile> projectileList) throws CouldNotAddProjectileException;

    /**
     * Changes the current projectile of the ranged weapon with a new one.
     * @param projectileList the new projectiles.
     * @return the old projectiles if there is any.
     * @throws CouldNotAddProjectileException gets thrown if the projectile could not be added.
     */
    List<Projectile> changeProjectiles(List<Projectile> projectileList) throws CouldNotAddProjectileException;

    /**
     * Removes an amount of projectiles from the ranged weapon.
     * @param amountOfProjectiles the amount of projectiles.
     * @return a list with the projectiles.
     */
    List<Projectile> removeProjectiles(int amountOfProjectiles);
}
