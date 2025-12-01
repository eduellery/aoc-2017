import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.MethodName.class)
class Day08Test {

    private String exampleFile = "day08.example";
    private String inputFile = "day08.in";

    @Test
    void testExample() {
        List<String> input = Utils.readLines(exampleFile, Day08Test.class);
        Day08 day = Day08.fromValues(input);
        assertEquals(1, day.part1(), "Part 1");
        assertEquals(10, day.part2(), "Part 2");
    }

    @Test
    void testSolution() {
        List<String> input = Utils.readLines(inputFile, Day08Test.class);
        Day08 day = Day08.fromValues(input);
        assertEquals(5752, day.part1(), "Part 1");
        assertEquals(6366, day.part2(), "Part 2");
    }
}
