package com.hougland.adventofcode.day4;

import java.util.*;

public class MaybeRoom {
    private String roomName;
    private String encryptedName;
    private int sectorId;
    private String checksum;

    private final String[] alphabetArray = { null, "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o",
            "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    private final List<String> alphabetList;

    public MaybeRoom(
            String roomName
    ) {
        alphabetList = Arrays.asList(alphabetArray);

        // roomName: aaaaa-bbb-z-y-x-123[abxyz]
        this.roomName = roomName;

        // encryptedName: aaaaabbbzyx
        int firstDigitIndex = 0;
        for (int i = 0; i < roomName.length(); i++) {
            char letter = roomName.charAt(i);

            if (Character.isDigit(letter)) {
                firstDigitIndex = i - 2;
            }
        }
        this.encryptedName = roomName.substring(0, firstDigitIndex).replace("-", "");

        // sectorId: 123
        String sectorId = roomName.substring(firstDigitIndex, firstDigitIndex + 3);
        this.sectorId = Integer.parseInt(sectorId);

        // checksum: abxyz
        this.checksum = roomName.substring(firstDigitIndex + 4, firstDigitIndex + 9);
    }

    public int getSectorId() {
        return this.sectorId;
    }

    public String getEncryptedName() {
        return this.encryptedName;
    }

    public String getChecksum() {
        return this.checksum;
    }

    public String getDecryptedName() {
        // iterate through each letter
        // look up in a map what that letter's numerical value is (i -> 8)
        // add sectorId to that letter's numerical value (8 + 343 = 351)
        // divide that number by 26, and the modulo + the letter's numerical value is the decrypted letter's numerical value (343 % 26 = 5 + 8) -> n
        // if new index is greater than 26, get its % again
        // look up the new number's alphabetical value

        String decryptedName = "";

        for (int i = 0; i < encryptedName.length(); i++) {
            String letter = encryptedName.substring(i, i + 1);
            int alphabetIndex = alphabetList.indexOf(letter);

            int largeShiftedAlphabetIndex = alphabetIndex + sectorId;
            int adjustedShiftedAlphabetIndex = largeShiftedAlphabetIndex % 26;

            if (adjustedShiftedAlphabetIndex > 26) {
                adjustedShiftedAlphabetIndex = largeShiftedAlphabetIndex % 26;
            }

            decryptedName += alphabetList.get(adjustedShiftedAlphabetIndex);
        }

        return decryptedName;
    }

    public boolean isRealRoom() {
        Map<Integer, String> frequencyMap = buildFrequencyMap();

        String realChecksum = buildRealChecksum(frequencyMap);

        return this.checksum.equals(realChecksum);
    }

    private Map<Integer, String> buildFrequencyMap() {
        // ex: aaaaabbbzyx ---> [(5 -> a), (4 -> b), (1 -> zyx)]
        Map<Integer, String> frequencyMap = new HashMap<>();
        frequencyMap.put(8, "");
        frequencyMap.put(7, "");
        frequencyMap.put(6, "");
        frequencyMap.put(5, "");
        frequencyMap.put(4, "");
        frequencyMap.put(3, "");
        frequencyMap.put(2, "");
        frequencyMap.put(1, "");

        for (int i = 0; i < encryptedName.length(); i++) {
            String letter = encryptedName.substring(i, i + 1);
            int numOccurances = encryptedName.length() - encryptedName.replace(letter, "").length();
            if (numOccurances > 0) {
                if (!frequencyMap.get(numOccurances).contains(letter)) {
                    frequencyMap.put(numOccurances, (frequencyMap.get(numOccurances) + letter));
                }
            }
        }

        return frequencyMap;
    }

    private String buildRealChecksum(
            Map<Integer, String> frequencyMap
    ) {
        String realChecksum = "";

        for (int i = 8; i > 0; i--) {
            String letters = frequencyMap.get(i);

            if (letters.length() > 1) {
                // alphabetize, then add to realChecksum
                char[] chars = letters.toCharArray();
                Arrays.sort(chars);
                String alphabetizedLetters = new String(chars);
                realChecksum += alphabetizedLetters;
            } else {
                realChecksum += letters;
            }
        }
        return realChecksum.substring(0, 5);
    }
}
