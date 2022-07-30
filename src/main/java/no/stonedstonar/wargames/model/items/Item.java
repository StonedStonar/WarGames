package no.stonedstonar.wargames.model.items;

/**
 * Represents something physical that the units can use.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public interface Item {

    /**
     * Reduces the durability of the wanted item.
     * @param amount the amount.
     */
    void reduceDurability(int amount);

    /**
     * Adds durability to the item.
     * @param amount the amount to add.
     */
    void addDurability(int amount);

    /**
     * Sets the current durability
     * @param amount the amount of durability.
     */
    void setCurrentDurability(int amount);

    /**
     * Gets the max durability.
     * @return the max durability.
     */
    int getMaxDurability();

    /**
     * Gets the durability of the item. Returns 0 if it's broken.
     * @return the durability.
     */
    int getCurrentDurability();
}
