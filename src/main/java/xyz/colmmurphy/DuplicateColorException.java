package xyz.colmmurphy;

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
