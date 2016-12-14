package com.hougland.adventofcode.day12;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.hougland.adventofcode.day12.Instruction.Type.*;

public class Main {

    public static void main(String[] args) {
        List<String> instructionStrings = parseCSV("src/com/hougland/adventofcode/day12/day12.csv");
        List<Instruction> instructions = instructionStrings
                .stream()
                .map(Instruction::new)
                .collect(Collectors.toList());

        Map<String, Integer> registerToIndex = new HashMap<>();
        registerToIndex.put("a", 0);
        registerToIndex.put("b", 1);
        registerToIndex.put("c", 2);
        registerToIndex.put("d", 3);

        Integer[] registers = {0, 0, 1, 0};

        int instructionsIndex = 0;

        while (instructionsIndex < instructions.size()) {
            Instruction instruction = instructions.get(instructionsIndex);

            if (instruction.type == INC) {
                registers = executeInc(instruction, registers);
                instructionsIndex++;
            } else if (instruction.type == DEC) {
                registers = executeDec(instruction, registers);
                instructionsIndex++;
            } else if (instruction.type == CPY) {
                registers = executeCpy(instruction, registers);
                instructionsIndex++;
            } else if (instruction.type == JNZ) {

                int checkIfZero;
                if (instruction.x.matches("[a-d]")) {
                    int register = registerToIndex.get(instruction.x);
                    checkIfZero = registers[register];
                } else {
                    checkIfZero = Integer.parseInt(instruction.x);
                }

                if (checkIfZero == 0) {
                    instructionsIndex++;
                } else {
                    instructionsIndex += Integer.parseInt(instruction.y);
                }

            }
        }

        System.out.println("Value of register a: " + registers[0]);
        System.out.println("Value of register b: " + registers[0]);
        System.out.println("Value of register c: " + registers[0]);
        System.out.println("Value of register d: " + registers[0]);
    }


    public static Integer[] executeInc(Instruction instruction, Integer[] registers) {
        Map<String, Integer> registerToIndex = new HashMap<>();
        registerToIndex.put("a", 0);
        registerToIndex.put("b", 1);
        registerToIndex.put("c", 2);
        registerToIndex.put("d", 3);

        int registerIndex = registerToIndex.get(instruction.x);

        registers[registerIndex]++;

        return registers;
    }

    public static Integer[] executeDec(Instruction instruction, Integer[] registers) {
        Map<String, Integer> registerToIndex = new HashMap<>();
        registerToIndex.put("a", 0);
        registerToIndex.put("b", 1);
        registerToIndex.put("c", 2);
        registerToIndex.put("d", 3);

        int registerIndex = registerToIndex.get(instruction.x);

        registers[registerIndex]--;

        return registers;
    }

    public static Integer[] executeCpy(Instruction instruction, Integer[] registers) {
        Map<String, Integer> registerToIndex = new HashMap<>();
        registerToIndex.put("a", 0);
        registerToIndex.put("b", 1);
        registerToIndex.put("c", 2);
        registerToIndex.put("d", 3);

        int registerIndex = registerToIndex.get(instruction.y);

        int value;
        if (instruction.x.matches("[a-d]")) {
            int registerValue = registerToIndex.get(instruction.x);
            value = registers[registerValue];
        } else {
            value = Integer.parseInt(instruction.x);
        }

        registers[registerIndex] = value;

        return registers;
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
