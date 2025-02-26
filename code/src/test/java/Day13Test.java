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
class Day13Test {

    private String exampleFile = "day13.example";
    private String inputFile = "day13.in";

    @Test
    void testExample() {
        List<String> input = Utils.readLines(exampleFile, Day13Test.class);
        Day13 day = new Day13(input);
        assertEquals(24, day.part1(), "Part 1");
        assertEquals(10, day.part2(), "Part 2");
    }

    @Test
    void testSolution() {
        List<String> input = Utils.readLines(inputFile, Day13Test.class);
        Day13 day = new Day13(input);
        assertEquals(1904, day.part1(), "Part 1");
        assertEquals(3833504, day.part2(), "Part 2");
    }

}
