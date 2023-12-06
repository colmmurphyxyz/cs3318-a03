package xyz.colmmurphy;

import java.util.ArrayList;

public class ColourTable {

    private final int numColours;
    private ArrayList<int[]> palette;

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
        this.palette.add(rgb);
    }

    public void add(int red, int green, int blue) {
        add(new int[] {red, green, blue});
        char x = 255;
    }
}
