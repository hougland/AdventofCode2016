package com.hougland.adventofcode.day7;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    
    public static void main(String[] args) {
        List<IPAddress> ipAddresses = parseCSV("src/com/hougland/adventofcode/day7/day7.csv");

        List<IPAddress> realIPAddresses = ipAddresses
                .stream()
                .filter(IPAddress::supportsAbba)
                .collect(Collectors.toList());

        System.out.println("Number of real IP addresses: " + realIPAddresses.size());
    }


    public static List<IPAddress> parseCSV(String filepath) {
        List<IPAddress> ipAddresses = new ArrayList<>();

        try {
            FileReader input = new FileReader(filepath);
            BufferedReader bufRead = new BufferedReader(input);
            String myLine;

            while ( (myLine = bufRead.readLine()) != null) {
                IPAddress ipAddress = new IPAddress(myLine);
                ipAddresses.add(ipAddress);
            }

        } catch (FileNotFoundException e) {
            System.out.print(e);
        } catch (IOException e) {
            System.out.print(e);
        }

        return ipAddresses;
    }
}
