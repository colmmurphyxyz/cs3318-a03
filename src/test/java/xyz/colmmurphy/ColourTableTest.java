package xyz.colmmurphy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColourTableTest {
    private ColourTable ct;
    private ColourTable fullColourTable;
    @BeforeEach
    public void beforeEach() {
        ct = new ColourTable(16);
        fullColourTable = new ColourTable(4);
        fullColourTable.add(255, 0, 0);
        fullColourTable.add(0, 255, 0);
        fullColourTable.add(0, 0, 255);
        fullColourTable.add(255, 255, 255);
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

    @Test
    public void testAdd() {
        assertDoesNotThrow(() -> {ct.add((byte) 1, (byte) 2, (byte) 3);} );
        assertThrows(ColourTableCapacityExceededException.class, () ->
                fullColourTable.add((byte) 1, (byte) 2, (byte) 3),
                "Cannot add colour to full colour palette"
        );
    }
}