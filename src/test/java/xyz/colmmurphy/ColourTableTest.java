package xyz.colmmurphy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColourTableTest {
    @BeforeEach
    public void beforeEach() {

    }

    @Test
    public void testConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new ColourTable(1),
                "numColours must be greater than 1");
        assertThrows(IllegalArgumentException.class, () -> new ColourTable(-16),
                "numColours must be greater than 1");
        assertDoesNotThrow(() -> new ColourTable(32));
        assertThrows(IllegalArgumentException.class, () -> new ColourTable(1025),
                "numColours must be a power of 2 less than 1025");
        assertThrows(IllegalArgumentException.class, () -> new ColourTable(2048),
                "numColours must be less than 1025");
    }
}