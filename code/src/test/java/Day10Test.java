import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.MethodName.class)
class Day10Test {

    private String inputFile = "day10.in";

    @Test
    void testExample() {
        Day10 day = Day10.fromValues("3,4,1,5", 5);
        assertEquals(12, day.part1(), "Part 1");
        assertEquals("", day.part2(), "Part 2");
    }

    @Test
    void testSolution() {
        String input = Utils.readLine(inputFile, Day10Test.class);
        Day10 day = Day10.fromValues(input, 256);
        assertEquals(11413, day.part1(), "Part 1");
        assertEquals("7adfd64c2a03a4968cf708d1b7fd418d", day.part2(), "Part 2");
    }
}
