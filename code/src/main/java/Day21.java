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
        String[] image = new String[]{".#.", "..#", "###"};
        boolean[][] pattern = strArrayToBooleanArrays(image);
        pattern = run(rules, pattern, initialLoops);
        int part1 = count(pattern);
        pattern = run(rules, pattern, extraLoops);
        int part2 = count(pattern);
        return new Day21(part1, part2);
    }

    private static boolean[][] run(List<Rule> rules, boolean[][] pattern, int iterations) {
        for (int iter = 0; iter < iterations; iter++) {
            int size = pattern.length;
            int ss = (size % 2 == 0) ? 2 : 3;
            int ns = ss + 1;
            int newSize = size / ss * ns;
            boolean[][] newPattern = new boolean[newSize][newSize];

            for (int r = 0; r < size / ss; r++) {
                for (int c = 0; c < size / ss; c++) {
                    boolean[][] square = getSquare(pattern, r, c, ss);
                    boolean[][] transformed = transform(rules, square);
                    putSquare(newPattern, r, c, transformed);
                }
            }
            pattern = newPattern;
        }
        return pattern;
    }

    private static int count(boolean[][] pattern) {
        int cnt = 0;
        for (boolean[] row : pattern) {
            for (boolean cell : row) {
                if (cell) cnt++;
            }
        }
        return cnt;
    }

    private static boolean[][] transform(List<Rule> rules, boolean[][] square) {
        for (Rule rule : rules) {
            if (rule.matches(square)) return rule.target;
        }
        throw new IllegalStateException("No matching rule found");
    }

    private static boolean[][] getSquare(boolean[][] pattern, int r, int c, int ss) {
        boolean[][] sq = new boolean[ss][ss];
        int y = r * ss;
        int x = c * ss;
        for (int i = 0; i < ss; i++) {
            System.arraycopy(pattern[y + i], x, sq[i], 0, ss);
        }
        return sq;
    }

    private static void putSquare(boolean[][] pattern, int r, int c, boolean[][] sq) {
        int ss = sq.length;
        int y = r * ss;
        int x = c * ss;
        for (int i = 0; i < ss; i++) {
            System.arraycopy(sq[i], 0, pattern[y + i], x, ss);
        }
    }

    static private boolean[][] strArrayToBooleanArrays(String[] strArr) {
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
        ArrayList<boolean[][]> srcs;

        public Rule(String[] arr) {
            source = strArrayToBooleanArrays(arr[0].split("/"));
            initSources();
            target = strArrayToBooleanArrays(arr[1].split("/"));
        }

        private void initSources() {
            srcs = new ArrayList<>();
            srcs.add(source);
            boolean[][] s = source;
            for (int i = 0; i < 3; i++) {
                s = rotate(s);
                srcs.add(s);
            }
            s = flip(source);
            srcs.add(s);
            for (int i = 0; i < 3; i++) {
                s = rotate(s);
                srcs.add(s);
            }
        }

        public boolean matches(boolean[][] square) {
            if (square.length != source.length) return false;
            for (boolean[][] src : srcs) {
                if (match(square, src)) return true;
            }
            return false;
        }

        private boolean match(boolean[][] square, boolean[][] src) {
            int l = square.length;
            for (int r = 0; r < l; r++) {
                if (!java.util.Arrays.equals(square[r], src[r])) return false;
            }
            return true;
        }

        private boolean[][] flip(boolean[][] square) {
            int l = square.length;
            boolean[][] res = new boolean[l][l];
            for (int r = 0; r < l; r++) {
                for (int c = 0; c < l; c++) {
                    res[r][l - c - 1] = square[r][c];
                }
            }
            return res;
        }

        private boolean[][] rotate(boolean[][] square) {
            int l = square.length;
            boolean[][] res = new boolean[l][l];
            for (int r = 0; r < l; r++) {
                for (int c = 0; c < l; c++) {
                    res[c][l - r - 1] = square[r][c];
                }
            }
            return res;
        }
    }
}
