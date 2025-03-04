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
