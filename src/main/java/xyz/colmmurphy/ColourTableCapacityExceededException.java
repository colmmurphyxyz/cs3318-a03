package xyz.colmmurphy;

public class ColourTableCapacityExceededException extends Exception {
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
