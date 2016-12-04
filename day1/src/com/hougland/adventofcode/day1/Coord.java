package com.hougland.adventofcode.day1;

public class Coord {
    private final int xcoord;
    private final int ycoord;

    public Coord(int xcoord, int ycoord) {
        this.xcoord = xcoord;
        this.ycoord = ycoord;
    }

    public int getXcoord() { return xcoord; }

    public int getYcoord() { return ycoord; }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 3 * hash + this.xcoord;
        hash = 3 * hash + this.ycoord;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Coord))
            return false;
        if (obj == this)
            return true;

        Coord coord = (Coord) obj;

        if ((this.xcoord == coord.getXcoord()) && (this.ycoord == coord.getYcoord())) {
            return true;
        } else {
            return false;
        }
    }
}
