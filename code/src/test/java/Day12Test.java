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
