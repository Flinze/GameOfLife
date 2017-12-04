package ca.bcit.comp2526.a2c;

/**
 * CouldNotRemoveException.
 *
 * An exception for when an item cannot be removed.
 *
 * @author Felix Lin
 * @version 1.0
 */
public class CouldNotRemoveException extends Exception {

    /**
     * Constructs an exception to pass a message
     * that cannot be removed.
     *
     * @param message the message printed when an error occurs.
     */
    public CouldNotRemoveException(String message) {
        super(message);
    }
}
