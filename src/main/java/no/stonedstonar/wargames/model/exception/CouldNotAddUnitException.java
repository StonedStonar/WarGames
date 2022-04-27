package no.stonedstonar.wargames.model.exception;

import java.io.Serializable;

/**
 * CouldNotAddUnitException represents an exception that gets thrown when a unit could not be added.
 *
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class CouldNotAddUnitException extends Exception implements Serializable {

    /**
     * Makes an instance of the CouldNotAddUnitException class.
     *
     * @param message the error message.
     */
    public CouldNotAddUnitException(String message) {
        super(message);

    }
}