package no.stonedstonar.wargames.model.items.weapons.meele;

import no.stonedstonar.wargames.model.exception.CouldNotAddWeaponEffectException;
import no.stonedstonar.wargames.model.items.weapons.WeaponEffect;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a short sword that is used in close combat.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class ShortSword extends MeleeWeapon{

    private int bonusDamage;

    /**
     * Makes an instance of the ShortSword class.
     */
    public ShortSword() {
        super(300, 12);
        bonusDamage = 0;
        try {
            addWeaponEffect(WeaponEffect.SHARP);
        }catch (CouldNotAddWeaponEffectException exception){
            throw new IllegalArgumentException(exception.getMessage());
        }
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
