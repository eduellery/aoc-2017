import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.MethodName.class)
class Day18Test {

    private String inputFile = "day18.in";

    @Test
    void testExample() {
        List<String> instructions =
                List.of(
                        "set a 1",
                        "add a 2",
                        "mul a a",
                        "mod a 5",
                        "snd a",
                        "set a 0",
                        "rcv a",
                        "jgz a -1",
                        "set a 1",
                        "jgz a -2");
        Day18 day = new Day18(instructions);
        assertEquals(4, day.part1());
    }

    @Test
    void testSolution() {
        List<String> instructions = Utils.readLines(inputFile, Day18Test.class);
        Day18 day = new Day18(instructions);
        assertEquals(3188, day.part1(), "Part 1");
        assertEquals(7112, day.part2(), "Part 2");
    }
}
