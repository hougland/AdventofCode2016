package com.hougland.adventofcode.day12;

import static com.hougland.adventofcode.day12.Instruction.Type.*;

public class Instruction {
    final String instructionText;
    Type type = null;
    final String x;
    String y = null;

    public Instruction(String instruction) {
        this.instructionText = instruction;

        String[] instructions = instruction.split(" ");
        if ("cpy".equals(instructions[0])) {
            this.type = CPY;
            this.y = instructions[2];
        } else if ("inc".equals(instructions[0])) {
            this.type = INC;
        } else if ("dec".equals(instructions[0])) {
            this.type = DEC;
        } else if ("jnz".equals(instructions[0])) {
            this.type = JNZ;
            this.y = instructions[2];
        } else {
            System.out.println("Found type that doesn't exist");
        }

        this.x = instructions[1];
    }

    public enum Type {
        CPY, INC, DEC, JNZ
    }
}
