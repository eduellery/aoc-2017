import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record Day23(long part1, long part2) {

    public Day23(List<String> input) {
        this(solvePart1(input), solvePart2(input));
    }

    private static long solvePart1(List<String> input) {
        return new Machine().run(input).debug.getOrDefault("mul", 0L);
    }

    private static long solvePart2(List<String> input) {
        int b = Integer.parseInt(input.get(0).split(" ")[2]) * 100 + 100000;
        return IntStream.iterate(b, i -> i <= b + 17000, i -> i + 17)
                .filter(n -> !isPrime(n))
                .count();
    }

    private static boolean isPrime(int num) {
        return (num < 2) ? false : IntStream.rangeClosed(2, (int) Math.sqrt(num)).noneMatch(i -> num % i == 0);
    }

    private static class Machine {
        private final Map<String, Long> registers = new HashMap<>();
        private int pc = 0;
        public final Map<String, Long> debug = new HashMap<>();

        public Machine run(List<String> instructions) {
            while (pc >= 0 && pc < instructions.size()) {
                execute(instructions.get(pc));
            }
            return this;
        }

        private void execute(String instruction) {
            String[] parts = instruction.split(" ");
            debug.put(parts[0], debug.getOrDefault(parts[0], 0L) + 1);

            switch (parts[0]) {
                case "set" -> registers.put(parts[1], deref(parts[2]));
                case "sub" -> registers.put(parts[1], deref(parts[1]) - deref(parts[2]));
                case "mul" -> registers.put(parts[1], deref(parts[1]) * deref(parts[2]));
                default -> {
                    if (deref(parts[1]) != 0) {
                        pc += (int) deref(parts[2]) - 1;
                    }
                }
            }
            pc++;
        }

        private long deref(String key) {
            return key.matches("-?\\d+") ? Long.parseLong(key) : registers.getOrDefault(key, 0L);
        }
    }
}
