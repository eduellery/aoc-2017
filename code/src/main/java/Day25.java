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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public record Day25(long part1, long part2) {

    private enum State {A, B, C, D, E, F};

    private static final Pattern INIT_STATE_PATTERN = Pattern.compile("Begin in state ([A-Z]).");
    private static final Pattern STEP_PATTERN = Pattern.compile("Perform a diagnostic checksum after (\\d+) steps.");
    private static final Pattern STATE_PATTERN = Pattern.compile(
        "In state (\\w+):\\s+" +
        "If the current value is 0:\\s+" +
        "- Write the value (\\d+).\\s+" +
        "- Move one slot to the (left|right).\\s+" +
        "- Continue with state (\\w+).\\s+" +
        "If the current value is 1:\\s+" +
        "- Write the value (\\d+).\\s+" +
        "- Move one slot to the (left|right).\\s+" +
        "- Continue with state (\\w+)."
    );

    public static Day25 checksum(String text) {
        HashSet<Integer> tape = new HashSet<>();
        ParsedData parsedData = parse(text);
        State state = parsedData.initialState;
        int pos = 0;

        for (int i = 0; i < parsedData.steps(); i++) {
            int val = readVal(tape, pos);
            Transition transition = parsedData.transitions().get(state + "," + val);
            writeVal(tape, transition.writeValue, pos);
            pos += transition.direction;
            state = transition.nextState;
        }

        return new Day25(tape.size(), 0);
    }

    private static ParsedData parse(String text) {
        State initialState = null;
        int steps = 0;
        Map<String, Transition> transitions = new HashMap<>();

        Matcher initStateMatcher = INIT_STATE_PATTERN.matcher(text);
        if (initStateMatcher.find()) {
            initialState = State.valueOf(initStateMatcher.group(1));
        }

        Matcher stepMatcher = STEP_PATTERN.matcher(text);
        if (stepMatcher.find()) {
            steps = Integer.parseInt(stepMatcher.group(1));
        }

        Matcher matcher = STATE_PATTERN.matcher(text);
        while (matcher.find()) {
            State state = State.valueOf(matcher.group(1));
            int writeValue0 = Integer.parseInt(matcher.group(2));
            int direction0 = matcher.group(3).equalsIgnoreCase("right") ? 1 : -1;
            State nextState0 = State.valueOf(matcher.group(4));
            int writeValue1 = Integer.parseInt(matcher.group(5));
            int direction1 = matcher.group(6).equalsIgnoreCase("right") ? 1 : -1;
            State nextState1 = State.valueOf(matcher.group(7));
            transitions.put(state + ",0", new Transition(writeValue0, direction0, nextState0));
            transitions.put(state + ",1", new Transition(writeValue1, direction1, nextState1));
        }

        return new ParsedData(initialState, steps, transitions);
    }

    private record Transition(int writeValue, int direction, State nextState) {}

    private record ParsedData(State initialState, int steps, Map<String, Transition> transitions) {}

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
