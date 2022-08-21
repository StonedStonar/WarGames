package no.stonedstonar.wargames.model.items.armour;

import no.stonedstonar.wargames.model.items.Item;

import java.util.List;

/**
 * Represents a basic interface for an armour piece.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public interface Armour extends Item {

    /**
     * Gets the value the armour protects against.
     * @return the protection value.
     */
    int getProtection();

    /**
     * Gets all the armour effects.
     * @return the armour effects.
     */
    List<ArmourEffects> getArmourEffects();
}
