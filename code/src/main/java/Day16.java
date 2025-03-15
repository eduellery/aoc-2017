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
import java.util.Queue;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntBiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public record Day16(String part1, String part2) {

    private static String ZEROS_32 = "00000000000000000000000000000000";
    private static int FACTOR_A = 16807;
    private static int FACTOR_B = 48271;

    public static Day16 fromInstructions(String dancers, List<String> instructions) {
        List<DanceMove> danceMoves = createDanceSteps(instructions);
        String part1 = moveDancers(dancers, danceMoves);
        dancers = performDancesWithMemoization(dancers, danceMoves, 1_000_000_000);
        return new Day16(part1, dancers);
    }

    private static String performDancesWithMemoization(String dancers, List<DanceMove> danceMoves, int iterations) {
        Map<String, Integer> seen = new HashMap<>();
        List<String> states = new ArrayList<>();

        int i = 0;
        while (i < iterations) {
            if (seen.containsKey(dancers)) {
                int cycleLength = i - seen.get(dancers);
                int remaining = (iterations - i) % cycleLength;
                dancers = states.get(seen.get(dancers) + remaining);
                i = iterations;
                continue;
            }
            seen.put(dancers, i);
            states.add(dancers);
            dancers = moveDancers(dancers, danceMoves);
            i++;
        }
        return dancers;
    }

    private static String moveDancers(String dancers, List<DanceMove> danceMoves) {
        for (DanceMove move : danceMoves) {
            dancers = move.apply(dancers);
        }
        return dancers;
    }

    private static List<DanceMove> createDanceSteps(List<String> moves) {
        List<DanceMove> danceMoves = new ArrayList<>();
        for (String move : moves) {
            switch (move.charAt(0)) {
                case 's' -> danceMoves.add(new DanceMove(DanceMove.Type.S, Integer.parseInt(move.substring(1)), -1, -1, null, null));
                case 'x' -> {
                    String[] parts = move.substring(1).split("/");
                    danceMoves.add(new DanceMove(DanceMove.Type.X, -1, Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), null, null));
                }
                default -> {
                    String[] parts = move.substring(1).split("/");
                    danceMoves.add(new DanceMove(DanceMove.Type.P, -1, -1, -1, parts[0], parts[1]));
                }
            }
        }
        return danceMoves;
    }

    private record DanceMove(Type type, int index, int source, int target, String partnerA, String partnerB) {

        enum Type { S, X, P }

        public String apply(String dancers) {
            return switch (type) {
                case S -> swap(dancers, dancers.length() - index);
                case X -> swap(dancers, source, target);
                default -> swap(dancers, dancers.indexOf(partnerA), dancers.indexOf(partnerB));
            };
        }

        private static String swap(String dancers, int index) {
            return dancers.substring(index) + dancers.substring(0, index);
        }

        private static String swap(String dancers, int source, int target) {
            char[] arr = dancers.toCharArray();
            char temp = arr[source];
            arr[source] = arr[target];
            arr[target] = temp;
            return new String(arr);
        }
    }
}
