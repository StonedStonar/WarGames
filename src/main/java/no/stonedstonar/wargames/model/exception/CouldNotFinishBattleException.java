package no.stonedstonar.wargames.model.exception;

import java.io.Serializable;

/**
 * CouldNotFinishBattle represents an exception that gets thrown when a battle could not finish.
 *
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class CouldNotFinishBattleException extends Exception implements Serializable {

    /**
     * Makes an instance of the CouldNotFinishBattleException class.
     *
     * @param message the error message.
     */
    public CouldNotFinishBattleException(String message) {
        super(message);

    }
}