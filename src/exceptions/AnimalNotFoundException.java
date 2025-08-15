package exceptions;

/**
 * Exception thrown when an animal is not found in a habitat or collection.
 */
public class AnimalNotFoundException extends Exception {

    /**
     * Constructs a new AnimalNotFoundException with null as its detail message.
     */
    public AnimalNotFoundException() {
        super();
    }

    /**
     * Constructs a new AnimalNotFoundException with the specified detail message.
     * 
     * @param message the detail message
     */
    public AnimalNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new AnimalNotFoundException with the specified detail message and cause.
     * 
     * @param message the detail message
     * @param cause the cause
     */
    public AnimalNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
