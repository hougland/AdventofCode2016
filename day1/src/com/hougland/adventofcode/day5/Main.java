package com.hougland.adventofcode.day5;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        final String realInput = "ojvtpuvg";
//        final String testInput = "abc";

        // Part 2
        final String[] solution = new String[8];
        int solutionLength = 0;

        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();

            int hexCounter = 0;
            while (solutionLength < 8) {
                String hex = "00001";
                while (!"00000".equals(hex.substring(0, 5))) {
                    byte[] toHash = (realInput + String.valueOf(hexCounter)).getBytes();
                    hex = (new HexBinaryAdapter()).marshal(m.digest(toHash));
                    hexCounter++;
                }

                String indexString = hex.substring(5, 6);
                String value = hex.substring(6, 7);
                if (indexString.matches("[0-7]")) {
                    int index = Integer.parseInt(indexString);

                    if (solution[index] == null) {
                        solution[index] = value;
                        solutionLength++;
                    }
                }
            }

        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }

        // convert solution array into string
        StringBuilder builder = new StringBuilder();
        for (String string : solution) {
            builder.append(string);
        }
        String solutionString = builder.toString();

        System.out.println(solutionString);
    }

    public static void part1(String input) {
        final List<String> five0s = new ArrayList<>();

        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();

            int hexCounter = 0;
            for (int i = 0; i < 8; i++) {
                String hex = "00001";
                while (!"00000".equals(hex.substring(0, 5))) {
                    byte[] toHash = (input + String.valueOf(hexCounter)).getBytes();
                    hex = (new HexBinaryAdapter()).marshal(m.digest(toHash));
                    hexCounter++;
                }

                five0s.add(hex);
            }
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }

        for (String hex : five0s) {
            System.out.print(hex.charAt(5));
        }
    }
}
