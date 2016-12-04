package com.hougland.adventofcode.day3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<MaybeTriangle> triangles = parseCsv("src/com/hougland/adventofcode/day3/day3.csv");
        int numTriangles = 0;

        for (MaybeTriangle triangle : triangles) {
            if (triangle.isTriangle()) {
                numTriangles++;
            }
        }

        System.out.println("Number of true triangles: " + numTriangles);
    }

    private static List<MaybeTriangle> parseCsv(String filename) {
        MaybeTriangle maybeTriangle;
        List<MaybeTriangle> triangles = new ArrayList<>();

        try {
            FileReader input = new FileReader(filename);
            BufferedReader bufRead = new BufferedReader(input);
            String myLine;

            while ( (myLine = bufRead.readLine()) != null)
            {
                String[] maybeTriangleArray = myLine.split("\\s+");
                int side1 = Integer.valueOf(maybeTriangleArray[1]);
                int side2 = Integer.valueOf(maybeTriangleArray[2]);
                int side3 = Integer.valueOf(maybeTriangleArray[3]);

                maybeTriangle = new MaybeTriangle(side1, side2, side3);

                triangles.add(maybeTriangle);
            }

        } catch (FileNotFoundException e) {
            System.out.print(e);
        } catch (IOException e) {
            System.out.print(e);

        }
        return triangles;
    }
}
