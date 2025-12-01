import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

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
