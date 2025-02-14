import java.util.stream.Stream;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class Day01Test {

    private String fileName = "day01.in";

    private static Stream<Arguments> provideExamplesPart1() {
        return Stream.of(
            Arguments.of("1122", 3),
            Arguments.of("1111", 4),
            Arguments.of("1234", 0),
            Arguments.of("91212129", 9)
        );
    }

    private static Stream<Arguments> provideExamplesPart2() {
        return Stream.of(
            Arguments.of("1212", 6),
            Arguments.of("1221", 0),
            Arguments.of("123425", 4),
            Arguments.of("123123", 12),
            Arguments.of("12131415", 4)
        );
    }

    @ParameterizedTest
    @MethodSource("provideExamplesPart1")
    void testExamplePart1(String input, int expected) {
        Day01 day = new Day01(input);
        assertEquals(expected, day.part1());
    }

    @Test
    void testSolutionPart1() {
        String input = Utils.readLine(fileName, Day01Test.class);
        Day01 day = new Day01(input);
        assertEquals(1343, day.part1());
    }

    @ParameterizedTest
    @MethodSource("provideExamplesPart2")
    void testExamplePart2(String input, int expected) {
        Day01 day = new Day01(input);
        assertEquals(expected, day.part2());
    }

    @Test
    void testSolutionPart2() {
        String input = Utils.readLine(fileName, Day01Test.class);
        Day01 day = new Day01(input);
        assertEquals(1274, day.part2());
    }

}
