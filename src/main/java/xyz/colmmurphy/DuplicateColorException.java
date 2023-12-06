package xyz.colmmurphy;

public class DuplicateColorException extends Exception {
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
