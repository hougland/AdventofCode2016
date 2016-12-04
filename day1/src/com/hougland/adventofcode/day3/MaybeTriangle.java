package com.hougland.adventofcode.day3;

import java.util.ArrayList;
import java.util.List;

public class MaybeTriangle {
    public int side1;
    public int side2;
    public int side3;

    public MaybeTriangle(
            int side1,
            int side2,
            int side3
    ) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    public boolean isTriangle() {
        return (side1 + side2 > side3) && (side1 + side3 > side2) && (side2 + side3 > side1);
    }
}
