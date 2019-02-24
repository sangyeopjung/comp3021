package Exceptions;

/**
 * A general exception class dealing with any map problems
 */
public abstract class InvalidMapException extends Exception {

    /**
     *
     * @param s The exception message
     */
    public InvalidMapException(String s) {
        super(s);
    }
}
