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

public record Day20(int part1, int part2) {

    private static int TIME = 1_000;
    private static final Pattern PATTERN = Pattern.compile("<\\s*(-?\\d+),\\s*(-?\\d+),\\s*(-?\\d+)>");

    public static Day20 processInput(List<String> input) {
        int minimum = Integer.MAX_VALUE, closest = -1;
        List<Particle> particles = new ArrayList<>();
        for (int current = 0; current < input.size(); current++) {
            Particle particle = parseParticle(input.get(current));
            particles.add(particle);
            int distance = particle.getFarDistance();
            if (distance < minimum) {
                minimum = distance;
                closest = current;
            }
        }
        for (int t = 0; t < TIME; t++) {
            particles = removeCollisions(particles.stream().map(Particle::move).toList());
        }
        return new Day20(closest, particles.size());
    }

    private static Particle parseParticle(String line) {
        var values = PATTERN.matcher(line).results()
            .map(m -> Stream.of(m.group(1), m.group(2), m.group(3))
                            .map(Integer::parseInt)
                            .collect(Collectors.toList()))
            .toList();
        return new Particle(values.get(0), values.get(1), values.get(2));
    }

    private static List<Particle> removeCollisions(List<Particle> particles) {
        return particles.stream()
            .collect(Collectors.groupingBy(p -> p, LinkedHashMap::new, Collectors.counting()))
            .entrySet().stream()
            .filter(entry -> entry.getValue() == 1)
            .map(Map.Entry::getKey)
            .toList();
    }

    private static class Particle {
        List<Integer> positions;
        List<Integer> velocities;
        List<Integer> accelerations;

        public Particle(List<Integer> positions, List<Integer> velocities, List<Integer> accelerations) {
            this.positions = positions;
            this.velocities = velocities;
            this.accelerations = accelerations;
        }

        public int getFarDistance() {
            int x = positions.get(0) + velocities.get(0) * TIME + accelerations.get(0) * TIME * TIME;
            int y = positions.get(1) + velocities.get(1) * TIME + accelerations.get(1) * TIME * TIME;
            int z = positions.get(2) + velocities.get(2) * TIME + accelerations.get(2) * TIME * TIME;
            return getManhattanDistance(x, y, z);
        }

        private int getManhattanDistance(int x, int y, int z) {
            return Math.abs(x) + Math.abs(y) + Math.abs(z);
        }

        public Particle move() {
            List<Integer> newVelocities = IntStream.range(0, 3)
                .mapToObj(i -> velocities.get(i) + accelerations.get(i))
                .toList();
            List<Integer> newPositions = IntStream.range(0, 3)
                .mapToObj(i -> positions.get(i) + newVelocities.get(i))
                .toList();
            return new Particle(newPositions, newVelocities, accelerations);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Particle p = (Particle) obj;
            return positions.get(0) == p.positions.get(0) && positions.get(1) == p.positions.get(1) && positions.get(2) == p.positions.get(2);
        }

        @Override
        public int hashCode() {
            return Objects.hash(positions.get(0), positions.get(1), positions.get(2));
        }
    }
}
