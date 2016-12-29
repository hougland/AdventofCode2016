package com.hougland.adventofcode.day15;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Part of the sculpture is even interactive! When a button is pressed, a capsule is dropped and tries to fall through slots in a set of rotating discs to finally go through a little hole at the bottom and come out of the sculpture. If any of the slots aren't aligned with the capsule as it passes, the capsule bounces off the disc and soars away. You feel compelled to get one of those capsules.
//
// The discs pause their motion each second and come in different sizes; they seem to each have a fixed number of positions at which they stop. You decide to call the position with the slot 0, and count up for each position it reaches next.
//
// Furthermore, the discs are spaced out so that after you push the button, one second elapses before the first disc is reached, and one second elapses as the capsule passes from one disc to the one below it. So, if you push the button at time=100, then the capsule reaches the top disc at time=101, the second disc at time=102, the third disc at time=103, and so on.
//
// The button will only drop a capsule at an integer time - no fractional seconds allowed.
//
// For example, at time=0, suppose you see the following arrangement:
//
// Disc #1 has 5 positions; at time=0, it is at position 4.
// Disc #2 has 2 positions; at time=0, it is at position 1.
// If you press the button exactly at time=0, the capsule would start to fall; it would reach the first disc at time=1. Since the first disc was at position 4 at time=0, by time=1 it has ticked one position forward. As a five-position disc, the next position is 0, and the capsule falls through the slot.
//
// Then, at time=2, the capsule reaches the second disc. The second disc has ticked forward two positions at this point: it started at position 1, then continued to position 0, and finally ended up at position 1 again. Because there's only a slot at position 0, the capsule bounces away.
//
// If, however, you wait until time=5 to push the button, then when the capsule reaches each disc, the first disc will have ticked forward 5+1 = 6 times (to position 0), and the second disc will have ticked forward 5+2 = 7 times (also to position 0). In this case, the capsule would fall through the discs and come out of the machine.
//
// However, your situation has more than two discs; you've noted their positions in your puzzle input. What is the first time you can press the button to get a capsule?

public class Main {

    // possible answer: 203660

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
