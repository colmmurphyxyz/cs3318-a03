package xyz.colmmurphy;

/**
 * Exception thrown when client attempts to add an already existing colour to the palette
 */
public class DuplicateColorException extends RuntimeException {
    String message;
    public DuplicateColorException() {
        super();
    }
    public DuplicateColorException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
