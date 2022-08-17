package no.stonedstonar.wargames.model.battle;

import no.stonedstonar.wargames.model.army.Army;
import no.stonedstonar.wargames.model.exception.CouldNotFinishBattleException;
import no.stonedstonar.wargames.model.exception.CouldNotGetArmyException;

/**
 * Represents basic methods a battle should have.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public interface Battle {

    /**
     * Simulates a battle between two or more armies.
     * @return the army that won.
     * @throws CouldNotFinishBattleException gets thrown if the battle could not finish.
     */
    Army simulateBattle() throws CouldNotFinishBattleException;

    /**
     * Gets the winning army if there is any.
     * @return gets the army. Returns null if no army has won.
     * @throws CouldNotGetArmyException gets thrown if there is no winning army.
     */
    Army getWinnerArmy() throws CouldNotGetArmyException;
}
