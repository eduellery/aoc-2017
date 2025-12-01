import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

public record Day06(int part1, int part2) {

    public static Day06 fromValues(List<Integer> values) {
        var seen = new HashMap<List<Integer>, Integer>();
        int count = 0;
        seen.put(List.copyOf(values), count);

        while (true) {
            values = redistribute(values);
            count++;

            if (seen.containsKey(values)) break;
            seen.put(List.copyOf(values), count);
        }

        return new Day06(count, count - seen.get(values));
    }

    private static List<Integer> redistribute(List<Integer> values) {
        var list = new ArrayList<>(values);
        var index =
                IntStream.range(0, list.size()).boxed().max(Comparator.comparing(list::get)).get();

        var blocks = list.get(index);
        list.set(index, 0);

        for (int i = 1; i <= blocks; i++) {
            list.set((index + i) % list.size(), list.get((index + i) % list.size()) + 1);
        }

        return List.copyOf(list);
    }
}
