package no.stonedstonar.wargames.model.items.weapons.ranged;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a longbow that does more damage but is slower to reload than a normal bow.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class LongBow extends RangedWeapon{

    private int bonusDamage;

    private int reloadTime;

    /**
     * Makes an instance of the LongBow class.
     * @param arrowList the arrows for the bow.
     */
    public LongBow(List<Arrow> arrowList) {
        super(500, 5, new LinkedList<>());
        bonusDamage = 4;
        reloadTime = 1;
    }

    @Override
    public int getReloadTime() {
        return reloadTime;
    }

    @Override
    public int getBonusDamage() {
        return bonusDamage;
    }
}
