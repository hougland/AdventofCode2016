package com.hougland.adventofcode.day13;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // end goal: (31,39)
        int[][] officeCoords = new int[35][45];
        int startX = 1;
        int startY = 1;
        int endX = 31;
        int endY = 39;

        for (int i = 0; i < officeCoords.length; i++) {
            for (int j = 0; j < officeCoords[0].length; j++) {
                boolean isOpen = isOpen(i, j);
                officeCoords[i][j] = isOpen ? 0 : 1;
                if (i == 31 && j == 39) {
                    officeCoords[i][j] = 8;
                }
            }
        }

        for (int i = 0; i < officeCoords.length; i++) {
            System.out.println(Arrays.toString(officeCoords[i]));
        }
    }

    public static boolean isOpen(int x, int y) {
        int officeDesignerFavNumber = 1352;

        // Find x*x + 3*x + 2*x*y + y + y*y. Add the office designer's favorite number (your puzzle input).
        int magicNum = magicFormula(x, y) + officeDesignerFavNumber;

        // Find the binary representation of that sum; count the number of bits that are 1.
        String binary = Integer.toBinaryString(magicNum);
        int binarySum = binarySum(binary);

        // If the number of bits that are 1 is even, it's an open space.
        // If the number of bits that are 1 is odd, it's a wall.
        return isBinarySumEven(binarySum);
    }

    public static int magicFormula(int x, int y) {
        return x*x + 3*x + 2*x*y + y + y*y;
    }

    public static int binarySum(String binary) {
        String[] binaryChars = binary.split("");
        int binaryNum = 0;
        for (int i = 0; i < binaryChars.length; i++) {
            binaryNum += Integer.parseInt(binaryChars[i]);
        }
        return binaryNum;
    }

    public static boolean isBinarySumEven(int sum) {
        return sum % 2 == 0;
    }
}
