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
class Day02Test {

    private String examplePart1File = "day02.part1.example";
    private String examplePart2File = "day02.part2.example";
    private String inputFile = "day02.in";

    @Test
    void testExamplePart1() {
        List<String> lines = Utils.readLines(examplePart1File, Day02Test.class);
        Day02 day = Day02.fromLines(lines, true, false);
        assertEquals(18, day.part1());
    }

    @Test
    void testSolutionPart1() {
        List<String> lines = Utils.readLines(inputFile, Day02Test.class);
        Day02 day = Day02.fromLines(lines, true, false);
        assertEquals(53978, day.part1());
    }

    @Test
    void testExamplePart2() {
        List<String> lines = Utils.readLines(examplePart2File, Day02Test.class);
        Day02 day = Day02.fromLines(lines, false, true);
        assertEquals(9, day.part2());
    }

    @Test
    void testSolutionPart2() {
        List<String> lines = Utils.readLines(inputFile, Day02Test.class);
        Day02 day = Day02.fromLines(lines, false, true);
        assertEquals(314, day.part2());
    }

}
