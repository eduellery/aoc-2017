import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;

public record Day08(int part1, int part2) {

    private static Map<String, BiPredicate<Integer, Integer>> comparisons =
            Map.of(
                    "==", Integer::equals,
                    "!=", (x, y) -> !x.equals(y),
                    "<", (x, y) -> x < y,
                    ">", (x, y) -> x > y,
                    "<=", (x, y) -> x <= y,
                    ">=", (x, y) -> x >= y);

    private static Map<String, ToIntBiFunction<Integer, Integer>> commands =
            Map.of("inc", Integer::sum, "dec", (x, y) -> x - y);

    public static Day08 fromValues(List<String> lines) {
        Map<String, Integer> registers = new HashMap<>();
        int highestValueEver = 0;

        for (String line : lines) {
            var tokens = line.split("\\s");
            var reg = tokens[0];
            var command = tokens[1];
            var amount = Integer.parseInt(tokens[2]);
            var testReg = tokens[4];
            var comparison = tokens[5];
            var testAmount = Integer.parseInt(tokens[6]);
            if (comparisons.get(comparison).test(registers.getOrDefault(testReg, 0), testAmount)) {
                registers.compute(
                        reg,
                        (key, value) ->
                                commands.get(command)
                                        .applyAsInt(value == null ? 0 : value, amount));
                highestValueEver = Math.max(highestValueEver, registers.get(reg));
            }
        }

        return new Day08(Collections.max(registers.values()), highestValueEver);
    }
}
