package no.stonedstonar.wargames.model.exception;

import java.io.Serializable;

/**
 * CouldNotAddProjectileException represents an exception that gets thrown when
 *
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class CouldNotAddProjectileException extends Exception implements Serializable {

    /**
     * Makes an instance of the CouldNotAddProjectileException class.
     *
     * @param message the error message.
     */
    public CouldNotAddProjectileException(String message) {
        super(message);

    }
}