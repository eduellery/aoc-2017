package year2017.day11;

import utils.FileUtils;

public class AnotherHexEd {

    enum HexDir {
        n(0, -1), ne(1, -1), se(1, 0), s(0, 1), sw(-1, 1), nw(-1, 0);

        int dx, dy;

        HexDir(int dx, int dy) { this.dx = dx; this.dy = dy; }

    }


    private static int hexDistance(int x, int y) {
        return ((Math.abs(x) + Math.abs(y) + Math.abs(x + y))/ 2);
    }


    public static void main(String[] args) {

        String input = FileUtils.getLine("2017day11.txt",AnotherHexEd.class);
        int x = 0;
        int y = 0;

        int furthest = 0;
        int dist = 0;

        for (String each : input.split(",")) {
            HexDir current = HexDir.valueOf(each);
            x += current.dx;
            y += current.dy;
            dist = hexDistance(x, y);
            if (dist > furthest) {
                furthest = dist;
            }
        }
        System.out.println(x + ":" + y);
        System.out.println(dist);
        System.out.println(furthest);
    }
}
