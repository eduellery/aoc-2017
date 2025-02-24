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
class Day07Test {

    private String exampleFile = "day07.example";
    private String inputFile = "day07.in";

    @Test
    void testExample() {
        List<String> input = Utils.readLines(exampleFile, Day07Test.class);
        Day07 day = Day07.fromValues(input);
        assertEquals("tknk", day.part1());
        assertEquals(60, day.part2());
    }

    @Test
    void testSolution() {
        List<String> input = Utils.readLines(inputFile, Day07Test.class);
        Day07 day = Day07.fromValues(input);
        assertEquals("wiapj", day.part1());
        assertEquals(1072, day.part2());
    }

}
