package xyz.colmmurphy;

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
