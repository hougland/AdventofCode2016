package com.hougland.adventofcode.day15;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Disk> disks = parseCSV("src/com/hougland/adventofcode/day15/day15.csv");

        int capsulePosition = -1;
        int timeDelay = 170068; // 56695; // not less than 132277

        while (capsulePosition < disks.size() - 1) {
            if (capsulePosition == -1) {
                for (int i = 0; i <= timeDelay; i++) {
                    disks = tickDisks(disks);
                }
            } else {
                disks = tickDisks(disks);
            }

            capsulePosition++;
            boolean canPass = disks.get(capsulePosition).canPassThrough();

            if (!canPass) {
                // reset and increment timeDelay
                disks = resetDisks(disks);
                timeDelay += 13;
                capsulePosition = -1;
            } else {
                if (capsulePosition > 1) {
                    System.out.println(String.format("Can pass: capsule position [%d], time delay [%d]", capsulePosition, timeDelay));
                }
            }
        }

        System.out.println("Time delay: " + timeDelay);
    }

    public static List<Disk> resetDisks(List<Disk> disks) {
        for (Disk disk : disks) {
            disk.reset();
        }
        return disks;
    }

    public static List<Disk> tickDisks(List<Disk> disks) {
        for (Disk disk : disks) {
            disk.tick();
        }
        return disks;
    }

    public static List<Disk> parseCSV(String filepath) {
        List<Disk> instructions = new ArrayList<>();

        try {
            FileReader input = new FileReader(filepath);
            BufferedReader bufRead = new BufferedReader(input);
            String myLine;

            while ( (myLine = bufRead.readLine()) != null) {
                instructions.add(getDisk(myLine));
            }

        } catch (FileNotFoundException e) {
            System.out.print(e);
        } catch (IOException e) {
            System.out.print(e);
        }

        return instructions;
    }

    public static Disk getDisk(String line) {
        String[] words = line.split(" ");

        int diskNum = Integer.parseInt(words[1].split("")[1]);
        int numPositions = Integer.parseInt(words[3]);
        int position = Integer.parseInt(words[11].replace(".", ""));

        return new Disk(diskNum, numPositions, position);
    }
}
