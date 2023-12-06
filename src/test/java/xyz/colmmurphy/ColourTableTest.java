package xyz.colmmurphy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

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
        int beforeSize = ct.getPalette().size();
        int[] rgb = new int[] {1, 2, 3};
        assertDoesNotThrow(() -> {ct.add(rgb);} );
        assertEquals(beforeSize + 1, ct.getPalette().size());
        assertArrayEquals(ct.getPalette().get(beforeSize), rgb);
    }

    @Test
    public void testAddToFullPalette() {
        assertThrows(ColourTableCapacityExceededException.class, () ->
                        fullColourTable.add(1, 2, 3),
                "Cannot add colour to full colour palette"
        );
    }

    private static Stream<Arguments> invalidRGBValuesProvider() {
        return Stream.of(
                Arguments.of(-100, 0, 0),
                Arguments.of(10, 500, 9),
                Arguments.of(10, 50, 256),
                Arguments.of(300, 400, Integer.MAX_VALUE)
        );
    }

    @ParameterizedTest
    @MethodSource("invalidRGBValuesProvider")
    public void testRejectInvalidRGBValues(int red, int green, int blue) {
        assertThrows(IllegalArgumentException.class, () ->
                        ct.add(red, green, blue),
                "Invalid RGB value added to colour palette"
        );
    }

    @Test
    public void testRejectDuplicates() {
        ct.add(1, 1, 1);
        assertThrows(DuplicateColorException.class, () ->
                ct.add(1, 1, 1),
                "Duplicate colours are not allowed in the colour palette"
        );
    }

    private static Stream<Arguments> hexCodeValueProvider() {
        return Stream.of(
                Arguments.of("#aa11ff", 170, 17, 255),
                Arguments.of("#AABBFF", 170, 187, 255),
                Arguments.of("123456", 18, 52, 86)
        );
    }

    @ParameterizedTest
    @MethodSource("hexCodeValueProvider")
    public void testAddHexCode(String hexCode, int red, int green, int blue) {
        ct.add(red, green, blue);
        assertThrows(DuplicateColorException.class, () -> ct.add(hexCode));
    }

    private static Stream<Arguments> invalidHexCodeProvider() {
        return Stream.of(
                Arguments.of("abcdefg"),
                Arguments.of("#1111111111111111111111111111111111"),
                Arguments.of("Hello World!"),
                Arguments.of("")
        );
    }

    @ParameterizedTest
    @MethodSource("invalidHexCodeProvider")
    public void testAddInvalidHexCode(String hexCode) {
        assertThrows(IllegalArgumentException.class, () -> ct.add(hexCode));
    }
}