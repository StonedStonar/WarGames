package no.stonedstonar.wargames.model.items.weapons.meele;

import no.stonedstonar.wargames.model.items.weapons.WeaponEffect;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a short sword that is used in close combat.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class ShortSword extends MeleeWeapon{

    private List<WeaponEffect> weaponEffects;

    private int bonusDamage;

    /**
     * Makes an instance of the ShortSword class.
     */
    public ShortSword() {
        super(300, 12);
        weaponEffects = new LinkedList<>();
        weaponEffects.add(WeaponEffect.SHARP);
        bonusDamage = 0;
    }

    @Override
    public List<WeaponEffect> getWeaponEffects() {
        return weaponEffects;
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
