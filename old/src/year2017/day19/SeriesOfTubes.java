package year2017.day19;

import utils.FileUtils;
import utils.Timer;

import java.util.List;

public class SeriesOfTubes {

    private static final char vertical = '|';
    private static final char horizontal = '-';
    private static final char cross = '+';
    private static final char space = ' ';

    private static Orientation orientation = Orientation.S;
    private static int count = 0;

    public static void main(String[] args) {
        Timer.startTimer();
        List<String> lines = FileUtils.getLines("2017day19.txt", SeriesOfTubes.class);
        System.out.println(collectChars(lines, getFirstColumn(lines)));
        System.out.println(count);
        System.out.println(Timer.stopTimer());
    }

    private static String collectChars(List<String> lines, int initial) {
        char[][] matrix = new char[202][202];
        StringBuffer result = new StringBuffer();
        boolean finished = false;
        int i = initial;
        int j = 0;
        for (int x = 0;  x < lines.size(); x++) {
            char[] line = lines.get(x).toCharArray();
            matrix[x] = line;
        }
        while (!finished) {
            count++;
            switch (orientation) {
                case N:
                    j--;
                    break;
                case S:
                    j++;
                    break;
                case E:
                    i++;
                    break;
                case W:
                    i--;
                    break;
            }
            finished = handleNext(matrix, result, matrix[j][i], i, j);
        }
        return result.toString();
    }

    private static boolean handleNext(char[][] matrix, StringBuffer result, char c, int i, int j) {
        switch (c) {
            case cross:
                orientation = changeOrientation(matrix, i, j);
                return false;
            case horizontal:
                return false;
            case vertical:
                return false;
            case space:
                return true;
            default:
                result.append(c);
                return false;
        }
    }

    private static Orientation changeOrientation(char[][] matrix, int i, int j) {
        switch (orientation) {
            case N:
            case S:
                if (matrix[j][i + 1] == space) {
                    return Orientation.W;
                } else {
                    return Orientation.E;
                }
            case E:
            case W:
                if (matrix[j + 1][i] == space) {
                    return Orientation.N;
                } else {
                    return Orientation.S;
                }
            default:
                throw new RuntimeException("Unknown orientation " + orientation);
        }
    }

    private static int getFirstColumn(List<String> lines) {
        return lines.get(0).indexOf(vertical);
    }

    private enum Orientation { N, S, E, W}
}
