import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record Day04(int part1, int part2) {

    public Day04(List<String> input) {
        this(solve(input, Day04::isValidPwd1), solve(input, Day04::isValidPwd2));
    }

    private static int solve(List<String> input, Predicate<List<String>> validator) {
        return (int) input.stream()
                .map(line -> List.of(line.split("\\s+")))
                .filter(validator)
                .count();
    }

    private static boolean isValidPwd1(List<String> words) {
        return words.size() == words.stream().distinct().count();
    }

    private static boolean isValidPwd2(List<String> words) {
        return words.size() == words.stream()
                .map(word -> word.chars()
                        .sorted()
                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                        .toString())
                .distinct()
                .count();
    }
}
