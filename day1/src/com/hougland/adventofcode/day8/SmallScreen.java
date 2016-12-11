package com.hougland.adventofcode.day8;

import java.util.Arrays;

public class SmallScreen {
    int[][] screen;

    public SmallScreen(int y, int x) {
        this.screen = new int[y][x];
    }

    public void rotateRow(int row, int numPixels) {
        for (int i = 0; i < numPixels; i++) {
            int currentValue = screen[row][0];
            int nextValue;

            for (int j = 0; j < screen[row].length; j++) {
                if (j < screen[row].length - 1) {
                    nextValue = screen[row][j + 1];
                    screen[row][j + 1] = currentValue;
                    currentValue = nextValue;

                } else {
                    screen[row][0] = currentValue;
                }
            }
        }
    }

    public void rotateColumn(int column, int numPixels) {
        for (int i = 0; i < numPixels; i++) {
            int currentValue = screen[0][column];
            int nextValue;

            for (int j = 0; j < screen.length; j++) { // j represents which column index we're on
                if (j < screen.length - 1) {
                    nextValue = screen[j + 1][column];
                    screen[j + 1][column] = currentValue;
                    currentValue = nextValue;

                } else {
                    screen[0][column] = currentValue;
                }
            }
        }

    }

    public void turnOnRect(int y, int x) {
        int rowIndex = y - 1;
        int columnIndex = x - 1;

        for (int i = 0; i <= rowIndex; i++) {
            for (int j = 0; j <= columnIndex; j++) {
                screen[j][i] = 1;
            }
        }
    }

    public int numPixelsTurnedOn() {
        return Arrays.stream(screen).flatMapToInt(Arrays::stream).sum();
    }
}
