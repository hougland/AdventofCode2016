package com.hougland.adventofcode.day4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MaybeRoom {
    private String roomName;
    private String encryptedName;
    private int sectorId;
    private String checksum;

    public MaybeRoom(
            String roomName
    ) {
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

    public boolean isRealRoom() {
        Map<Integer, String> frequencyMap = buildFrequencyMap();

        String realChecksum = buildRealChecksum(frequencyMap);

        return this.checksum.equals(realChecksum);
    }

    public Map<Integer, String> buildFrequencyMap() {
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
                System.out.println("frequencyMap.get(numOccurances): " + frequencyMap.get(numOccurances) + " numOccurances: " + numOccurances);
                if (!frequencyMap.get(numOccurances).contains(letter)) {
                    frequencyMap.put(numOccurances, (frequencyMap.get(numOccurances) + letter));
                }
            }
        }

        return frequencyMap;
    }

    public String buildRealChecksum(
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
