package exceptions;

/**
 * Exception thrown when there is an issue with feeding an animal.
 */
public class InvalidFeedingException extends Exception {

    /**
     * Constructs a new InvalidFeedingException with null as its detail message.
     */
    public InvalidFeedingException() {
        super();
    }

    /**
     * Constructs a new InvalidFeedingException with the specified detail message.
     * 
     * @param message the detail message
     */
    public InvalidFeedingException(String message) {
        super(message);
    }

    /**
     * Constructs a new InvalidFeedingException with the specified detail message and cause.
     * 
     * @param message the detail message
     * @param cause the cause
     */
    public InvalidFeedingException(String message, Throwable cause) {
        super(message, cause);
    }
}
