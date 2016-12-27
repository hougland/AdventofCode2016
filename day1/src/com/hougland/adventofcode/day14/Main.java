package com.hougland.adventofcode.day14;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Main {

    // too high: 30495
    // too low: 19694
    // too low: 17925

    public static void main(String[] args) {
        final String salt = "ahsbgdzn";
//        final String salt = "abc";
        int index = 0;
        int numKeys = 0;
        Set<String> keys = new HashSet<>();
        List<String> hashes = generateHexes(new ArrayList<>(), salt, 1000);

        while (numKeys < 64) {
            String currentHex = hashes.get(index);

            Optional<String> threesies = containsThreesies(currentHex);

            // if it contains 3sies, find if there's 5sies in the next 1000 hexes
            if (threesies.isPresent()) {
                // make sure there are enough generated hashes to iterate through
                if (hashes.size() < index + 1000) {
                    generateHexes(hashes, salt, 1000);
                }

                boolean foundFivesies = false;
                for (int i = index + 1; i < index + 1000; i++) {
                    String currentHex2 = hashes.get(i);

                    // if the current hex isn't already in the keys
                    // and we haven't already found fivesies
                    // and the current hex2 has fivsies
                    // then add it to the keys
                    if (!keys.contains(currentHex) && !foundFivesies && containsFivesies(currentHex2, threesies.get())) {
                        System.out.println(String.format("Found a key at index [%d], hex: [%s]", index, currentHex));
                        foundFivesies = true;
                        keys.add(currentHex);
                        numKeys++;
                    }
                }
            }
            index++;
        }

        System.out.println("numKeys: " + numKeys);
    }

    public static List<String> generateHexes(final List<String> hexes, String salt, int numHexes) {
        final MessageDigest m;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (final NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
        m.reset();

        for (int i = 0; i < numHexes; i++) {
            int currentIndex = hexes.size();
            byte[] toHash = (salt + String.valueOf(currentIndex)).getBytes();
            String preStretchedHex = (new HexBinaryAdapter()).marshal(m.digest(toHash)).toLowerCase();
            String stretchedHex = stretchHex(preStretchedHex);

            hexes.add(stretchedHex);
        }

        return hexes;
    }

    public static String stretchHex(final String hex) {
        final MessageDigest m;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (final NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
        m.reset();

        String stretchedHex = hex;
        for (int i = 0; i < 2016; i++) {
            stretchedHex = (new HexBinaryAdapter()).marshal(m.digest(stretchedHex.getBytes())).toLowerCase();
            m.reset();
        }

        return stretchedHex;
    }

    public static Optional<String> containsThreesies(final String hex) {
        int count = 0;
        for (int i = 0; i < hex.length() - 1; i++) {
            String currentChar = hex.substring(i, i + 1);
            if (currentChar.equals(hex.substring(i + 1, i + 2))) {
                count++;
            } else {
                count = 0;
            }

            if (count == 2) {
//                System.out.println(String.format("Found threesies char: [%s] in hex: [%s]", currentChar, hex));
                return Optional.of(currentChar);
            }
        }

        return Optional.empty();
    }

    public static boolean containsFivesies(final String hex, final String threesies) {
        int count = 0;
        for (int i = 0; i < hex.length() - 1; i++) {
            String currentChar = hex.substring(i, i + 1);
            if (threesies.equals(currentChar)) {
                if (threesies.equals(hex.substring(i + 1, i + 2))) {
                    count++;
                } else {
                    count = 0;
                }

                if (count == 4) {
                    return true;
                }
            }
        }

        return false;
    }
}
