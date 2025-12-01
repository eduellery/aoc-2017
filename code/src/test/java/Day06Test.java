import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.MethodName.class)
class Day06Test {

    private String fileName = "day06.in";

    @Test
    void testExample() {
        Day06 day = Day06.fromValues(List.of(0, 2, 7, 0));
        assertEquals(5, day.part1(), "Part 1");
        assertEquals(4, day.part2(), "Part 2");
    }

    @Test
    void testSolution() {
        List<Integer> input = Utils.readLineAsIntList(fileName, Day06Test.class);
        Day06 day = Day06.fromValues(input);
        assertEquals(12841, day.part1(), "Part 1");
        assertEquals(8038, day.part2(), "Part 2");
    }
}
