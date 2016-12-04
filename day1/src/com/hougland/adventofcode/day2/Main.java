package com.hougland.adventofcode.day2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> instructions = parseCsv("src/com/hougland/adventofcode/day2/day2.csv");

        BathroomCode bathroomCode = new BathroomCode(instructions);
        List<Integer> code1 = bathroomCode.getBathroomCode();
        System.out.println("Code1 for bathroom: " + code1);

        List<String> code2 = bathroomCode.getBathroomCode2();
        System.out.println("Code2 for bathroom: " + code2);
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
