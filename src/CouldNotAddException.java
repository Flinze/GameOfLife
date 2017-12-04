package ca.bcit.comp2526.a2c;

/**
 * CouldNotAddException.
 *
 * An exception for when an item cannot be added.
 *
 * @author Felix Lin
 * @version 1.0
 */
public class CouldNotAddException extends Exception {

    /**
     * Constructs a could not add exception error.
     * @param message the message printed when error occurs.
     */
    public CouldNotAddException(String message) {
        super(message);
    }
}
