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
class Day17Test {

    private String inputFile = "day17.in";

    @Test
    void testExample() {
        Day17 day = new Day17(3);
        assertEquals(638, day.part1(), "Part 1");
        assertEquals(1222153, day.part2(), "Part 2");
    }

    @Test
    void testSolution() {
        int size = Utils.readLineAsInt(inputFile, Day17Test.class);
        Day17 day = new Day17(size);
        assertEquals(180, day.part1(), "Part 1");
        assertEquals(13326437, day.part2(), "Part 2");
    }

}
