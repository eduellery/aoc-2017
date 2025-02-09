package year2017.day08;

import utils.CollectionUtils;
import utils.FileUtils;

import java.util.*;

public class LikeRegisters {

    public static void main(String[] args) {
        List<Instruction> testInstructions = getInstructions("test08.txt");
        System.out.println(testInstructions);
        HashMap<String, Integer> testResultSet = getResultSet(testInstructions);
        System.out.println(testResultSet);
        System.out.println(CollectionUtils.getMaxValue(testResultSet.values()));

        List<Instruction> instructions = getInstructions("input08.txt");
        System.out.println(instructions);
        HashMap<String, Integer> resultSet = getResultSet(instructions);
        System.out.println(resultSet);
        System.out.println(CollectionUtils.getMaxValue(resultSet.values()));
    }

    private static HashMap<String, Integer> getResultSet(List<Instruction> instructions) {
        HashMap<String, Integer> result = new HashMap<String, Integer>();
        for (Instruction instruction : instructions) {
            result.put(instruction.getRegister(), 0);
        }
        processSet(result, instructions);
        return result;
    }

    private static void processSet(HashMap<String, Integer> set, List<Instruction> instructions) {
        for (Instruction instruction : instructions) {
            if (evaluateExpression(set, instruction.getTestRegister(), instruction.getOperator(), instruction.getTestValue())) {
                modifySet(set, instruction.getRegister(), instruction.isIncrement(), instruction.getValue());
            }
        }
    }

    private static void modifySet(HashMap<String, Integer> set, String register, boolean increment, int incrementValue) {
        int value = set.get(register);
        value = increment ? value + incrementValue : value - incrementValue;
        set.put(register, value);
    }

    private static boolean evaluateExpression(HashMap<String, Integer> set, String testRegister, Instruction.Operator operator, int testValue) {
        Integer value = set.get(testRegister);
        switch (operator) {
            case EQ:
                return value == testValue;
            case NEQ:
                return value == testValue;
            case G:
                return value > testValue;
            case L:
                return value < testValue;
            case GE:
                return value >= testValue;
            case LE:
                return value <= testValue;
            default:
                throw new RuntimeException("Unknown operator " + operator);
        }
    }

    private static List<Instruction> getInstructions(String fileName) {
        List<String> lines = FileUtils.getLines(fileName, LikeRegisters.class);
        List<Instruction> instructions = new ArrayList<Instruction>();
        for (String line : lines) {
            Instruction instruction = getInstruction(line);
            instructions.add(instruction);
        }
        return instructions;
    }

    private static Instruction getInstruction(String line) {
        String[] valueArray = line.split("\\s");
        String register = valueArray[0];
        boolean increment = valueArray[1].equalsIgnoreCase("inc");
        int value = Integer.valueOf(valueArray[2]);
        String testRegister = valueArray[4];
        String operator = valueArray[5];
        int testValue = Integer.valueOf(valueArray[6]);
        Instruction instruction = new Instruction(register, increment, value, testRegister, operator, testValue);
        return instruction;
    }
}
