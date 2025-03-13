import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.IntSummaryStatistics;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntBiFunction;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public record Day25(long part1, long part2) {

    private enum State {A, B, C, D, E, F};

    public static Day25 checksum(boolean test) {
        int steps = test ? 6 : 12964419;
        HashSet<Integer> tape = new HashSet<>();
        State state = State.A;
        int pos = 0;

        for (int i = 0; i < steps; i++) {
            int val = readVal(tape, pos);
            switch (state) {
                case A:
                    if (val == 0) {
                        writeVal(tape, 1, pos);
                        pos++;
                        state = State.B;
                    } else {
                        writeVal(tape, 0, pos);
                        if (test) pos--; else pos++;
                        state = test ? State.B : State.F;
                    }
                    break;
                case B:
                    if (val == 0) {
                        writeVal(tape, test ? 1 : 0, pos);
                        pos--;
                        state = test ? State.A : State.B;
                    } else {
                        writeVal(tape, 1, pos);
                        if (test) pos++; else pos--;
                        state = test ? State.A : State.C;
                    }
                    break;
                case C:
                    if (val == 0) {
                        writeVal(tape, 1, pos);
                        pos--;
                        state = State.D;
                    } else {
                        writeVal(tape, 0, pos);
                        pos++;
                        state = State.C;
                    }
                    break;
                case D:
                    if (val == 0) {
                        writeVal(tape, 1, pos);
                        pos--;
                        state = State.E;
                    } else {
                        writeVal(tape, 1, pos);
                        pos++;
                        state = State.A;
                    }
                    break;
                case E:
                    if (val == 0) {
                        writeVal(tape, 1, pos);
                        pos--;
                        state = State.F;
                    } else {
                        writeVal(tape, 0, pos);
                        pos--;
                        state = State.D;
                    }
                    break;
                case F:
                    if (val == 0) {
                        writeVal(tape, 1, pos);
                        pos++;
                        state = State.A;
                    } else {
                        writeVal(tape, 0, pos);
                        pos--;
                        state = State.E;
                    }
                    break;
            }
        }

        return new Day25(tape.size(), 0);
    }

    private static int readVal(HashSet<Integer> tape, int pos) {
            return tape.contains(pos) ? 1 : 0;
        }

    private static void writeVal(HashSet<Integer> tape, int val, int pos) {
        if (val == 1) {
            tape.add(pos);
        } else {
            tape.remove(pos);
        }
    }
}
