package exceptions;

/**
 * Exception thrown when an animal is placed in an incompatible habitat.
 */
public class IncompatibleHabitatException extends Exception {

    /**
     * Constructs a new IncompatibleHabitatException with null as its detail message.
     */
    public IncompatibleHabitatException() {
        super();
    }

    /**
     * Constructs a new IncompatibleHabitatException with the specified detail message.
     * 
     * @param message the detail message
     */
    public IncompatibleHabitatException(String message) {
        super(message);
    }

    /**
     * Constructs a new IncompatibleHabitatException with the specified detail message and cause.
     * 
     * @param message the detail message
     * @param cause the cause
     */
    public IncompatibleHabitatException(String message, Throwable cause) {
        super(message, cause);
    }
}
