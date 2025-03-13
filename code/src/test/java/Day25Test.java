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
class Day25Test {

    private String exampleFile = "day25.example";
    private String inputFile = "day25.in";

    @Test
    void testExample() {
        List<String> input = Utils.readLines(exampleFile, Day25Test.class);
        Day25 day = Day25.checksum(true);
        assertEquals(3, day.part1(), "Part 1");
        assertEquals(0, day.part2(), "Part 2");
    }

    @Test
    void testSolution() {
        List<String> input = Utils.readLines(inputFile, Day25Test.class);
        Day25 day = Day25.checksum(false);
        assertEquals(3145, day.part1(), "Part 1");
        assertEquals(0, day.part2(), "Part 2");
    }

}
