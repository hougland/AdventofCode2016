package com.hougland.adventofcode.day1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static java.lang.Math.abs;

public class Day1 {
    private static int xcoord = 0;
    private static int ycoord = 0;
    private static int directionFacing = 0;
    private static Coord currentCoord = new Coord(xcoord, ycoord);
    private static Set<Coord> pastCoords = new HashSet<>();
    private static Coord repeatCoord = null;

    public static void main(String[] args) {
        pastCoords.add(currentCoord);

        String[] instructions = parseCsv("src/com/hougland/adventofcode/day1/day1.csv");

        // loop through instructions to update x/y coordinates
        for (int i = 0; i < instructions.length; i++) {
            String direction = instructions[i].substring(0, 1);
            int numBlocks = Integer.parseInt(instructions[i].substring(1, instructions[i].length()));

            if ("R".equals(direction)) {
                if (directionFacing == 3) {
                    directionFacing = 0;
                } else {
                    directionFacing++;
                }
            } else if ("L".equals(direction)) {
                if (directionFacing == 0) {
                    directionFacing = 3;
                } else {
                    directionFacing--;
                }
            }

            switch (directionFacing) {
                case 0:
                    for (int j = 1; j <= numBlocks; j++) {
                        ycoord++;
                        checkPrevCoords();
                    }
                    break;
                case 1:
                    for (int j = 1; j <= numBlocks; j++) {
                        xcoord++;
                        checkPrevCoords();
                    }
                    break;
                case 2:
                    for (int j = 1; j <= numBlocks; j++) {
                        ycoord--;
                        checkPrevCoords();
                    }
                    break;
                case 3:
                    for (int j = 1; j <= numBlocks; j++) {
                        xcoord--;
                        checkPrevCoords();
                    }
                    break;
            }

            if (repeatCoord != null) {
                int totalDistance = abs(repeatCoord.getXcoord()) + abs(repeatCoord.getYcoord());
                System.out.println(String.format("Distance from repeated coordinate: [%d]", totalDistance));
            }
        }

        int totalDistance = abs(xcoord) + abs(ycoord);
        System.out.println("Total distance: " + totalDistance);
    }

    private static void checkPrevCoords() {
        currentCoord = new Coord(xcoord, ycoord);
        boolean contains = pastCoords.contains(currentCoord);
        if (contains) {
            repeatCoord = currentCoord;
        } else {
            pastCoords.add(currentCoord);
        }
    }

    private static String[] parseCsv(String filename) {
        String[] instructions = null;

        try {
            FileReader input = new FileReader(filename);
            BufferedReader bufRead = new BufferedReader(input);
            String myLine;

            while ( (myLine = bufRead.readLine()) != null)
            {
                instructions = myLine.split(",");
            }

            for (int i = 0; i < instructions.length; i++) {
                instructions[i] = instructions[i].replaceAll("\\s", "");
            }


        } catch (FileNotFoundException e) {
            System.out.print(e);
        } catch (IOException e) {
            System.out.print(e);

        }
        return instructions;
    }
}

