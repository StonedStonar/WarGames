package no.stonedstonar.wargames.model.exception;

import java.io.Serializable;

/**
 * CouldNotGetArmyException represents an exception that gets thrown when an army could not be located.
 *
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class CouldNotGetArmyException extends Exception implements Serializable {

    /**
     * Makes an instance of the CouldNotGetArmyException class.
     *
     * @param message the error message.
     */
    public CouldNotGetArmyException(String message) {
        super(message);

    }
}