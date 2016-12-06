package com.hougland.adventofcode.day4;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // parse csv into MaybeRoom objects
        List<MaybeRoom> rooms = parseCsv("src/com/hougland/adventofcode/day4/day4.csv");

        // for each row, determine if it's a real room
        List<MaybeRoom> realRooms = new ArrayList<>();
        for (MaybeRoom room : rooms) {
            if (room.isRealRoom()) {
                realRooms.add(room);
            }
        }

        // for each real room, add up the sector IDs
        int sectorIdsSum = 0;
        for (MaybeRoom room : realRooms) {
            sectorIdsSum += room.getSectorId();
        }

        System.out.println("Sum of sector Ids: " + sectorIdsSum);

        // Part 2
        // Iterate through realRooms, decrypt the shift cipher, and write result to new file
        // Manually look through real-names.txt for north pole objects
        PrintWriter writer;
        try {
            writer = new PrintWriter("real-names.txt", "UTF-8");

            for (MaybeRoom room : realRooms) {
                String realName = room.getDecryptedName();
                writer.println(String.format("Real: %s SectorId: %s", realName, room.getSectorId()));
            }

            writer.close();

        } catch (IOException e) {
            System.out.print(e);
        }
    }

    private static List<MaybeRoom> parseCsv(String filename) {
        MaybeRoom maybeRoom;
        List<MaybeRoom> rooms = new ArrayList<>();

        try {
            FileReader input = new FileReader(filename);
            BufferedReader bufRead = new BufferedReader(input);
            String myLine;

            while ( (myLine = bufRead.readLine()) != null) {
                maybeRoom = new MaybeRoom(myLine);
                rooms.add(maybeRoom);
            }

        } catch (FileNotFoundException e) {
            System.out.print(e);
        } catch (IOException e) {
            System.out.print(e);

        }
        return rooms;
    }
}
