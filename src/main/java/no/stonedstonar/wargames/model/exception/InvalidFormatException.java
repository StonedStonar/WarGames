package no.stonedstonar.wargames.model.exception;

import java.io.Serializable;

/**
 * InvalidFormatException represents an exception that gets thrown when the format of the file is invalid.
 *
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class InvalidFormatException extends Exception implements Serializable {

    /**
     * Makes an instance of the InvalidFormatException class.
     * @param message the error message.
     */
    public InvalidFormatException(String message) {
        super(message);

    }
}