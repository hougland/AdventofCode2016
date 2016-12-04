package com.hougland.adventofcode.day2;

import java.util.ArrayList;
import java.util.List;

public class BathroomCode {
    List<String> codedInstructions;
    Keypad keypad1;
    Keypad keypad2;

    public BathroomCode(
            List<String> codedInstructions
    ) {
        this.codedInstructions = codedInstructions;
        this.keypad1 = new Keypad();
        this.keypad2 = new Keypad();
    }

    public List<Integer> getBathroomCode() {
        List<Integer> codes = new ArrayList<>();

        for (String instruction : codedInstructions) {
            for (int i = 0; i < instruction.length(); i++) {
                String direction = instruction.substring(i, i + 1);

                // logic to update currentPosition
                switch (direction) {
                    case "U":
                        keypad1.moveRowUp();
                        break;
                    case "D":
                        keypad1.moveRowDown();
                        break;
                    case "L":
                        keypad1.moveColumnLeft();
                        break;
                    case "R":
                        keypad1.moveColumnRight();
                        break;
                }
            }

            // add final currentPosition to codes
            codes.add(keypad1.getNumAtCurrentPosition());
        }

        return codes;
    }

    public List<String> getBathroomCode2() {
        List<String> codes = new ArrayList<>();

        for (String instruction : codedInstructions) {
            for (int i = 0; i < instruction.length(); i++) {
                String direction = instruction.substring(i, i + 1);

                // logic to update currentPosition
                switch (direction) {
                    case "U":
                        keypad2.moveRowUp2();
                        break;
                    case "D":
                        keypad2.moveRowDown2();
                        break;
                    case "L":
                        keypad2.moveColumnLeft2();
                        break;
                    case "R":
                        keypad2.moveColumnRight2();
                        break;
                }
            }

            // add final currentPosition to codes
            codes.add(keypad2.getValueAtCurrentPosition2());
        }

        return codes;
    }
}
