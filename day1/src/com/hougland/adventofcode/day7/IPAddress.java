package com.hougland.adventofcode.day7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IPAddress {
    String fullIPAddress;
    List<String> hypernetSequences;
    List<String> otherSequences;

    public IPAddress(String ipAddress) {
        this.fullIPAddress = ipAddress;

        Map<String, List<String>> sequences = buildHypernetSequences();
        this.hypernetSequences = sequences.get("hypernet");
        this.otherSequences = sequences.get("other");
    }

    public boolean supportsAbba() {
        // if any of the hypernetSequences contains an Abba, return false
        for (String sequence : hypernetSequences) {
            if (containsAbba(sequence)) {
                return false;
            }
        }
        
        // if the rest of the ipAddress contains an Abba, return true
        for (String sequence : otherSequences) {
            if (containsAbba(sequence)) {
                return true;
            }
        }
        return false;
    }

    private boolean containsAbba(String sequence) {
        for (int i = 0; i < sequence.length(); i++) {
            if ((sequence.length() - i) < 4) {
                return false;
            }

            String firstLetter = sequence.substring(i, i + 1);
            String secondLetter = sequence.substring(i + 1, i + 2);

            if (secondLetter.equals(firstLetter)) {
                continue;
            }

            if ((secondLetter + firstLetter).equals(sequence.substring(i + 2, i + 4))) {
                return true;
            }
        }

        return false;
    }

    private Map<String, List<String>> buildHypernetSequences() {
        List<String> hypernetSequences = new ArrayList<>();
        List<String> otherSequences = new ArrayList<>();
        String sequence = null;

        for (int i = 0; i < fullIPAddress.length(); i++) {
            String character = fullIPAddress.substring(i, i + 1);

            if ("[".equals(character)) {
                otherSequences.add(sequence);
                sequence = "";
            } else if ("]".equals(character)) {
                hypernetSequences.add(sequence);
                sequence = null;
            } else if (sequence == null) {
                sequence = character;
            } else {
                sequence += character;
            }
        }

        otherSequences.add(sequence);

        Map<String, List<String>> bothSequences = new HashMap<>();
        bothSequences.put("hypernet", hypernetSequences);
        bothSequences.put("other", otherSequences);

        return bothSequences;
    }
}
