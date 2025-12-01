import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.MethodName.class)
class Day07Test {

    private String exampleFile = "day07.example";
    private String inputFile = "day07.in";

    @Test
    void testExample() {
        List<String> input = Utils.readLines(exampleFile, Day07Test.class);
        Day07 day = Day07.fromValues(input);
        assertEquals("tknk", day.part1(), "Part 1");
        assertEquals(60, day.part2(), "Part 2");
    }

    @Test
    void testSolution() {
        List<String> input = Utils.readLines(inputFile, Day07Test.class);
        Day07 day = Day07.fromValues(input);
        assertEquals("wiapj", day.part1(), "Part 1");
        assertEquals(1072, day.part2(), "Part 2");
    }
}
