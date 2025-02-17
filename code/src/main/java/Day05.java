import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record Day05(int part1, int part2) {

    public Day05(List<Integer> input) {
        this(solve(input, Day05::countSteps1), solve(input, Day05::countSteps2));
    }

    private static int solve(List<Integer> input, Function<int[], Integer> stepCounter) {
        int[] values = input.stream().mapToInt(Integer::intValue).toArray();
        return stepCounter.apply(values);
    }

    private static int countSteps1(int[] values) {
        int steps = 0, index = 0;
        while (index < values.length) {
            int jump = values[index]++;
            index += jump;
            steps++;
        }
        return steps;
    }

    private static int countSteps2(int[] values) {
        int steps = 0, index = 0;
        while (index < values.length) {
            int jump = values[index];
            values[index] += (jump >= 3) ? -1 : 1;
            index += jump;
            steps++;
        }
        return steps;
    }
}
