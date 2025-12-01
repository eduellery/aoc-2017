import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.MethodName.class)
class Day15Test {

    private String inputFile = "day15.in";

    @Test
    void testExample() {
        Day15 day = Day15.fromSeeds(65, 8921);
        assertEquals(588, day.part1(), "Part 1");
        assertEquals(309, day.part2(), "Part 2");
    }

    @Test
    void testSolution() {
        List<String> input = Utils.readLines(inputFile, Day15Test.class);
        int valueA = Integer.parseInt(input.get(0).split("with ")[1]);
        int valueB = Integer.parseInt(input.get(1).split("with ")[1]);
        Day15 day = Day15.fromSeeds(valueA, valueB);
        assertEquals(320, day.part2(), "Part 1");
        assertEquals(592, day.part1(), "Part 2");
    }
}
