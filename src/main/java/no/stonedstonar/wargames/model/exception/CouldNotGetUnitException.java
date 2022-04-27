package no.stonedstonar.wargames.model.exception;

import java.io.Serializable;

/**
 * CouldNotGetUnitException represents an exception that gets thrown when a unit could not be found.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class CouldNotGetUnitException extends Exception implements Serializable {

    /**
     * Makes an instance of the CouldNotGetUnitException class.
     *
     * @param message the error message.
     */
    public CouldNotGetUnitException(String message) {
        super(message);

    }
}