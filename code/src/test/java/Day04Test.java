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
class Day04Test {

    private String fileName = "day04.in";

    @Test
    void testExamplePart1() {
        Day04 day = new Day04(List.of("aa bb cc dd ee", "aa bb cc dd aa", "aa bb cc dd aaa"));
        assertEquals(2, day.part1());
    }

    @Test
    void testSolutionPart1() {
        List<String> input = Utils.readLines(fileName, Day04Test.class);
        Day04 day = new Day04(input);
        assertEquals(455, day.part1());
    }

    @Test
    void testExamplePart2() {
        Day04 day = new Day04(List.of("abcde fghij", "abcde xyz ecdab", "a ab abc abd abf abj", "iiii oiii ooii oooi oooo", "oiii ioii iioi iiio"));
        assertEquals(3, day.part2());
    }

    @Test
    void testSolutionPart2() {
        List<String> input = Utils.readLines(fileName, Day04Test.class);
        Day04 day = new Day04(input);
        assertEquals(186, day.part2());
    }

}
