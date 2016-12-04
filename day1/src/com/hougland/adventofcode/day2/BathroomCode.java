package com.hougland.adventofcode.day2;

import java.util.ArrayList;
import java.util.List;

public class BathroomCode {
    List<String> codedInstructions;
    Keypad keypad;

    public BathroomCode(
            List<String> codedInstructions
    ) {
        this.codedInstructions = codedInstructions;
        this.keypad = new Keypad();
    }

    public List<Integer> getBathroomCode() {
        List<Integer> codes = new ArrayList<>();

        for (String instruction : codedInstructions) {
            for (int i = 0; i < instruction.length(); i++) {
                String direction = instruction.substring(i, i + 1);

                // logic to update currentPosition
                switch (direction) {
                    case "U":
                        keypad.moveRowUp();
                        break;
                    case "D":
                        keypad.moveRowDown();
                        break;
                    case "L":
                        keypad.moveColumnLeft();
                        break;
                    case "R":
                        keypad.moveColumnRight();
                        break;
                }
            }

            // add final currentPosition to codes
            codes.add(keypad.getNumAtCurrentPosition());
        }


        return codes;
    }

}
