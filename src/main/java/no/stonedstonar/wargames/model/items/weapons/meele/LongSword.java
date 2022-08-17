package no.stonedstonar.wargames.model.items.weapons.meele;

import no.stonedstonar.wargames.model.items.weapons.WeaponEffect;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class LongSword extends MeleeWeapon{

    private List<WeaponEffect> weaponEffects;

    private int bonusDamage;

    /**
     * Makes an instance of the LongSword class.
     */
    public LongSword() {
        super(500, 15);
        this.bonusDamage = 5;
        weaponEffects = new LinkedList<>();
        weaponEffects.add(WeaponEffect.SHARP);
    }


    @Override
    public String getItemName() {
        return getClass().getSimpleName();
    }

    @Override
    public List<WeaponEffect> getWeaponEffects() {
        return new LinkedList<>();
    }

    @Override
    public int getBonusDamage() {
        return bonusDamage;
    }
}
