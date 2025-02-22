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
class Day09Test {

    private String inputFile = "day09.in";

    private static Stream<Arguments> provideExamplesPart1() {
        return Stream.of(
            Arguments.of("{}", 1),
            Arguments.of("{{{}}}", 6),
            Arguments.of("{{},{}}", 5),
            Arguments.of("{{{},{},{{}}}}", 16),
            Arguments.of("{<a>,<a>,<a>,<a>}", 1),
            Arguments.of("{{<ab>},{<ab>},{<ab>},{<ab>}}", 9),
            Arguments.of("{{<!!>},{<!!>},{<!!>},{<!!>}}", 9),
            Arguments.of("{{<a!>},{<a!>},{<a!>},{<ab>}}", 3)
        );
    }

    private static Stream<Arguments> provideExamplesPart2() {
        return Stream.of(
            Arguments.of("<>", 0),
            Arguments.of("<random characters>", 17),
            Arguments.of("<<<<>", 3),
            Arguments.of("<{!>}>", 2),
            Arguments.of("<!!>", 0),
            Arguments.of("<!!!>>", 0),
            Arguments.of("<{o\"i!a,<{i<a>", 10)
        );
    }

    @ParameterizedTest
    @MethodSource("provideExamplesPart1")
    void testExamplePart1(String input, int expected) {
        Day09 day = Day09.fromValues(input);
        assertEquals(expected, day.part1());
    }

    @Test
    void testSolutionPart1() {
        String input = Utils.readLine(inputFile, Day09Test.class);
        Day09 day = Day09.fromValues(input);
        assertEquals(17390, day.part1());
    }

    @ParameterizedTest
    @MethodSource("provideExamplesPart2")
    void testExamplePart2(String input, int expected) {
        Day09 day = Day09.fromValues(input);
        assertEquals(expected, day.part2());
    }

    @Test
    void testSolutionPart2() {
        String input = Utils.readLine(inputFile, Day09Test.class);
        Day09 day = Day09.fromValues(input);
        assertEquals(7825, day.part2());
    }

}
