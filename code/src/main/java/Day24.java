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

public record Day24(long part1, long part2) {

    public static Day24 build(List<String> input) {
        List<Component> components = new ArrayList<>(input.size());
        for (String value : input) {
            int[] val = Arrays.stream(value.split("/"))
                .mapToInt(Integer::parseInt)
                .toArray();
            components.add(new Component(val[0], val[1]));
        }

        BridgeNode shortBridge = new BridgeNode();
        shortBridge.build(components, 0, false);
        BridgeNode longBridge = new BridgeNode();
        longBridge.build(components, 0, true);

        return new Day24(shortBridge.strength, longBridge.strength);
    }

    private static record Component(int port1, int port2) {
        Integer match(int req) {
            if (port1 == req) return port2;
            else if (port2 == req) return port1;
            return null;
        }

        int strength() {
            return port1 + port2;
        }
    }

    private static class BridgeNode {
        Component component;
        List<BridgeNode> items = new ArrayList<>();
        int position;
        int maxPosition;
        int strength;

        BridgeNode() {
            this.component = new Component(0, 0);
            this.position = 0;
            this.maxPosition = 0;
            this.strength = 0;
        }

        BridgeNode(Component component, int position) {
            this.component = component;
            this.position = position;
            this.maxPosition = position;
            this.strength = component.strength();
        }

        void build(List<Component> components, int port, boolean longest) {
            int maxStrength = 0;
            for (Component comp : components) {
                Integer next = comp.match(port);
                if (next != null) {
                    List<Component> remaining = new ArrayList<>(components);
                    remaining.remove(comp);
                    BridgeNode node = new BridgeNode(comp, position + 1);
                    items.add(node);
                    node.build(remaining, next, longest);
                    if (longest) {
                        if (node.maxPosition > maxPosition || 
                            (node.maxPosition == maxPosition && node.strength > maxStrength)) {
                            maxPosition = node.maxPosition;
                            maxStrength = node.strength;
                        }
                    } else {
                        maxStrength = Math.max(maxStrength, node.strength);
                    }
                }
            }
            strength = component.strength() + maxStrength;
        }
    }
}
