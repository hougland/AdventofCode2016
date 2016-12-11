package com.hougland.adventofcode.day9;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        String[] instructionCharacters = parseCSV("src/com/hougland/adventofcode/day9/day9.csv");

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
