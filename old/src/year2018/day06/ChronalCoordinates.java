package year2018.day06;

import utils.FileUtils;

import java.awt.*;
import java.util.HashMap;
import java.util.List;

public class ChronalCoordinates {

    public static void main(String[] args) {
        List<String> lines = FileUtils.getLines("2018day06.txt", ChronalCoordinates.class);
        int largestArea = getLargestArea(lines);
    }

    private static int getLargestArea(List<String> lines) {
        HashMap<Integer, Point> points = new HashMap<>();

        int maxx = 0;
        int maxy = 0;
        int count = 0;

        // Get distances
        for (String str : lines) {
            String s[] = str.trim().split(", ");
            int x = Integer.parseInt(s[0]);
            int y = Integer.parseInt(s[1]);
            points.put(count, new Point(x, y));
            count++;
            if (x > maxx) {
                maxx = x;
            }
            if (y > maxy) {
                maxy = y;
            }
        }

        int[][] grid = new int[maxx + 1][maxy + 1];
        HashMap<Integer, Integer> regions = new HashMap<>();

        for (int x = 0; x <= maxx; x++) {
            for (int y = 0; y <= maxy; y++) {

                int best = maxx + maxy;
                int closestPointIndex = -1;

                // find distance to closest point
                for (int i = 0; i < count; i++) {
                    Point p = points.get(i);

                    int dist = Math.abs(x - p.x) + Math.abs(y - p.y);
                    if (dist < best) {
                        best = dist;
                        closestPointIndex = i;
                    } else if (dist == best) {
                        closestPointIndex = -1;
                    }
                }

                grid[x][y] = closestPointIndex;
                Integer nearSquares = regions.get(closestPointIndex);
                if (nearSquares == null) {
                    nearSquares = new Integer(1);
                } else {
                    nearSquares = nearSquares.intValue() + 1;
                }
                regions.put(closestPointIndex, nearSquares);
            }
        }

        for (int key : regions.keySet()) {
            System.out.println(key + " " + regions.get(key));
        }
        
        // remove infinite
        for (int x = 0; x <= maxx; x++) {
            int bad = grid[x][0];
            regions.remove(bad);
            bad = grid[x][maxy];
            regions.remove(bad);
        }
        for (int y = 0; y <= maxy; y++) {
            int bad = grid[0][y];
            regions.remove(bad);
            bad = grid[maxx][y];
            regions.remove(bad);
        }

        for (int key : regions.keySet()) {
            System.out.println(key + " " + regions.get(key));
        }

        // find biggest
        int biggest = 0;
        for (int size : regions.values()) {
            if (size > biggest) {
                biggest = size;
            }
        }

        System.out.println("Biggest: " + biggest);

        int inarea = 0;

        for (int x = 0; x <= maxx; x++) {
            for (int y = 0; y <= maxy; y++) {

                int size = 0;
                for (int i = 0; i < count; i++) {
                    Point p = points.get(i);
                    int dist = Math.abs(x - p.x) + Math.abs(y - p.y);
                    size += dist;
                }

                if (size < 10000) {
                    inarea++;
                }

            }
        }

        System.out.println("Area Size: " + inarea);

        return 0;
    }
}
