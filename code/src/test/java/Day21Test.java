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
class Day21Test {

    private String inputFile = "day21.in";

    @Test
    void testExample() {
        List<String> input = List.of("../.# => ##./#../...", ".#./..#/### => #..#/..../..../#..#");
        Day21 day = Day21.processInput(input, 2, 0);
        assertEquals(12, day.part1(), "Part 1");
        assertEquals(12, day.part2(), "Part 2");
    }

    @Test
    void testSolution() {
        List<String> input = Utils.readLines(inputFile, Day21Test.class);
        Day21 day = Day21.processInput(input, 5, 13);
        assertEquals(123, day.part1(), "Part 1");
        assertEquals(1984683, day.part2(), "Part 2");
    }

}
