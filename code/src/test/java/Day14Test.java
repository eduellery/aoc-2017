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
class Day14Test {

    private String inputFile = "day14.in";

    @Test
    void testExample() {
        Day14 day = Day14.fromInput("flqrgnkx");
        assertEquals(8108, day.part1(), "Part 1");
        assertEquals(1242, day.part2(), "Part 2");
    }

    @Test
    void testSolution() {
        String input = Utils.readLine(inputFile, Day14Test.class);
        Day14 day = Day14.fromInput(input);
        assertEquals(8316, day.part1(), "Part 1");
        assertEquals(1074, day.part2(), "Part 2");
    }

}
