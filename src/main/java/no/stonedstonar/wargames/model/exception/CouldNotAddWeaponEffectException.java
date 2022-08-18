package no.stonedstonar.wargames.model.exception;

import java.io.Serializable;

/**
 * CouldNotAddWeaponEffectException represents an exception that gets thrown when
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class CouldNotAddWeaponEffectException extends Exception implements Serializable {

    /**
     * Makes an instance of the CouldNotAddWeaponEffectException class.
     * @param message the error message.
     */
    public CouldNotAddWeaponEffectException(String message) {
        super(message);

    }
}