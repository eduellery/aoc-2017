import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@TestMethodOrder(MethodOrderer.MethodName.class)
class Day11Test {

    private String inputFile = "day11.in";

    private static Stream<Arguments> provideExamples() {
        return Stream.of(
                Arguments.of(List.of("ne", "ne", "ne"), 3, 3),
                Arguments.of(List.of("ne", "ne", "sw", "sw"), 0, 2),
                Arguments.of(List.of("ne", "ne", "s", "s"), 2, 2),
                Arguments.of(List.of("se", "sw", "se", "sw", "sw"), 3, 3));
    }

    @ParameterizedTest
    @MethodSource("provideExamples")
    void testExample(List<String> input, int part1, int part2) {
        Day11 day = Day11.fromDirections(input);
        assertEquals(part1, day.part1(), "Part 1");
        assertEquals(part2, day.part2(), "Part 2");
    }

    @Test
    void testSolution() {
        List<String> input = Utils.readLineAsStringList(inputFile, Day11Test.class);
        Day11 day = Day11.fromDirections(input);
        assertEquals(722, day.part1(), "Part 1");
        assertEquals(1551, day.part2(), "Part 2");
    }
}
