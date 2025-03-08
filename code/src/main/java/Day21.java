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

public record Day21(int part1, int part2) {

    public static Day21 processInput(List<String> input, int initialLoops, int extraLoops) {
        ArrayList<Rule> rules = new ArrayList<>(input.size());
        for (String line : input) {
            String[] arr = line.split(" => ");
            rules.add(new Rule(arr));
        }
        boolean[][] pattern = strArrayToBoolArrays(new String[]{".#.", "..#", "###"});
        pattern = run(rules, pattern, initialLoops);
        int part1 = count(pattern);
        pattern = run(rules, pattern, extraLoops);
        int part2 = count(pattern);
        return new Day21(part1, part2);
    }

    private static boolean[][] run(List<Rule> rules, boolean[][] pattern, int loops) {
        for (int i = 0; i < loops; i++) {
            int size = pattern.length;
            int ss = (size % 2 == 0) ? 2 : 3;
            int ns = ss + 1;
            int newSize = size / ss * ns;
            boolean[][] newPattern = new boolean[newSize][newSize];
            for (int row = 0; row < size / ss; row++) {
                for (int column = 0; column < size / ss; column++) {
                    boolean[][] square = getSquare(pattern, row, column, ss);
                    boolean[][] transformed = transform(rules, square);
                    putSquare(newPattern, row, column, transformed);
                }
            }
            pattern = newPattern;
        }
        return pattern;
    }

    private static int count(boolean[][] pattern) {
        int counter = 0;
        for (boolean[] row : pattern) {
            for (boolean cell : row) {
                if (cell) counter++;
            }
        }
        return counter;
    }

    private static boolean[][] transform(List<Rule> rules, boolean[][] square) {
        for (Rule rule : rules) {
            if (rule.matches(square)) return rule.target;
        }
        throw new IllegalStateException("No matching rule found");
    }

    private static boolean[][] getSquare(boolean[][] pattern, int row, int column, int squareSize) {
        boolean[][] square = new boolean[squareSize][squareSize];
        int y = row * squareSize;
        int x = column * squareSize;
        for (int i = 0; i < squareSize; i++) {
            System.arraycopy(pattern[y + i], x, square[i], 0, squareSize);
        }
        return square;
    }

    private static void putSquare(boolean[][] pattern, int row, int column, boolean[][] square) {
        int squareSize = square.length;
        int y = row * squareSize;
        int x = column * squareSize;
        for (int i = 0; i < squareSize; i++) {
            System.arraycopy(square[i], 0, pattern[y + i], x, squareSize);
        }
    }

    static private boolean[][] strArrayToBoolArrays(String[] strArr) {
        boolean[][] boolArr = new boolean[strArr.length][];
        for (int i = 0; i < strArr.length; i++) {
            boolArr[i] = new boolean[strArr[i].length()];
            for (int j = 0; j < strArr[i].length(); j++) {
                boolArr[i][j] = strArr[i].charAt(j) == '#';
            }
        }
        return boolArr;
    }

    static private class Rule {
        boolean[][] source, target;
        ArrayList<boolean[][]> sources;

        public Rule(String[] arr) {
            source = strArrayToBoolArrays(arr[0].split("/"));
            target = strArrayToBoolArrays(arr[1].split("/"));
            initSources();
        }

        private void initSources() {
            sources = new ArrayList<>();
            sources.add(source);
            boolean[][] newSource = source;
            for (int i = 0; i < 3; i++) {
                newSource = rotate(newSource);
                sources.add(newSource);
            }
            newSource = flip(source);
            sources.add(newSource);
            for (int i = 0; i < 3; i++) {
                newSource = rotate(newSource);
                sources.add(newSource);
            }
        }

        public boolean matches(boolean[][] square) {
            if (square.length != source.length) return false;
            for (boolean[][] source : sources) {
                if (match(square, source)) return true;
            }
            return false;
        }

        private boolean match(boolean[][] square, boolean[][] source) {
            int length = square.length;
            for (int row = 0; row < length; row++) {
                if (!java.util.Arrays.equals(square[row], source[row])) return false;
            }
            return true;
        }

        private boolean[][] flip(boolean[][] square) {
            int length = square.length;
            boolean[][] result = new boolean[length][length];
            for (int row = 0; row < length; row++) {
                for (int column = 0; column < length; column++) {
                    result[row][length - column - 1] = square[row][column];
                }
            }
            return result;
        }

        private boolean[][] rotate(boolean[][] square) {
            int length = square.length;
            boolean[][] result = new boolean[length][length];
            for (int row = 0; row < length; row++) {
                for (int column = 0; column < length; column++) {
                    result[column][length - row - 1] = square[row][column];
                }
            }
            return result;
        }
    }
}
