import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.MethodName.class)
class Day11Test {

    private String inputFile = "day11.in";

    private static Stream<Arguments> provideExamplesPart1() {
        return Stream.of(
            Arguments.of(List.of("ne", "ne", "ne"), 3),
            Arguments.of(List.of("ne", "ne", "sw", "sw"), 0),
            Arguments.of(List.of("ne", "ne", "s", "s"), 2),
            Arguments.of(List.of("se", "sw", "se", "sw", "sw"), 3)
        );
    }

    private static Stream<Arguments> provideExamplesPart2() {
        return Stream.of(
            Arguments.of(List.of("ne", "ne", "ne"), 3),
            Arguments.of(List.of("ne", "ne", "sw", "sw"), 2),
            Arguments.of(List.of("ne", "ne", "s", "s"), 2),
            Arguments.of(List.of("se", "sw", "se", "sw", "sw"), 3)
        );
    }

    @ParameterizedTest
    @MethodSource("provideExamplesPart1")
    void testExamplePart1(List<String> input, int expected) {
        Day11 day = Day11.fromDirections(input);
        assertEquals(expected, day.part1());
    }

    @Test
    void testSolutionPart1() {
        List<String> input = Utils.readLineAsStringList(inputFile, Day11Test.class);
        Day11 day = Day11.fromDirections(input);
        assertEquals(722, day.part1());
    }

    @ParameterizedTest
    @MethodSource("provideExamplesPart2")
    void testExamplePart2(List<String> input, int expected) {
        Day11 day = Day11.fromDirections(input);
        assertEquals(expected, day.part2());
    }

    @Test
    void testSolutionPart2() {
        List<String> input = Utils.readLineAsStringList(inputFile, Day11Test.class);
        Day11 day = Day11.fromDirections(input);
        assertEquals(1551, day.part2());
    }

}
