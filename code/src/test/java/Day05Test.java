import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.MethodName.class)
class Day05Test {

    private String fileName = "day05.in";

    @Test
    void testExample() {
        Day05 day = new Day05(List.of(0, 3, 0, 1, -3));
        assertEquals(5, day.part1(), "Part 1");
        assertEquals(10, day.part2(), "Part 2");
    }

    @Test
    void testSolution() {
        List<Integer> input = Utils.readLinesAsInt(fileName, Day05Test.class);
        Day05 day = new Day05(input);
        assertEquals(355965, day.part1(), "Part 1");
        assertEquals(26948068, day.part2(), "Part 2");
    }
}
