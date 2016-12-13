package com.hougland.adventofcode.day10;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<String> instructions = parseCSV("src/com/hougland/adventofcode/day10/day10.csv");

        // set initial state
        Set<Integer> botNumbers = new HashSet<>();
        Set<Bot> bots = new HashSet<>();
        List<String> newInstructions = new ArrayList<>();

        for (String instruction : instructions) {
            String[] instructionArray = instruction.split(" ");

            if ("value".equals(instructionArray[0])) {
                int botNum = Integer.parseInt(instructionArray[5]);
                int value = Integer.parseInt(instructionArray[1]);

                if (botNumbers.contains(botNum)) {
                    for (Bot bot : bots) {
                        if (bot.getBotNumber() == botNum) {
                            bot.addNumber(value);
                        }
                    }
                } else {
                    Bot bot = new Bot(botNum, value);
                    bots.add(bot);
                    botNumbers.add(botNum);
                }

            } else {
                newInstructions.add(instruction);
            }
        }

        // put this in a while loop
        while (newInstructions.size() > 0) {
            final Bot activeBot = bots.stream().filter(Bot::hasTwoCards).findFirst().get();

            String[] instruction = newInstructions
                    .stream()
                    .map(i -> i.split(" "))
                    .filter(i -> Integer.parseInt(i[1]) == activeBot.botNumber)
                    .findFirst()
                    .get();

            followInstructions(instruction, activeBot, bots, botNumbers);

            String instructionString = Arrays.toString(instruction).replace("[", "").replace("]", "").replace(",", "");
            newInstructions.remove(instructionString);
        }
    }

    public static void followInstructions(String[] instructions, Bot bot, Set<Bot> bots, Set<Integer> botNumbers) {
        // take care of low number
        if ("output".equals(instructions[5])) {
            bot.giveLow();
        } else {
            int lowToBotNum = Integer.parseInt(instructions[6]);
            Optional<Bot> lowToBotOptional = bots.stream().filter(b -> b.getBotNumber() == lowToBotNum).findFirst();

            Bot lowToBot;
            if (lowToBotOptional.isPresent()) {
                lowToBot = lowToBotOptional.get();
            } else {
                botNumbers.add(lowToBotNum);
                lowToBot = new Bot(lowToBotNum);
                bots.add(lowToBot);
            }

            lowToBot.addNumber(bot.low);
        }

        // take care of high number
        if ("output".equals(instructions[10])) {
            bot.giveHigh();
        } else {
            int highToBotNum = Integer.parseInt(instructions[11]);
            Optional<Bot> highToBotOptional = bots.stream().filter(b -> b.getBotNumber() == highToBotNum).findFirst();

            Bot highToBot;
            if (highToBotOptional.isPresent()) {
                highToBot = highToBotOptional.get();
            } else {
                botNumbers.add(highToBotNum);
                highToBot = new Bot(highToBotNum);
                bots.add(highToBot);
            }

            highToBot.addNumber(bot.high);

            bot.giveAll();
        }
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
