package xyz.colmmurphy;

public class ColourTable {

    private int numColours;
    public ColourTable(int numColours) {
        this.setNumColours(numColours);
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

    public void setNumColours(int numColours) {
        if (numColours <= 1 || !isPowerOf2(numColours)) {
            throw new IllegalArgumentException("numColours must be a power of 2 greater than 1");
        }
    }
}
