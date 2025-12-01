import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public record Day02(int part1, int part2) {

    public static Day02 fromLines(List<String> lines, boolean part1, boolean part2) {
        int sum1 =
                part1 ? lines.stream().map(Day02::parseLine).mapToInt(Day02::solvePart1).sum() : 0;

        int sum2 =
                part2 ? lines.stream().map(Day02::parseLine).mapToInt(Day02::solvePart2).sum() : 0;

        return new Day02(sum1, sum2);
    }

    private static List<Integer> parseLine(String line) {
        return Arrays.stream(line.split("\\t")).map(Integer::parseInt).collect(Collectors.toList());
    }
    ;

    private static int solvePart1(List<Integer> numbers) {
        IntSummaryStatistics stats =
                numbers.stream().mapToInt(Integer::intValue).summaryStatistics();
        return stats.getMax() - stats.getMin();
    }
    ;

    private static int solvePart2(List<Integer> numbers) {
        return numbers.stream()
                .flatMap(i -> numbers.stream().filter(j -> j != i && j % i == 0).map(j -> j / i))
                .findFirst()
                .get();
    }
    ;
}
