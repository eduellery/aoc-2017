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
class Day10Test {

    private String inputFile = "day10.in";

    @Test
    void testExample() {
        Day10 day = Day10.fromValues("3,4,1,5", 5);
        assertEquals(12, day.part1());
        assertEquals("", day.part2());
    }

    @Test
    void testSolution() {
        String input = Utils.readLine(inputFile, Day10Test.class);
        Day10 day = Day10.fromValues(input, 256);
        assertEquals(11413, day.part1());
        assertEquals("7adfd64c2a03a4968cf708d1b7fd418d", day.part2());
    }

}
