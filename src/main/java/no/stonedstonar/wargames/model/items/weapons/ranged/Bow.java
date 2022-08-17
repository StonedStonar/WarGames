package no.stonedstonar.wargames.model.items.weapons.ranged;

import no.stonedstonar.wargames.model.items.weapons.WeaponEffect;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a bow used by an archer.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class Bow extends RangedWeapon{

    private final int reloadTime;

    private final int bonusDamage;

    /**
     * Makes an instance of the Bow class.
     * @param arrows the arrows to load into the bow.
     */
    public Bow(List<Arrow> arrows) {
        super(300, 2);
        checkIfObjectIsNull(arrows,"arrows");
        addProjectiles(new LinkedList<>(arrows));
        reloadTime = 0;
        bonusDamage = 0;
    }

    /**
     * Makes a custom bow with custom durability.
     * @param maxDurability the durability of the bow.
     * @param arrows the arrows.
     * @param meleeDamage the melee damage.
     * @param reloadTime reload time of the bow.
     * @param bonusDamage bonus damage for the bow.
     */
    public Bow(int maxDurability, List<Arrow> arrows, int meleeDamage, int reloadTime, int bonusDamage){
        super(maxDurability, meleeDamage);
        checkIfObjectIsNull(arrows,"arrows");
        addProjectiles(new LinkedList<>(arrows));
        checkIfNumberIsBelowN(0, reloadTime, "reload time");
        checkIfNumberIsBelowN(0, bonusDamage, "bonuns damage");
        this.reloadTime = reloadTime;
        this.bonusDamage = bonusDamage;
    }

    @Override
    public List<WeaponEffect> getWeaponEffects() {
        return new LinkedList<>();
    }

    @Override
    public int getReloadTime() {
        return reloadTime;
    }

    @Override
    public int getBonusDamage() {
        return bonusDamage;
    }

    @Override
    public String getItemName() {
        return getClass().getSimpleName();
    }
}
