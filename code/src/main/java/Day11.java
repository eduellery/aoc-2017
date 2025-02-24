import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntBiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public record Day11(int part1, int part2) {

    private enum HexDir {
        n(0, -1), ne(1, -1), se(1, 0),
        s(0, 1), sw(-1, 1), nw(-1, 0);

        final int dx, dy;

        HexDir(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }

        private static final Map<String, HexDir> LOOKUP = new HashMap<>();

        static {
            for (HexDir dir : HexDir.values()) {
                LOOKUP.put(dir.name(), dir);
            }
        }

        static HexDir fromString(String direction) {
            return LOOKUP.get(direction);
        }
    }

    public static Day11 fromDirections(List<String> directions) {
        int x = 0, y = 0, dist = 0, furthest = 0;

        for (String dir : directions) {
            HexDir hexDir = HexDir.fromString(dir);
            x += hexDir.dx;
            y += hexDir.dy;
            dist = hexDist(x, y);
            furthest = Math.max(furthest, dist);
        }

        return new Day11(dist, furthest);
    }

    private static int hexDist(int x, int y) {
        return (Math.abs(x) + Math.abs(y) + Math.abs(x + y)) / 2;
    }
}
