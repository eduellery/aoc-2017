import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntBiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public record Day17(int part1, int part2) {

    public Day17(int input) {
        this(createList(2017, input), getFirstItem(50_000_000, input));
    }

    private static int createList(int insertions, int size) {
        List<Integer> result = new ArrayList<>(insertions + 1);
        result.add(0);
        int index = 0;

        for (int i = 1; i <= insertions; i++) {
            index = ((index + size) % i) + 1;
            result.add(index, i);
        }

        return result.get(result.indexOf(insertions) + 1);
    }

    private static int getFirstItem(int insertions, int size) {
        int index = 0;
        int result = -1;

        for (int i = 1; i <= insertions; i++) {
            index = ((index + size) % i) + 1;
            if (index == 1) result = i;
        }

        return result;
    }
}
