package no.stonedstonar.wargames.model.items.weapons.meele;

import no.stonedstonar.wargames.model.exception.CouldNotAddWeaponEffectException;
import no.stonedstonar.wargames.model.items.weapons.WeaponEffect;


/**
 * Represents a short sword that is used in close combat.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class ShortSword extends MeleeWeapon{

    private final int bonusDamage;

    /**
     * Makes an instance of the ShortSword class.
     */
    public ShortSword() {
        super(300, 15);
        bonusDamage = 0;
        try {
            addWeaponEffect(WeaponEffect.SHARP);
        }catch (CouldNotAddWeaponEffectException exception){
            throw new IllegalArgumentException(exception.getMessage());
        }
    }

    /**
     * Makes a custom instance of the short sword.
     * @param durability the durability of the sword.
     * @param damage the base damage.
     * @param bonusDamage the bonus damage.
     */
    public ShortSword(int durability, int damage, int bonusDamage){
        super(durability, damage);
        checkIfNumberIsBelowN(0, bonusDamage, "bonus damage");
        this.bonusDamage = bonusDamage;
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
