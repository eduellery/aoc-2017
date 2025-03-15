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

public record Day19(String part1, int part2) {

    private static final char VERTICAL = '|';
    private static final char HORIZONTAL = '-';
    private static final char CROSS = '+';
    private static final char SPACE = ' ';

    private enum Orientation { N, S, E, W }

    public static Day19 traverseDiagram(List<String> diagram) {
        return collectChars(diagram, getFirstColumn(diagram));
    }

    private static Day19 collectChars(List<String> lines, int initial) {
        char[][] matrix = lines.stream().map(String::toCharArray).toArray(char[][]::new);
        StringBuilder result = new StringBuilder();
        Orientation orientation = Orientation.S;
        boolean finished = false;
        int i = initial, j = 0, count = 0;

        while (!finished) {
            count++;
            switch (orientation) {
                case N -> j--;
                case S -> j++;
                case E -> i++;
                default -> i--;
            }

            Orientation newOrientation = handleNext(matrix, result, matrix[j][i], i, j, orientation);
            if (newOrientation == null) {
                finished = true;
            } else {
                orientation = newOrientation;
            }
        }
        return new Day19(result.toString(), count);
    }

    private static Orientation handleNext(char[][] matrix, StringBuilder result, char c, int i, int j, Orientation orientation) {
        return switch (c) {
            case CROSS -> changeOrientation(matrix, i, j, orientation);
            case HORIZONTAL, VERTICAL -> orientation;
            case SPACE -> null; // Null indicates stopping condition
            default -> {
                result.append(c);
                yield orientation;
            }
        };
    }

    private static Orientation changeOrientation(char[][] matrix, int i, int j, Orientation orientation) {
        return switch (orientation) {
            case N, S -> (matrix[j][i + 1] == SPACE) ? Orientation.W : Orientation.E;
            default -> (matrix[j + 1][i] == SPACE) ? Orientation.N : Orientation.S;
        };
    }

    private static int getFirstColumn(List<String> lines) {
        return lines.get(0).indexOf(VERTICAL);
    }
}
