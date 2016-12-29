package com.hougland.adventofcode.day15;

public class Disk {
    final int num;
    final int numPositions;
    int position;
    int originalPosition;

    public Disk(final int num, final int numPositions, final int position) {
        this.num = num;
        this.numPositions = numPositions;
        this.position = position;
        this.originalPosition = position;
    }

    public void tick() {
        if (position < numPositions - 1) {
            position++;
        } else {
            position = 0;
        }
    }

    public void reset() {
        position = originalPosition;
    }

    public boolean canPassThrough() {
        return position == 0;
    }
}
