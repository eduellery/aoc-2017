import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.MethodName.class)
class Day20Test {

    private String exampleFilePart1 = "day20.part1.example";
    private String exampleFilePart2 = "day20.part2.example";
    private String inputFile = "day20.in";

    @Test
    void testExamplePart1() {
        List<String> input = Utils.readLines(exampleFilePart1, Day20Test.class);
        Day20 day = Day20.processInput(input);
        assertEquals(0, day.part1(), "Part 1");
    }

    @Test
    void testExamplePart2() {
        List<String> input = Utils.readLines(exampleFilePart2, Day20Test.class);
        Day20 day = Day20.processInput(input);
        assertEquals(1, day.part2(), "Part 2");
    }

    @Test
    void testSolution() {
        List<String> input = Utils.readLines(inputFile, Day20Test.class);
        Day20 day = Day20.processInput(input);
        assertEquals(91, day.part1(), "Part 1");
        assertEquals(567, day.part2(), "Part 2");
    }

}
