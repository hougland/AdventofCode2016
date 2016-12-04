package com.hougland.adventofcode.day2;

public class Keypad {
    int[][] keypad;
    int currentRow;
    int currentColumn;

    public Keypad() {
        int[][] twoDarray = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        this.keypad = twoDarray;
        this.currentRow = 1;
        this.currentColumn = 1;
    }

    public int getCurrentRow() { return currentRow; }

    public int getCurrentColumn() { return currentColumn; }

    public int getNumAtCurrentPosition() { return keypad[currentRow][currentColumn]; }

    public void moveRowUp() {
        if (currentRow != 0) {
            currentRow--;
        }
    }

    public void moveRowDown() {
        if (currentRow != 2) {
            currentRow++;
        }

    }

    public void moveColumnLeft() {
        if (currentColumn != 0) {
            currentColumn--;
        }
    }

    public void moveColumnRight() {
        if (currentColumn != 2) {
            currentColumn++;
        }
    }
}
