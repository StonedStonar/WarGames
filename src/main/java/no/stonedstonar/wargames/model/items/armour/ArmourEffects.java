package no.stonedstonar.wargames.model.items.armour;

import no.stonedstonar.wargames.model.WeaponsEffects;
import no.stonedstonar.wargames.model.items.weapons.WeaponEffect;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents effects that an armour can have.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public enum ArmourEffects {

    /**
     * Represents an armour that is hardened.
     */
    HARDENED,

    /**
     * Represents heavy or thick armour.
     */
    THICKARMOUR,

    /**
     * Represents an armour that is light so the unit can be more agile.
     */
    LIGHT,

    /**
     * Represents an armour that is padded.
     */
    PADDED;



    /**
     * Gets the weapon effects that is blocked by the list of armour effects.
     * @param weaponsEffects the list with weapons effect(s).
     * @param armour the armour that holds the effects.
     * @return a list with the remaining weapons effects.
     */
    public static List<WeaponsEffects> getBlockedWeaponEffects(List<WeaponsEffects> weaponsEffects, Armour armour){
        checkIfObjectIsNull(weaponsEffects, "weapons effects");
        checkIfObjectIsNull(armour, "armour");
        List<WeaponEffect> weaponsEffectsToRemove = new ArrayList<>();
        List<ArmourEffects> armourEffects = armour.getArmourEffects();

        return ;
    }

    /**
     * Gets the blocked weapon effect from an enum.
     * @return the weapon effects that is blocked.
     */
    public List<WeaponEffect> getBlockedWeaponEffects(){
        List<WeaponEffect> weaponEffects = new ArrayList<>();
        switch (this){
            case HARDENED -> ;
            case LIGHT -> ;
            case THICKARMOUR -> {
                weaponEffects.add(WeaponEffect.EXPLOSIVE);

            };

        }
    }

    /**
     * Checks if an object is null.
     *
     * @param object the object you want to check.
     * @param error  the error message the exception should have.
     * @throws IllegalArgumentException gets thrown if the object is null.
     */
    private static void checkIfObjectIsNull(Object object, String error) {
        if (object == null) {
            throw new IllegalArgumentException("The " + error + " cannot be null.");
        }
    }
}
