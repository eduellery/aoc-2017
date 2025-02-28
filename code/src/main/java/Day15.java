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

public record Day15(int part1, int part2) {

    private static String ZEROS_32 = "00000000000000000000000000000000";
    private static int FACTOR_A = 16807;
    private static int FACTOR_B = 48271;

    public static Day15 fromSeeds(int seedA, int seedB) {
        return new Day15(
            countMatches(new Generator(seedA, FACTOR_A, 1), new Generator(seedB, FACTOR_B, 1), 40_000_000),
            countMatches(new Generator(seedA, FACTOR_A, 4), new Generator(seedB, FACTOR_B, 8), 5_000_000)
        );
    }

    private static int countMatches(Generator genA, Generator genB, int times) {
        return (int) IntStream.range(0, times)
                .filter(i -> compareTrailingBits(genA.getAndGenerateNext(), genB.getAndGenerateNext()))
                .count();
    }

    private static boolean compareTrailingBits(long valueA, long valueB) {
        return toBinaryString(valueA).equals(toBinaryString(valueB));
    }

    private static String toBinaryString(long value) {
        String result = Long.toBinaryString(value);
        return (ZEROS_32.substring(0, 32 - result.length()) + result).substring(16);
    }

    private static class Generator {

        private static final int DIVISOR = 2_147_483_647;

        private final int factor;
        private final int multiple;
        private long value;

        public Generator(int seed, int factor, int multiple) {
            this.value = seed;
            this.factor = factor;
            this.multiple = multiple;
        }

        public long getAndGenerateNext() {
            long temp = value;
            do {
                value = (value * factor) % DIVISOR;
            } while (value % multiple != 0);
            return temp;
        }
    }
}
