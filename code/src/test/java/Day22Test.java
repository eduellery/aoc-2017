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
class Day22Test {

    private String inputFile = "day22.in";

    private static Stream<Arguments> provideExamplesPart1() {
        return Stream.of(
                Arguments.of(List.of("..#", "#..", "..."), 7, 5),
                Arguments.of(List.of("..#", "#..", "..."), 70, 41),
                Arguments.of(List.of("..#", "#..", "..."), 10000, 5587));
    }

    private static Stream<Arguments> provideExamplesPart2() {
        return Stream.of(
                Arguments.of(List.of("..#", "#..", "..."), 100, 26),
                Arguments.of(List.of("..#", "#..", "..."), 10000000, 2511944));
    }

    @ParameterizedTest
    @MethodSource("provideExamplesPart1")
    void testExamplePart1(List<String> input, int rounds, int expected) {
        Day22 day = Day22.processInput(input, rounds, false);
        assertEquals(expected, day.infected());
    }

    @ParameterizedTest
    @MethodSource("provideExamplesPart2")
    void testExamplePart2(List<String> input, int rounds, int expected) {
        Day22 day = Day22.processInput(input, rounds, true);
        assertEquals(expected, day.infected());
    }

    @Test
    void testSolution() {
        List<String> input = Utils.readLines(inputFile, Day22Test.class);
        Day22 easyVirus = Day22.processInput(input, 10000, false);
        Day22 hardVirus = Day22.processInput(input, 10000000, true);
        assertEquals(5348, easyVirus.infected(), "Part 1");
        assertEquals(2512225, hardVirus.infected(), "Part 2");
    }
}
