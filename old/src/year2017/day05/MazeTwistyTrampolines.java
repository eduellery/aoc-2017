package year2017.day05;

import utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class MazeTwistyTrampolines {

    public static void main (String[] args) {
        List<String> input = FileUtils.getLines("2017day05.txt", MazeTwistyTrampolines.class);
        System.out.println("Part 1: " + getMazeCount(input, false));
        System.out.println("Part 2: " + getMazeCount(input, true));
    }

    private static int getMazeCount(final List<String> input, boolean decrease) {
        int count = 0;
        int i = 0;
        ArrayList<String> result = new ArrayList<>(input);
        try {
            while (count < Integer.MAX_VALUE) {
                int value = Integer.valueOf(result.get(i));
                if (decrease) {
                    result.set(i, String.valueOf(value >= 3 ? value - 1 : value + 1));
                } else {
                    result.set(i, String.valueOf(value + 1));
                }
                i += value;
                count++;
            }
        } catch (IndexOutOfBoundsException ie) {
            return count;
        }
        return -1;
    }

}
