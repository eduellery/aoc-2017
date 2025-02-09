package year2017.day11;

import utils.FileUtils;
import utils.MathUtils;
import utils.TestUtils;

public class HexEd {

    public static void main(String[] args) {
        String[] test = {"ne,ne,ne", "ne,ne,sw,sw", "ne,ne,s,s", "se,sw,se,sw,sw", "ne,se,ne", "n", "s,s,s", "n,n,ne", "ne,se"};
        Integer[] results = {3, 0, 2, 3, 3, 1, 3, 3, 2};
        System.out.println(TestUtils.test(test, results, HexEd::getFinalHexDistance));
        System.out.println(getFinalHexDistance(FileUtils.getLine("2017day11.txt", HexEd.class)));
        System.out.println(getMaxHexDistance(FileUtils.getLine("2017day11.txt", HexEd.class)));
    }

    private static Integer getFinalHexDistance(String path) {
        return getHexDistance(path, false);
    }

    private static Integer getMaxHexDistance(String path) {
        return getHexDistance(path, true);
    }

    private static Integer getHexDistance(String path, boolean furthest) {
        String[] directions = path.trim().split(",");
        double i = 0;
        double j = 0;
        int result = 0;
        for (String direction : directions) {
            switch (Directions.valueOf(direction.toUpperCase())) {
                case N:
                    j++;
                    break;
                case NE:
                    i += 0.5;
                    j += 0.5;
                    break;
                case SE:
                    i += 0.5;
                    j -= 0.5;
                    break;
                case S:
                    j--;
                    break;
                case SW:
                    i -= 0.5;
                    j -= 0.5;
                    break;
                case NW:
                    i -= 0.5;
                    j += 0.5;
                    break;
            }
            if (furthest) {
                int current = Double.valueOf(MathUtils.getHexDistance(i, j)).intValue();
                result = current > result ? current : result;
            }
        }
        return furthest ? result : Double.valueOf(MathUtils.getHexDistance(i, j)).intValue();
    }

    private enum Directions {N, NE, SE, S, SW, NW};
}
