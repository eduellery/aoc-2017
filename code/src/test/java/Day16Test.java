import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.MethodName.class)
class Day16Test {

    private String inputFile = "day16.in";

    @Test
    void testExample() {
        List<String> instructions = List.of("s1", "x3/4", "pe/b");
        Day16 day = Day16.fromInstructions("abcde", instructions);
        assertEquals("baedc", day.part1(), "Part 1");
        assertEquals("abcde", day.part2(), "Part 2");
    }

    @Test
    void testSolution() {
        List<String> instructions = Utils.readLineAsStringList(inputFile, Day16Test.class);
        Day16 day = Day16.fromInstructions("abcdefghijklmnop", instructions);
        assertEquals("kbednhopmfcjilag", day.part1(), "Part 1");
        assertEquals("fbmcgdnjakpioelh", day.part2(), "Part 2");
    }
}
