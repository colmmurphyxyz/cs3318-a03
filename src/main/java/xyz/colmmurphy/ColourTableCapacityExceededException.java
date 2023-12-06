package xyz.colmmurphy;

/**
 * Exception thrown when client attempts to add a colour to a palette with no space for additional data
 */
public class ColourTableCapacityExceededException extends RuntimeException {
    String message;
    public ColourTableCapacityExceededException() {
        super();
    }
    public ColourTableCapacityExceededException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
