package xyz.colmmurphy;

import java.util.ArrayList;

public class ColourTable {

    private final int numColours;
    private ArrayList<byte[]> palette;

    public ColourTable(int numColours) {
        if (numColours <= 1 || numColours > 1024 || !isPowerOf2(numColours)) {
            throw new IllegalArgumentException("numColours must be a power of 2 greater than 1");
        } else {
            this.numColours = numColours;
        }
        this.palette = new ArrayList<byte[]>(numColours);
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

    public void add(byte[] rgb) {
        this.palette.add(rgb);
    }

    public void add(byte red, byte green, byte blue) {
        add(new byte[] {red, green, blue});
        char x = 255;
    }
}
