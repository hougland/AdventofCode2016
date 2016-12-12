package com.hougland.adventofcode.day9;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {

    // 7226 too low

    public static void main(String[] args) {
        String[] instructionCharacters = parseCSV("src/com/hougland/adventofcode/day9/day9test.csv");

//        decompress1(instructionCharacters);

        decompress2(instructionCharacters);
    }

    public static void decompress2(String[] instructionCharacters) {
        int sum = 0;

        for (int i = 0; i < instructionCharacters.length; i++) {
            int markerEndsOnIndex = 0;

            Marker currentMarker = null;

            if ("(".equals(instructionCharacters[i])) {
                for (int j = i + 4; j < i + 8; j++) {
                    if (")".equals(instructionCharacters[j])) {
                        currentMarker = new Marker(Arrays.copyOfRange(instructionCharacters, i + 1, j));
                        markerEndsOnIndex = j;
                        // marker starts on i, ends on j
                        // so pass the full chunk of instructions that are within that block to "decompress" method
                        // decompress method will recursively call itself until there aren't any more markers in it
                    }
                }

                String[] newInstructions = Arrays.copyOfRange(instructionCharacters, markerEndsOnIndex + 1, markerEndsOnIndex + 1 + currentMarker.howManyChars);

                sum += decompress(newInstructions, currentMarker);


                // increase i to skip over that whole subsection that's been summed up already
                i += (currentMarker.length + currentMarker.howManyChars + 1);

            } else {
                sum++;
            }
        }

        System.out.println("Part 2 string length: " + sum);
    }

    public static int decompress(String[] instructionsSlice, Marker marker) {
        System.out.println("an iteration of decompress beginning");
        System.out.println("InstructionSlice: " + Arrays.stream(instructionsSlice).collect(Collectors.joining("")));
        System.out.println("Marker.howmanyChars: " + marker.howManyChars + " Marker.howmanyTimes: " + marker.howManyTimes);
//        String decompressed = "";
//        String toDecompress = Arrays.stream(instructionsSlice).collect(Collectors.joining(""));
        int sum = 0;

        if (hasAnotherMarker(instructionsSlice)) {
            // follow the instructions from the current marker by multiplying out the string
            for (int k = 0; k < marker.howManyTimes; k++) {
                sum += instructionsSlice.length;
            }


            // get the next marker
            Marker nextMarker = null;
            int markerBeginsOnIndex = -1;
            int markerEndsOnIndex = -1;

            for (int i = 0; i < instructionsSlice.length; i++) {
                if ("(".equals(instructionsSlice[i])) {
                    for (int j = i + 4; j < i + 8; j++) {
                        if (nextMarker == null) {
                            if (")".equals(instructionsSlice[j])) {
                                nextMarker = new Marker(Arrays.copyOfRange(instructionsSlice, i + 1, j));
                                markerBeginsOnIndex = i;
                                markerEndsOnIndex = j;
                            }
                        }

                    }
                }
            }

            // pass next marker and following instructions to decompress
            for (int i = 0; i < markerBeginsOnIndex; i++) { // in case the first character isn't "("
                sum++;
            }

            String[] nextInstructions = Arrays.copyOfRange(instructionsSlice, markerEndsOnIndex + 1, instructionsSlice.length);
            sum += decompress(nextInstructions, nextMarker);

        } else {
            for (int k = 0; k < marker.howManyTimes; k++) {
                sum += instructionsSlice.length;
            }
        }

        return sum;
    }

    public static boolean hasAnotherMarker(String[] instructionsSlice) {
        for (String instruction : instructionsSlice) {
            if ("(".equals(instruction)) {
                return true;
            }
        }

        return false;
    }

    public static void decompress1(String[] instructionCharacters) {
        String decompressed = "";

        for (int i = 0; i < instructionCharacters.length; i++) {
            Marker currentMarker = null;
            int markerEndsOnIndex = 0;

            if ("(".equals(instructionCharacters[i])) {
                // create the marker
                for (int j = i + 4; j < i + 8; j++) {
                    if (")".equals(instructionCharacters[j])) {
                        currentMarker = new Marker(Arrays.copyOfRange(instructionCharacters, i + 1, j));
                        markerEndsOnIndex = j;
                    }
                }

                // follow marker instructions
                String[] toDecompress = Arrays.copyOfRange(instructionCharacters, markerEndsOnIndex + 1, markerEndsOnIndex + 1 + currentMarker.howManyChars);
                String addToDecompressedString = Arrays.stream(toDecompress).collect(Collectors.joining(""));

                for (int k = 0; k < currentMarker.howManyTimes; k++) {
                    decompressed += addToDecompressedString;
                }

                // don't iterate over anything that's already been decompressed
                i += (currentMarker.length + currentMarker.howManyChars + 1);

            } else {
                decompressed += instructionCharacters[i];
            }
        }

        System.out.println(decompressed);
        System.out.println("Decompressed string length: " + decompressed.length());
    }

    public static String[] parseCSV(String filepath) {
        String[] instructionCharacters = null;

        try {
            FileReader input = new FileReader(filepath);
            BufferedReader bufRead = new BufferedReader(input);
            String myLine;

            while ( (myLine = bufRead.readLine()) != null) {
                instructionCharacters = myLine.split("");
            }

        } catch (FileNotFoundException e) {
            System.out.print(e);
        } catch (IOException e) {
            System.out.print(e);
        }

        return instructionCharacters;
    }
}
