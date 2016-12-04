package com.hougland.adventofcode.day3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // part 1
        List<MaybeTriangle> triangles = parseCsv1("src/com/hougland/adventofcode/day3/day3.csv");
        int numTriangles = 0;

        for (MaybeTriangle triangle : triangles) {
            if (triangle.isTriangle()) {
                numTriangles++;
            }
        }

        System.out.println("Number of true triangles part 1: " + numTriangles);

        // part 2
        List<MaybeTriangle> triangles2 = parseCsv2("src/com/hougland/adventofcode/day3/day3.csv");
        int numTriangles2 = 0;

        for (MaybeTriangle triangle : triangles2) {
            if (triangle.isTriangle()) {
                numTriangles2++;
            }
        }

        System.out.println("Number of true triangles part 2: " + numTriangles2);
    }

    private static List<MaybeTriangle> parseCsv1(String filename) {
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

    private static List<MaybeTriangle> parseCsv2(String filename) {
        List<MaybeTriangle> triangles = new ArrayList<>();

        try {
            FileReader input = new FileReader(filename);
            BufferedReader bufRead = new BufferedReader(input);
            String myLine;
            int counter = 1;
            List<Integer> sides = new ArrayList<>();

            while ( (myLine = bufRead.readLine()) != null) {
                String[] maybeTriangleArray = myLine.split("\\s+");
                int side1 = Integer.valueOf(maybeTriangleArray[1]);
                int side2 = Integer.valueOf(maybeTriangleArray[2]);
                int side3 = Integer.valueOf(maybeTriangleArray[3]);

                sides.add(side1);
                sides.add(side2);
                sides.add(side3);

                if (counter != 3) {
                    counter++;
                } else {
                    List<MaybeTriangle> threeTriangles = buildThreeTriangles(sides);
                    triangles.addAll(threeTriangles);

                    counter = 1;
                    sides = new ArrayList<>();
                }
            }

        } catch (FileNotFoundException e) {
            System.out.print(e);
        } catch (IOException e) {
            System.out.print(e);

        }
        return triangles;
    }

    public static List<MaybeTriangle> buildThreeTriangles(List<Integer> sides) {
        if (sides.size() != 9) {
            System.out.println("Passed wrong num of sides. Wanted 9, got " + sides.size());
        }

        MaybeTriangle triangle1;
        MaybeTriangle triangle2;
        MaybeTriangle triangle3;

        triangle1 = new MaybeTriangle(sides.get(0), sides.get(3), sides.get(6));
        triangle2 = new MaybeTriangle(sides.get(1), sides.get(4), sides.get(7));
        triangle3 = new MaybeTriangle(sides.get(2), sides.get(5), sides.get(8));

        List<MaybeTriangle> triangles = new ArrayList<>();
        triangles.add(triangle1);
        triangles.add(triangle2);
        triangles.add(triangle3);

        return triangles;
    }
}
