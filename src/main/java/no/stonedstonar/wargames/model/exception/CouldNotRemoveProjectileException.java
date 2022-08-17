package no.stonedstonar.wargames.model.exception;

import java.io.Serializable;

/**
 * CouldNotRemoveProjectileException represents an exception that gets thrown when
 *
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class CouldNotRemoveProjectileException extends Exception implements Serializable {

    /**
     * Makes an instance of the CouldNotRemoveProjectileException class.
     *
     * @param message the error message.
     */
    public CouldNotRemoveProjectileException(String message) {
        super(message);

    }
}