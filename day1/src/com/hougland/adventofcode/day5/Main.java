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

        final List<String> five0s = new ArrayList<>();

        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();

            int hexCounter = 0;
            for (int i = 0; i < 8; i++) {
                String hex = "00001";
                while (!"00000".equals(hex.substring(0, 5))) {
                    byte[] toHash = (realInput + String.valueOf(hexCounter)).getBytes();
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
