package no.stonedstonar.wargames.model.exception;

import java.io.Serializable;

/**
 * CouldNotRemoveUnitException represents an exception that gets thrown when a unit could not be removed.
 *
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class CouldNotRemoveUnitException extends Exception implements Serializable {

    /**
     * Makes an instance of the CouldNotRemoveUnitException class.
     *
     * @param message the error message.
     */
    public CouldNotRemoveUnitException(String message) {
        super(message);

    }
}