import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public record Day07(String part1, int part2) {

    public static Day07 fromValues(List<String> lines) {
        Set<String> all = new HashSet<>();
        Set<String> nonRoot = new HashSet<>();
        Map<String, Disc> discs = new HashMap<>();

        for (String line : lines) {
            String[] parts = line.split(",\\s|\\s");
            String name = parts[0];
            all.add(name);
            Disc disc = discs.computeIfAbsent(name, Disc::new);
            disc.setWeight(Integer.parseInt(parts[1].replaceAll("[()]", "")));
            if (parts.length > 2) {
                for (int i = 3; i < parts.length; i++) {
                    String childName = parts[i];
                    nonRoot.add(childName);
                    Disc child = discs.computeIfAbsent(childName, Disc::new);
                    disc.addChild(child);
                }
            }
        }

        String rootName =
                all.stream().filter(name -> !nonRoot.contains(name)).findFirst().orElseThrow();
        Disc root = discs.get(rootName);

        return new Day07(rootName, fixedValue(root));
    }

    private static int fixedValue(Disc disc) {
        Disc grandpa = null, parent = null;

        while (disc != null) {
            grandpa = parent;
            parent = disc;
            disc = getFaultyDisc(disc);
        }

        int incorrectWeight = parent.getTotalWeight();
        int correctWeight =
                grandpa.getChildren().stream()
                        .filter(d -> d.getTotalWeight() != incorrectWeight)
                        .findFirst()
                        .orElseThrow()
                        .getTotalWeight();

        return parent.getWeight() + (correctWeight - incorrectWeight);
    }

    private static Disc getFaultyDisc(Disc disc) {
        return disc.getChildren().stream()
                .collect(Collectors.groupingBy(Disc::getTotalWeight, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .map(
                        e ->
                                disc.getChildren().stream()
                                        .filter(d -> d.getTotalWeight() == e.getKey())
                                        .findFirst()
                                        .orElse(null))
                .findFirst()
                .orElse(null);
    }

    private static class Disc {
        private final String name;
        private int weight;
        private final List<Disc> children = new ArrayList<>();

        public Disc(String name) {
            this.name = name;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public void addChild(Disc disc) {
            children.add(disc);
        }

        public int getWeight() {
            return weight;
        }

        public List<Disc> getChildren() {
            return children;
        }

        public int getTotalWeight() {
            return weight + children.stream().mapToInt(Disc::getTotalWeight).sum();
        }
    }
}
