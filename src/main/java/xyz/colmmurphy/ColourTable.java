package xyz.colmmurphy;

import java.util.ArrayList;
import java.util.Arrays;

public class ColourTable {

    private final int numColours;
    private final ArrayList<int[]> palette;

    public ColourTable(int numColours) {
        if (numColours <= 1 || numColours > 1024 || !isPowerOf2(numColours)) {
            throw new IllegalArgumentException("numColours must be a power of 2 greater than 1");
        } else {
            this.numColours = numColours;
        }
        this.palette = new ArrayList<int[]>(numColours);
    }

    private boolean isPowerOf2(int x) {
        int count = 0;
        while (x > 0) {
            count += x & 1;
            x >>= 1;
        }
        return count == 1;
    }
    public int getNumColours() {
        return this.numColours;
    }

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

    public int[] get(int index) {
        return this.palette.get(index);
    }
}
