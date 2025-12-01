import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.MethodName.class)
class Day24Test {

    private String inputFile = "day24.in";

    @Test
    void testExample() {
        List<String> input = List.of("0/2", "2/2", "2/3", "3/4", "3/5", "0/1", "10/1", "9/10");
        Day24 day = Day24.build(input);
        assertEquals(31, day.part1(), "Part 1");
        assertEquals(19, day.part2(), "Part 2");
    }

    @Test
    void testSolution() {
        List<String> input = Utils.readLines(inputFile, Day24Test.class);
        Day24 day = Day24.build(input);
        assertEquals(1868, day.part1(), "Part 1");
        assertEquals(1841, day.part2(), "Part 2");
    }
}
