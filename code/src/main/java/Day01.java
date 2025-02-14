import java.util.stream.IntStream;

public record Day01(int part1, int part2) {

    public Day01(String input) {
        this(solvePart1(input), 2);
    }

    private static int solvePart1(String input) {
        int[] digits = input.chars().map(Character::getNumericValue).toArray();
        int length = digits.length;

        return IntStream.range(0, length)
                .filter(i -> digits[i] == digits[(i + 1) % length])
                .map(i -> digits[i])
                .sum();
    }

}
