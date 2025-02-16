import java.util.stream.Stream;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class Day03Test {

    private String fileName = "day03.in";

    private static Stream<Arguments> provideExamples() {
        return Stream.of(
            Arguments.of(1, 0),
            Arguments.of(12, 3),
            Arguments.of(23, 2),
            Arguments.of(1024, 31)
        );
    }

    @ParameterizedTest
    @MethodSource("provideExamples")
    void testExamplePart1(int input, int expected) {
        Day03 day = new Day03(input);
        assertEquals(expected, day.part1());
    }

    @Test
    void testSolutionPart1() {
        int input = Utils.readLineAsInt(fileName, Day03Test.class);
        Day03 day = new Day03(input);
        assertEquals(552, day.part1());
    }

    @Test
    void testSolutionPart2() {
        int input = Utils.readLineAsInt(fileName, Day03Test.class);
        Day03 day = new Day03(input);
        assertEquals(330785, day.part2());
    }

}
