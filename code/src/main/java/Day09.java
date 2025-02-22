import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntBiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record Day09(int part1, int part2) {

    enum State {
        OPEN, CLOSE, GARBAGE, IGNORE, OTHER;

        private static final Map<State, Map<Character, State>> TRANSITIONS = Map.of(
            OTHER, Map.of('{', OPEN, '}', CLOSE, '<', GARBAGE),
            OPEN, Map.of('}', CLOSE, '<', GARBAGE, ',', OTHER),
            CLOSE, Map.of('{', OPEN, '<', GARBAGE, ',', OTHER),
            GARBAGE, Map.of('!', IGNORE, '>', OTHER),
            IGNORE, Map.of('!', GARBAGE)
        );

        public State getTransition(char c) {
            return TRANSITIONS.getOrDefault(this, Map.of()).getOrDefault(c, this);
        }
    }

    public static Day09 fromValues(String input) {
        var machine = new Machine();

        for (char c : input.toCharArray()) {
            State current = machine.state();
            if (current == State.IGNORE) c = '!';

            State next = current.getTransition(c);
            switch (next) {
                case OPEN -> machine.incLevel();
                case CLOSE -> machine.addScore(machine.decLevel());
                case GARBAGE -> { if (current == State.GARBAGE) machine.incGarbage(); }
                default -> {}
            }
            machine.setState(next);
        }

        return new Day09(machine.score(), machine.garbage());
    }

    private static class Machine {
        private State state = State.OTHER;
        private int level = 0;
        private int score = 0;
        private int garbage = 0;

        public State state() { return state; }
        public void setState(State state) { this.state = state; }
        public void incLevel() { level++; }
        public int decLevel() { return level--; }
        public void addScore(int value) { score += value; }
        public int score() { return score; }
        public void incGarbage() { garbage++; }
        public int garbage() { return garbage; }
    }
}
