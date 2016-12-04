package com.hougland.adventofcode.day2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// too low: 54855

public class Main {

    public static void main(String[] args) {
        List<String> instructions = parseCsv("src/com/hougland/adventofcode/day2/day2.csv");

        BathroomCode bathroomCode = new BathroomCode(instructions);
        List<Integer> code = bathroomCode.getBathroomCode();
        System.out.println("Code for bathroom: " + code);
    }

    private static List<String> parseCsv(String filename) {
        List<String> instructions = new ArrayList<>();

        try {
            FileReader input = new FileReader(filename);
            BufferedReader bufRead = new BufferedReader(input);
            String myLine;

            while ( (myLine = bufRead.readLine()) != null)
            {
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
