import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Day01Test {

    @Test void testPart1a() {
        Day01 day = new Day01("1122");
        assertEquals(3, day.solvePart1());
    }

    @Test void testPart1b() {
        Day01 day = new Day01("1111");
        assertEquals(4, day.solvePart1());
    }

    @Test void testPart1c() {
        Day01 day = new Day01("1234");
        assertEquals(0, day.solvePart1());
    }

    @Test void testPart1d() {
        Day01 day = new Day01("91212129");
        assertEquals(9, day.solvePart1());
    }

}
