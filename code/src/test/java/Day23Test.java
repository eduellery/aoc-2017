import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.MethodName.class)
class Day23Test {

    private String inputFile = "day23.in";

    @Test
    void testSolution() {
        List<String> input = Utils.readLines(inputFile, Day23Test.class);
        Day23 day = new Day23(input);
        assertEquals(8281, day.part1(), "Part 1");
        assertEquals(911, day.part2(), "Part 2");
    }
}
