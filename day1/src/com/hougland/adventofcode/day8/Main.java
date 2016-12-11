package com.hougland.adventofcode.day8;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> instructions = parseCSV("src/com/hougland/adventofcode/day8/day8.csv");

        SmallScreen smallScreen = followInstructions(instructions);

        System.out.println("Number of pixels turned on: " + smallScreen.numPixelsTurnedOn());
    }

    public static SmallScreen followInstructions(List<String> instructions) {
        SmallScreen smallScreen = new SmallScreen(6, 50);
//        SmallScreen smallScreen = new SmallScreen(3, 7); // test

        for (String instruction : instructions) {
            System.out.println(instruction);
            if ("rect".equals(instruction.substring(0, 4))) {
                String xString = "";
                int xEndingIndex = 0;
                for (int i = 5; i < instruction.length() - 2; i++) {
                    xString += instruction.substring(i, i + 1);
                    if ("x".equals(instruction.substring(i + 1, i + 2))) {
                        xEndingIndex = i;
                    }
                }
                int x = Integer.parseInt(xString);

                int y = Integer.parseInt(instruction.substring(xEndingIndex + 2, xEndingIndex + 3));

                smallScreen.turnOnRect(x, y);

            } else if ("rotate row".equals(instruction.substring(0, 10))) {
                int row = Integer.parseInt(instruction.substring(13, 14));

                String numPixelsString = "";
                for (int i = 18; i < instruction.length(); i++) {
                    numPixelsString += instruction.substring(i, i + 1);
                }
                int numPixels = Integer.parseInt(numPixelsString);

                smallScreen.rotateRow(row, numPixels);

            } else if ("rotate column".equals(instruction.substring(0, 13))) {
                String columnString = "";
                int columnEndingIndex = 0;
                for (int i = 16; i < instruction.length(); i++) {
                    columnString += instruction.substring(i, i + 1);
                    if (" ".equals(instruction.substring(i + 1, i + 2))) {
                        columnEndingIndex = i;
                        break;
                    }
                }

                int column = Integer.parseInt(columnString);

                int numPixels = Integer.parseInt(instruction.substring(columnEndingIndex + 5, columnEndingIndex + 6));

                smallScreen.rotateColumn(column, numPixels);
            }

            System.out.println(String.format("%d | %d | %d", smallScreen.screen[0][0], smallScreen.screen[0][1], smallScreen.screen[0][2]));
            System.out.println(String.format("%d | %d | %d",smallScreen.screen[1][0], smallScreen.screen[1][1], smallScreen.screen[1][2]));
            System.out.println(String.format("%d | %d | %d", smallScreen.screen[2][0], smallScreen.screen[2][1], smallScreen.screen[2][2]));
        }


        return smallScreen;
    }

    public static List<String> parseCSV(String filepath) {
        List<String> instructions = new ArrayList<>();

        try {
            FileReader input = new FileReader(filepath);
            BufferedReader bufRead = new BufferedReader(input);
            String myLine;

            while ( (myLine = bufRead.readLine()) != null) {
                instructions.add(myLine);
            }

        } catch (FileNotFoundException e) {
            System.out.print(e);
        } catch (IOException e) {
            System.out.print(e);
        }

        return instructions;
    }
}
