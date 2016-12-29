package com.hougland.adventofcode.day16;

public class Main {

    public static void main(String[] args) {
        final int diskSpace = 272;
        final String input = "10011111011011001";
//        final int diskSpace = 20;
//        final String input = "10000";

        String data = getDataForDiskSpace(input, diskSpace);
        String checksum = getOddChecksum(data);

        System.out.println("Checksum: " + checksum);
    }

    public static String getOddChecksum(final String data) {
        String checksum = data;

        while (checksum.length() % 2 == 0) {
            checksum = getChecksum(checksum);
        }

        return checksum;
    }

    public static String getChecksum(final String data) {
        String[] chars = data.split("");
        String checksum = "";

        for (int i = 0; i < chars.length; i += 2) {
            if (chars[i + 1].equals(chars[i])) {
                checksum += "1";
            } else {
                checksum += "0";
            }
        }

        return checksum;
    }

    public static String getDataForDiskSpace(final String input, final int diskSpace) {
        String data = input;

        while (data.length() < diskSpace) {
            data = getData(data);
        }

        return data.substring(0, diskSpace);
    }

    public static String getData(final String input) {
        String a = input;
        String b = reverseString(a);
        b = replaceChars(b);

        return a + "0" + b;
    }

    public static String replaceChars(final String input) {
        String[] chars = input.split("");
        String replaced = "";

        for (int i = 0; i < chars.length; i++) {
            if ("1".equals(chars[i])) {
                replaced += "0";
            } else {
                replaced += "1";
            }
        }

        return replaced;
    }

    public static String reverseString(final String input) {
        String[] chars = input.split("");
        String reversed = "";

        for (int i = chars.length - 1; i >= 0; i--) {
            reversed += chars[i];
        }

        return reversed;
    }
}
