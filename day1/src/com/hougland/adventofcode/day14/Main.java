package com.hougland.adventofcode.day14;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        final String salt = "ahsbgdzn";
//        final String salt = "abc";
        int index = 0;
        final MessageDigest m;
        int numKeys = 0;
        Set<String> keys = new HashSet<>();

        try {
            m = MessageDigest.getInstance("MD5");
        } catch (final NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }

        m.reset();

        while (numKeys < 64) {
            byte[] toHash = (salt + String.valueOf(index)).getBytes();
            String hex = (new HexBinaryAdapter()).marshal(m.digest(toHash));

            Optional<String> threesies = containsThreesies(hex);

            if (threesies.isPresent()) {
                int index2 = index + 1;
                boolean foundFivesies = false;
                for (int i = 0; i < 1000; i++) {
                    byte[] toHash2 = (salt + String.valueOf(index2)).getBytes();
                    String hex2 = (new HexBinaryAdapter()).marshal(m.digest(toHash2));

                    if (!keys.contains(hex)) {

                        if (!foundFivesies && containsFivesies(hex2, threesies.get())) {
                            System.out.println(String.format("Found a key at index [%d], index2 [%d], hex: [%s]", index, index2, hex));
                            keys.add(hex);
                            numKeys++;
                            foundFivesies = true;
                        }
                    }
                    index2++;
                }
            }
            index++;
        }

        System.out.println("numKeys: " + numKeys);
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
