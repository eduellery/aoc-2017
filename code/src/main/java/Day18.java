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
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public record Day18(int part1, int part2) {

    public Day18(List<String> instructions) {
        this(solvePart1(instructions), solvePart2(instructions));
    }

    private static int solvePart1(final List<String> instructions) {
        Map<Character, BigInteger> registers = new HashMap<>();
        BigInteger lastSound = null;
        List<BigInteger> soundsPlayed = new ArrayList<>();

        for (int i = 0; i < instructions.size(); ) {
            String[] parts = instructions.get(i).split(" ");
            String cmd = parts[0];
            char reg = parts[1].charAt(0);

            registers.putIfAbsent(reg, BigInteger.ZERO);

            switch (cmd) {
                case "snd" -> soundsPlayed.add(getValue(parts[1], registers));
                case "set" -> registers.put(reg, getValue(parts[2], registers));
                case "add" -> registers.put(reg, registers.get(reg).add(getValue(parts[2], registers)));
                case "mul" -> registers.put(reg, registers.get(reg).multiply(getValue(parts[2], registers)));
                case "mod" -> registers.put(reg, registers.get(reg).mod(getValue(parts[2], registers)));
                case "rcv" -> {
                    if (registers.get(reg).compareTo(BigInteger.ZERO) > 0) {
                        lastSound = soundsPlayed.get(soundsPlayed.size() - 1);
                        return lastSound.intValue();
                    }
                }
                case "jgz" -> {
                    if (getValue(parts[1], registers).compareTo(BigInteger.ZERO) > 0) {
                        i += getValue(parts[2], registers).intValue();
                        continue;
                    }
                }
            }
            i++;
        }
        return lastSound.intValue();
    }

    private static int solvePart2(final List<String> instructions) {
        Program program0 = new Program(instructions, 0);
        Program program1 = new Program(instructions, 1);

        program0.setOtherProgram(program1);
        program1.setOtherProgram(program0);

        while (!(program0.isHalted() && program1.isHalted()) &&
               !(program0.isWaiting() && program1.isWaiting())) {
            while (!program0.isHalted() && !program0.isWaiting()) {
                program0.processInstructions();
            }
            while (!program1.isHalted() && !program1.isWaiting()) {
                program1.processInstructions();
            }
        }
        return program1.getMessagesSent();
    }

    private static BigInteger getValue(String location, Map<Character, BigInteger> registers) {
        return isRegister(location) ? registers.getOrDefault(location.charAt(0), BigInteger.ZERO) : new BigInteger(location);
    }

    private static boolean isRegister(String input) {
        return input.length() == 1 && Character.isLetter(input.charAt(0));
    }

    private static class Program {
        private final List<String> instructions;
        private final Deque<BigInteger> queue;
        private final Map<Character, BigInteger> registers;
        private int instructionPointer;
        private Program otherProgram;
        private int messagesSent;

        public Program(List<String> instructions, int id) {
            this.instructions = instructions;
            this.instructionPointer = 0;
            this.messagesSent = 0;
            this.queue = new ArrayDeque<>();
            this.registers = new HashMap<>();
            registers.put('p', BigInteger.valueOf(id));
        }

        public int getMessagesSent() {
            return messagesSent;
        }

        public void setOtherProgram(Program program) {
            this.otherProgram = program;
        }

        private void sendMessage(BigInteger value) {
            otherProgram.queue.addLast(value);
            messagesSent++;
        }

        private BigInteger pullFromQueue() {
            return queue.pollFirst();
        }

        public boolean isWaiting() {
            return queue.isEmpty() && instructions.get(instructionPointer).startsWith("rcv ");
        }

        public boolean isHalted() {
            return instructionPointer < 0 || instructionPointer >= instructions.size();
        }

        public void processInstructions() {
            String[] parts = instructions.get(instructionPointer).split(" ");
            String cmd = parts[0];
            char reg = parts[1].charAt(0);

            registers.putIfAbsent(reg, BigInteger.ZERO);

            switch (cmd) {
                case "snd" -> sendMessage(getValue(parts[1], registers));
                case "set" -> registers.put(reg, getValue(parts[2], registers));
                case "add" -> registers.put(reg, registers.get(reg).add(getValue(parts[2], registers)));
                case "mul" -> registers.put(reg, registers.get(reg).multiply(getValue(parts[2], registers)));
                case "mod" -> registers.put(reg, registers.get(reg).mod(getValue(parts[2], registers)));
                case "rcv" -> registers.put(reg, pullFromQueue());
                case "jgz" -> {
                    if (getValue(parts[1], registers).compareTo(BigInteger.ZERO) > 0) {
                        instructionPointer += getValue(parts[2], registers).intValue();
                        return;
                    }
                }
            }
            instructionPointer++;
        }
    }
}
