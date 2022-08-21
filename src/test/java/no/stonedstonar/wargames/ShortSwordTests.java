package no.stonedstonar.wargames;

import no.stonedstonar.wargames.model.items.weapons.meele.ShortSword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests the short sword class.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public class ShortSwordTests extends TestTemplate {


    private ShortSword shortSword;

    /**
     * Makes an instance of the ShortSwordTests class.
     */
    public ShortSwordTests() {

    }

    /**
     * Sets up the tests.
     */
    @BeforeEach
    public void setUpTests(){
        shortSword = new ShortSword();
        resetTestClass();
    }

    /**
     * Tests if constructor with three parameters works with invalid input.
     */
    @Test
    @DisplayName("Tests if constructor with three parameters works with invalid input.")
    public void testsIfConstructorWithThreeParamWorksWithInvalidInput(){
        int durability = 50;
        int damage = 12;
        try {
            shortSword = new ShortSword(durability, damage, -2);
            addError(getIllegalPrefix(), "the input bonus damage is negative");
        }catch (IllegalArgumentException exception){}
    }

    /**
     * Tests if constructor with three parameters works with valid input.
     */
    @Test
    @DisplayName("Tests if constructor with three parameters works with valid input.")
    public void testsIfConstructorWithThreeParametersWorksWithValidInput(){
        try {
            shortSword = new ShortSword(50, 12, 2);
        }catch (IllegalArgumentException exception){
            addErrorWithException( "Expected the shortsword to be made since", "the input is valid", exception);
        }
    }
}
