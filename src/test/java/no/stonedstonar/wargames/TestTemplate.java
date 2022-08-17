package no.stonedstonar.wargames;

import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Represents basic methods that is used by all the test classes.
 * @author Steinar Hjelle Midthus
 * @version 0.1
 */
public abstract class TestTemplate {

    private int errors;

    private StringBuilder stringBuilder;

    private final String illegalPrefix;

    /**
     * Makes an instance of the test template class.
     */
    public TestTemplate(){
        illegalPrefix = makeExceptionString("IllegalArgumentException");
    }

    /**
     * Resets the errors to a default value.
     */
    protected void resetTestClass(){
        this.errors = 0;
        stringBuilder = new StringBuilder();
    }

    /**
     * Gets the illegal prefix.
     * @return the illegal prefix.
     */
    protected String getIllegalPrefix(){
        return illegalPrefix;
    }

    /**
     * Checks if the tests failed and displays the results.
     */
    @AfterEach
    protected void checkIfTestsFailedAndDisplayResult(){
        if(stringBuilder.length() == 0){
            assertTrue(true);
        }else {
            fail("\nAmount of errors " + errors + " listed errors: " + stringBuilder.toString());
        }
    }

    /**
     * Adds an error with an exception in the title.
     * @param errorPrefix what it should say before the main error.
     * @param error what it should say after the error.
     * @param exception the exception that was not expected.
     */
    protected void addErrorWithException(String errorPrefix, String error, Exception exception){
        addError(errorPrefix, error);
        stringBuilder.append(" and not a ").append(exception.getClass().getSimpleName());
    }

    /**
     * Makes an exception into the wanted string.
     * @param exceptionName the name of the exception.
     * @return the full exception string.
     */
    protected String makeExceptionString(String exceptionName){
        return "Expected to get a " +  exceptionName + " since";
    }

    /**
     * Adds a new error to the stringbuilder.
     * @param errorPrefix what it should say before the error.
     * @param error the error to append.
     */
    protected void addError(String errorPrefix, String error){
        stringBuilder.append("\n").append(errorPrefix).append(error);
        errors++;
    }
}
