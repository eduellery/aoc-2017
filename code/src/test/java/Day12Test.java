import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.MethodName.class)
class Day12Test {

    private String exampleFile = "day12.example";
    private String inputFile = "day12.in";

    @Test
    void testExample() {
        List<String> input = Utils.readLines(exampleFile, Day12Test.class);
        Day12 day = Day12.fromLines(input);
        assertEquals(6, day.part1(), "Part 1");
        assertEquals(2, day.part2(), "Part 2");
    }

    @Test
    void testSolution() {
        List<String> input = Utils.readLines(inputFile, Day12Test.class);
        Day12 day = Day12.fromLines(input);
        assertEquals(130, day.part1(), "Part 1");
        assertEquals(189, day.part2(), "Part 2");
    }
}
