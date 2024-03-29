package no.stonedstonar.wargames.ui.controllers;

/**
 * Represents a general controller that is used in the loading and acting in a scene.
 * @version 0.1
 * @author Group 13
 */
public interface Controller {

    /**
     * Updates the content on the wanted page.
     */
    void updateContent();

    /**
     * Makes all the content to reset that needs to be reset.
     */
    void emptyContent();

    /**
     * Sets up functions that only needs to be ran once.
     */
    void setFunctionsOnce();
}
