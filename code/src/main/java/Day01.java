import java.io.*;
import java.util.*;

public class Day01 {

    private int part1, part2;

    public Day01(String input) {
        System.out.println("Testing " + input);
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < input.length() - 1; i++) {
            if (Character.getNumericValue(input.charAt(i)) == Character.getNumericValue(input.charAt(i + 1))) {
                sum1 += Character.getNumericValue(input.charAt(i));
            }
            if (Character.getNumericValue(input.charAt(i)) == Character.getNumericValue(input.charAt((i + (input.length() / 2)) % input.length()))) {
                sum2 += Character.getNumericValue(input.charAt(i));
            }
        }
        if (Character.getNumericValue(input.charAt(0)) == Character.getNumericValue(input.charAt(input.length() - 1))) {
            sum1 += Character.getNumericValue(input.charAt(0));
        }
        this.part1 = sum1;
        this.part2 = sum2;
    }

    public int solvePart1() {
        return part1;
    }

    public int solvePart2() {
        return part2;
    }
}
