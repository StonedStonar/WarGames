package no.stonedstonar.wargames.model.exception;

import java.io.Serializable;

/**
 * CouldNotRemoveWeaponEffectException represents an exception that gets thrown when
 *
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class CouldNotRemoveWeaponEffectException extends Exception implements Serializable {

    /**
     * Makes an instance of the CouldNotRemoveWeaponEffectException class.
     *
     * @param message the error message.
     */
    public CouldNotRemoveWeaponEffectException(String message) {
        super(message);

    }
}