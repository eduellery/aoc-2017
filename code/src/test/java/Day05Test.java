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
class Day05Test {

    private String fileName = "day05.in";

    @Test
    void testExamplePart1() {
        Day05 day = new Day05(List.of(0, 3, 0, 1, -3));
        assertEquals(5, day.part1());
    }

    @Test
    void testSolutionPart1() {
        List<Integer> input = Utils.readLinesAsInt(fileName, Day05Test.class);
        Day05 day = new Day05(input);
        assertEquals(355965, day.part1());
    }

    @Test
    void testExamplePart2() {
        Day05 day = new Day05(List.of(0, 3, 0, 1, -3));
        assertEquals(10, day.part2());
    }

    @Test
    void testSolutionPart2() {
        List<Integer> input = Utils.readLinesAsInt(fileName, Day05Test.class);
        Day05 day = new Day05(input);
        assertEquals(26948068, day.part2());
    }

}
