package com.hougland.adventofcode.day9;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Marker {
    int howManyChars;
    int howManyTimes;
    int length;

    public Marker(String[] marker) {
        this.length = marker.length;

        int firstNumEndingIndex = 0;
        if ("x".equals(marker[1])) {
            howManyChars = Integer.parseInt(marker[0]);
        } else if ("x".equals(marker[2])) {
            howManyChars = Integer.parseInt(marker[0] + marker[1]);
            firstNumEndingIndex = 1;
        } else if ("x".equals(marker[3])) {
            howManyChars = Integer.parseInt(marker[0] + marker[1] + marker[2]);
            firstNumEndingIndex = 2;
        } else if ("x".equals(marker[4])) {
            howManyChars = Integer.parseInt(marker[0] + marker[1] + marker[2] + marker[3]);
            firstNumEndingIndex = 3;
        } else {
            System.out.println(Arrays.stream(marker).collect(Collectors.joining("")));
            throw new IllegalStateException("WHATTT1");
        }

        if (marker.length == firstNumEndingIndex + 3) {
            howManyTimes = Integer.parseInt(marker[firstNumEndingIndex + 2]);
        } else if (marker.length == firstNumEndingIndex + 4) {
            howManyTimes = Integer.parseInt(marker[firstNumEndingIndex + 2] + marker[firstNumEndingIndex + 3]);
        } else {
            throw new IllegalStateException("WHATTT2");
        }
    }
}
