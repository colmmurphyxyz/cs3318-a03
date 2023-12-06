package xyz.colmmurphy;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class used to represent a palette of RGB colours
 */
public class ColourTable {

    /**
     * maximum number of colours supported by the palette
     */
    private final int numColours;

    /**
     * Array used to store the colours in the palette
     */
    private final ArrayList<int[]> palette;

    /**
     * Constructor to the ColourTable class
     * @param numColours maximum number of colours storable by the palette
     *                   must be greater than one and a power of 2
     */
    public ColourTable(int numColours) {
        if (numColours <= 1 || numColours > 1024 || !isPowerOf2(numColours)) {
            throw new IllegalArgumentException("numColours must be a power of 2 greater than 1");
        } else {
            this.numColours = numColours;
        }
        this.palette = new ArrayList<int[]>(numColours);
    }

    /**
     *
     * @param x positive integer
     * @return true, if x is a power of 2 (i.e. log_2(x) is an integer)
     */
    private boolean isPowerOf2(int x) {
        int count = 0;
        while (x > 0) {
            count += x & 1;
            x >>= 1;
        }
        return count == 1;
    }

    /**
     * Getter for the maximum capacity of the colour palette
     * @return maximum number of colours supported by the colour palette
     */
    public int getNumColours() {
        return this.numColours;
    }

    /**
     * Adds an rgb value to the colour palette
     * @param rgb int array of size 3, containing the red, green, and blue values respectively
     * @throws ColourTableCapacityExceededException if the colour palette is full
     * @throws IllegalArgumentException if the rgb colour provided is invalid (e.g. values are less than 0 or greater than 255)
     * @throws DuplicateColorException if the given colour already exists in the palette
     */
    public void add(int[] rgb) {
        if (this.palette.size() == this.numColours) {
            throw new ColourTableCapacityExceededException();
        }
        if (Arrays.stream(rgb).anyMatch((it) -> it < 0 || it > 255)) {
            throw new IllegalArgumentException("Invalid RBG value(s)");
        }
        for (int[] colour : this.palette) {
            if (Arrays.equals(colour, rgb)) {
                throw new DuplicateColorException("Colour " + Arrays.toString(rgb) + " already exists in the palette");
            }
        }
        this.palette.add(rgb);
    }

    public void add(int red, int green, int blue) {
        this.add(new int[] {red, green, blue});
    }

    /**
     * Overloaded method of ColourTable.add(int[])
     * @param hexCode rgb colout value, represented in hex code format. Does not need to include a leading #
     */
    public void add(String hexCode) {
        if (!hexCode.matches("^#?[a-f0-9A-F]{6}$")) {
            throw new IllegalArgumentException("not a valid Hex Code");
        }
        int startIndex = (hexCode.startsWith("#")) ? 1 : 0;
        int red = Integer.parseInt(hexCode.substring(startIndex, startIndex + 2), 16);
        int green = Integer.parseInt(hexCode.substring(startIndex + 2, startIndex + 4), 16);
        int blue = Integer.parseInt(hexCode.substring(startIndex + 4, startIndex + 6), 16);
        this.add(red, green, blue);
    }

    /**
     * get the colour at the given index of the colour palette
     * @param index index of the colour palette array
     * @return int array of the rgb value at the given index, or null if no colour exists at that index
     */
    public int[] get(int index) {
        return this.palette.get(index);
    }

    /**
     * Getter for the private palette field
     * @return colour palette
     */
    public ArrayList<int[]> getPalette() {
        return this.palette;
    }
}
