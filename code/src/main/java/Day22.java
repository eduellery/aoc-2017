import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.IntSummaryStatistics;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
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
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public record Day22(int infected) {

    public static Day22 processInput(List<String> input, int rounds, boolean mutation) {
        Map<Long, Character> grid = new HashMap<>();
        int size = input.size();
        for (int y = 0; y < size; y++) {
            char[] row = input.get(y).toCharArray();
            for (int x = 0; x < size; x++) {
                grid.put(hash(x, y), row[x]);
            }
        }
        int infected = getInfections(grid, size, rounds, mutation);
        return new Day22(infected);
    }

    private static int getInfections(Map<Long, Character> grid, int size, int rounds, boolean mutation) {
        int x = size / 2, y = size / 2;
        int dx = 0, dy = -1;
        int infections = 0;

        for (int i = 0; i < rounds; i++) {
            char state = grid.getOrDefault(hash(x, y), '.');

            switch (state) {
                case '.' -> { // Clean node
                    int temp = dx;
                    dx = dy;
                    dy = -temp;
                    grid.put(hash(x, y), mutation ? 'W' : '#');
                    if (!mutation) infections++;
                }
                case 'W' -> { // Weakened node
                    grid.put(hash(x, y), '#');
                    infections++;
                }
                case 'F' -> { // Flagged node
                    dx = -dx;
                    dy = -dy;
                    grid.put(hash(x, y), '.');
                }
                default -> { // Infected node '#'
                    int temp = dx;
                    dx = -dy;
                    dy = temp;
                    grid.put(hash(x, y), mutation ? 'F' : '.');
                }
            }

            x += dx;
            y += dy;
        }

        return infections;
    }

    private static long hash(int x, int y) {
        return ((long) x << 32) | (y & 0xFFFFFFFFL);
    }
}
