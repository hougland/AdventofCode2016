package com.hougland.adventofcode.day2;

public class Keypad {
    int[][] keypad1;
    String[][] keypad2;
    int currentRow;
    int currentColumn;
    int currentRow2;
    int currentColumn2;

    public Keypad() {
        int[][] twoDArray1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        this.keypad1 = twoDArray1;
        String[][] twoDArray2 = {
                { null, null, "1", null, null },
                { null, "2", "3", "4", null },
                { "5", "6", "7", "8", "9" },
                { null, "A", "B", "C", null },
                { null, null, "D", null, null } };
        this.keypad2 = twoDArray2;
        this.currentRow = 1;
        this.currentColumn = 1;
        this.currentRow2 = 2;
        this.currentColumn2 = 0;
    }

    public int getCurrentRow() { return currentRow; }

    public int getCurrentColumn() { return currentColumn; }

    public int getNumAtCurrentPosition() { return keypad1[currentRow][currentColumn]; }

    public String getValueAtCurrentPosition2() { return keypad2[currentRow2][currentColumn2]; }

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

    public void moveRowUp2() {
        if (currentRow2 == 0) {
            return;
        } else if (currentRow2 == 1 && currentColumn2 == 2) {
            currentRow2--;
        } else if (currentRow2 == 2 && (currentColumn2 == 1 || currentColumn2 == 2 || currentColumn2 == 3)) {
            currentRow2--;
        } else if (currentRow2 == 3 || currentRow2 == 4) {
            currentRow2--;
        }
    }

    public void moveRowDown2() {
        if (currentRow2 == 0 || currentRow2 == 1) {
            currentRow2++;
        } else if (currentRow2 == 2 && (currentColumn2 == 1 || currentColumn2 == 2 || currentColumn2 == 3)) {
            currentRow2++;
        } else if (currentRow2 == 3 && currentColumn2 == 2) {
            currentRow2++;
        }
    }

    public void moveColumnLeft2() {
        if (currentRow2 == 0) {
            return;
        } else if (currentRow2 == 1 && currentColumn2 != 1) {
            currentColumn2--;
        } else if (currentRow2 == 2 && currentColumn2 != 0) {
            currentColumn2--;
        } else if (currentRow2 == 3 && currentColumn2 != 1) {
            currentColumn2--;
        }
    }

    public void moveColumnRight2() {
        if (currentRow2 == 0) {
            return;
        } else if (currentRow2 == 1 && currentColumn2 != 3) {
            currentColumn2++;
        } else if (currentRow2 == 2 && currentColumn2 != 4) {
            currentColumn2++;
        } else if (currentRow2 == 3 && currentColumn2 != 3) {
            currentColumn2++;
        }
    }
}
