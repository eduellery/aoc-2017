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

public record Day13(int part1, int part2) {

    public Day13(List<String> lines) {
        this(getSeverity(lines, false), getSeverity(lines, true));
    }

    private static int getSeverity(List<String> lines, boolean delayed) {
        List<Layer> layers = new ArrayList<>();
        for (String line : lines) {
            String[] data = line.split(": ");
            layers.add(new Layer(Integer.parseInt(data[0]), Integer.parseInt(data[1])));
        }
        int delay = 0;
        if (!delayed) {
            delay = layers.stream()
                .filter(layer -> layer.isCaught(0))
                .mapToInt(Layer::getSeverity)
                .sum();
        } else {
            boolean caught;
            do {
                caught = false;
                for (Layer layer : layers) {
                    if (layer.isCaught(delay)) {
                        caught = true;
                        break;
                    }
                }
                delay++;
            } while (caught);
            delay--;
        }
        return delay;
    }
}

class Layer {
    private final int depth;
    private final int range;

    public Layer(int depth, int range) {
        this.depth = depth;
        this.range = range;
    }

    public boolean isCaught(int delay) {
        return (depth + delay) % (2 * (range - 1)) == 0;
    }

    public int getSeverity() {
        return depth * range;
    }
}
