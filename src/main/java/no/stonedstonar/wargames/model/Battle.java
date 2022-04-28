package no.stonedstonar.wargames.model;

import no.stonedstonar.wargames.model.army.Army;
import no.stonedstonar.wargames.model.exception.CouldNotFinishBattleException;

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
}
