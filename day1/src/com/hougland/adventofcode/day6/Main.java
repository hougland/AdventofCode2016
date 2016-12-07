package com.hougland.adventofcode.day6;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        List<String> firstLetters = new ArrayList<>();
        List<String> secondLetters = new ArrayList<>();
        List<String> thirdLetters = new ArrayList<>();
        List<String> fourthLetters = new ArrayList<>();
        List<String> fifthLetters = new ArrayList<>();
        List<String> sixthLetters = new ArrayList<>();
        List<String> seventhLetters = new ArrayList<>();
        List<String> eighthLetters = new ArrayList<>();

        try {
            FileReader input = new FileReader("src/com/hougland/adventofcode/day6/day6.csv");
            BufferedReader bufRead = new BufferedReader(input);
            String myLine;

            while ( (myLine = bufRead.readLine()) != null) {
                firstLetters.add(myLine.substring(0, 1));
                secondLetters.add(myLine.substring(1, 2));
                thirdLetters.add(myLine.substring(2, 3));
                fourthLetters.add(myLine.substring(3, 4));
                fifthLetters.add(myLine.substring(4, 5));
                sixthLetters.add(myLine.substring(5, 6));
                seventhLetters.add(myLine.substring(6, 7));
                eighthLetters.add(myLine.substring(7, 8));
            }

        } catch (FileNotFoundException e) {
            System.out.print(e);
        } catch (IOException e) {
            System.out.print(e);
        }

        String firstLetter = mostCommon(firstLetters);
        String secondLetter = mostCommon(secondLetters);
        String thirdLetter = mostCommon(thirdLetters);
        String fourthLetter = mostCommon(fourthLetters);
        String fifthLetter = mostCommon(fifthLetters);
        String sixthLetter = mostCommon(sixthLetters);
        String seventhLetter = mostCommon(seventhLetters);
        String eighthLetter = mostCommon(eighthLetters);

        System.out.println(firstLetter + secondLetter + thirdLetter + fourthLetter + fifthLetter + sixthLetter +
        seventhLetter + eighthLetter);

        String firstLetter2 = leastCommon(firstLetters);
        String secondLetter2 = leastCommon(secondLetters);
        String thirdLetter2 = leastCommon(thirdLetters);
        String fourthLetter2 = leastCommon(fourthLetters);
        String fifthLetter2 = leastCommon(fifthLetters);
        String sixthLetter2 = leastCommon(sixthLetters);
        String seventhLetter2 = leastCommon(seventhLetters);
        String eighthLetter2 = leastCommon(eighthLetters);

        System.out.println(firstLetter2 + secondLetter2 + thirdLetter2 + fourthLetter2 + fifthLetter2 + sixthLetter2 +
                seventhLetter2 + eighthLetter2);
    }

    public static String mostCommon(List<String> letters) {
        Map<String, Integer> map = new HashMap<>();

        for (String letter : letters) {
            Integer val = map.get(letter);
            map.put(letter, val == null ? 1 : val + 1);
        }

        Map.Entry<String, Integer> max = null;

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (max == null || entry.getValue() > max.getValue())
                max = entry;
        }

        return max.getKey();
    }

    public static String leastCommon(List<String> letters) {
        Map<String, Integer> map = new HashMap<>();

        for (String letter : letters) {
            Integer val = map.get(letter);
            map.put(letter, val == null ? 1 : val + 1);
        }

        Map.Entry<String, Integer> max = null;

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (max == null || entry.getValue() < max.getValue())
                max = entry;
        }

        return max.getKey();
    }
}
