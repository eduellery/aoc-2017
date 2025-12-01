import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.MethodName.class)
class Day19Test {

    private String exampleFile = "day19.example";
    private String inputFile = "day19.in";

    @Test
    void testExample() {
        List<String> diagram = Utils.readLines(exampleFile, Day19Test.class);
        Day19 day = Day19.traverseDiagram(diagram);
        assertEquals("ABCDEF", day.part1(), "Part 1");
        assertEquals(38, day.part2(), "Part 2");
    }

    @Test
    void testSolution() {
        List<String> diagram = Utils.readLines(inputFile, Day19Test.class);
        Day19 day = Day19.traverseDiagram(diagram);
        assertEquals("XYFDJNRCQA", day.part1(), "Part 1");
        assertEquals(17450, day.part2(), "Part 2");
    }
}
