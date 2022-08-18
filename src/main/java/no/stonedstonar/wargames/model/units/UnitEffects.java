package no.stonedstonar.wargames.model.units;

import java.util.List;

/**
 * Different effects that a unit could have.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public enum UnitEffects {

    /**
     * Represents a unit bleeding.
     */
    BLEEDING,

    /**
     * Represents a unit poisoned.
     */
    POISONED,

    /**
     * Represents a unit burning.
     */
    BURNING,

    /**
     * Represents a unit that is infected.
     */
    INFECTION,

    /**
     * Represents a unit that has a large open wound.
     */
    DEEPWOUND,

    /**
     * Represents a unit that has a small open wound.
     */
    SMALLWOUND,

    /**
     * Represnets a big wound.
     */
    BIGWOUND,

    /**
     * Represents a bruise.
     */
    BRUISE;

    /**
     * Gets the damage the effect has on a unit.
     * @return the damage.
     */
    public int getDamageFromEffect(){
        return switch (this){
            case BRUISE, BIGWOUND, SMALLWOUND, DEEPWOUND -> 0;
            case BLEEDING, POISONED, INFECTION -> 2;
            case BURNING -> 5;
        };
    }

    /**
     * Gets the total damage from effects.
     * @param unitEffectsList the list with effects.
     * @return the sum of the damage from the effects.
     */
    public static int getTotalDamageFromEffects(List<UnitEffects> unitEffectsList){
        return unitEffectsList.stream().mapToInt(UnitEffects::getDamageFromEffect).sum();
    }
}
