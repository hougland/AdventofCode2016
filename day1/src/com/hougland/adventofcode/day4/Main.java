package com.hougland.adventofcode.day4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
