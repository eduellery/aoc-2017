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

public record Day12(int part1, int part2) {

    public static Day12 fromLines(List<String> lines) {
        Map<Integer, Set<Integer>> nodeMap = new HashMap<>();

        for (String line : lines) {
            String[] parts = line.split(" <-> ");
            int node = Integer.parseInt(parts[0]);
            Set<Integer> connections = new HashSet<>();
            for (String connection : parts[1].split(", ")) {
                connections.add(Integer.parseInt(connection));
            }
            nodeMap.put(node, connections);
        }

        Set<Integer> groupZero = bfsGroup(0, nodeMap);

        Set<Set<Integer>> groups = new HashSet<>();
        Set<Integer> visited = new HashSet<>();

        for (Integer node : nodeMap.keySet()) {
            if (!visited.contains(node)) {
                Set<Integer> group = bfsGroup(node, nodeMap);
                groups.add(group);
                visited.addAll(group);
            }
        }

        return new Day12(groupZero.size(), groups.size());
    }

    private static Set<Integer> bfsGroup(int start, Map<Integer, Set<Integer>> nodeMap) {
        Set<Integer> group = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (!group.contains(current)) {
                group.add(current);
                queue.addAll(nodeMap.getOrDefault(current, Collections.emptySet()));
            }
        }
        return group;
    }
}
