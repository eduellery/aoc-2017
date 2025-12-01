import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.MethodName.class)
class Day04Test {

    private String fileName = "day04.in";

    @Test
    void testExamplePart1() {
        Day04 day = new Day04(List.of("aa bb cc dd ee", "aa bb cc dd aa", "aa bb cc dd aaa"));
        assertEquals(2, day.part1());
    }

    @Test
    void testExamplePart2() {
        Day04 day =
                new Day04(
                        List.of(
                                "abcde fghij",
                                "abcde xyz ecdab",
                                "a ab abc abd abf abj",
                                "iiii oiii ooii oooi oooo",
                                "oiii ioii iioi iiio"));
        assertEquals(3, day.part2());
    }

    @Test
    void testSolution() {
        List<String> input = Utils.readLines(fileName, Day04Test.class);
        Day04 day = new Day04(input);
        assertEquals(455, day.part1(), "Part 1");
        assertEquals(186, day.part2(), "Part 2");
    }
}
