import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@TestMethodOrder(MethodOrderer.MethodName.class)
class Day03Test {

    private String fileName = "day03.in";

    private static Stream<Arguments> provideExamples() {
        return Stream.of(
                Arguments.of(1, 0),
                Arguments.of(12, 3),
                Arguments.of(23, 2),
                Arguments.of(1024, 31));
    }

    @ParameterizedTest
    @MethodSource("provideExamples")
    void testExamples(int input, int expected) {
        Day03 day = new Day03(input);
        assertEquals(expected, day.part1());
    }

    @Test
    void testSolution() {
        int input = Utils.readLineAsInt(fileName, Day03Test.class);
        Day03 day = new Day03(input);
        assertEquals(552, day.part1(), "Part 1");
        assertEquals(330785, day.part2(), "Part 2");
    }
}
