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
class Day08Test {

    private String exampleFile = "day08.example";
    private String inputFile = "day08.in";

    @Test
    void testExamplePart1() {
        List<String> input = Utils.readLines(exampleFile, Day08Test.class);
        Day08 day = Day08.fromValues(input);
        assertEquals(1, day.part1());
    }

    @Test
    void testSolutionPart1() {
        List<String> input = Utils.readLines(inputFile, Day08Test.class);
        Day08 day = Day08.fromValues(input);
        assertEquals(5752, day.part1());
    }

    @Test
    void testExamplePart2() {
        List<String> input = Utils.readLines(exampleFile, Day08Test.class);
        Day08 day = Day08.fromValues(input);
        assertEquals(10, day.part2());
    }

    @Test
    void testSolutionPart2() {
        List<String> input = Utils.readLines(inputFile, Day08Test.class);
        Day08 day = Day08.fromValues(input);
        assertEquals(6366, day.part2());
    }

}
