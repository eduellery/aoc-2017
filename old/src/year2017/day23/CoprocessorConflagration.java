package year2017.day23;

import utils.FileUtils;
import common.Command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoprocessorConflagration {

    public static void main(String[] args) {
        List<Command> commands = getCommands(FileUtils.getLines("2017day23.txt", CoprocessorConflagration.class));
        Map<String, Integer> registers = new HashMap<>();
        System.out.println(commands);
        int value = processCommands(registers, commands);
        System.out.println("Value:" + value);
//        commands = getCommands(FileUtils.getLines("2017day18.txt",CoprocessorConflagration.class));
//        registers = new HashMap<>();
//        System.out.println(commands);
//        value = processCommands(registers, commands);
//        System.out.println(value);
    }

    private static List<Command> getCommands(List<String> lines) {
        List<Command> commands = new ArrayList<>();
        for (String line : lines) {
            String[] values = line.split("\\s");
            Command command = new Command(values[0], values[1], values.length == 3 ? values[2] : null);
            commands.add(command);
        }
        return commands;
    }

    private static int processCommands(Map<String, Integer> registers, List<Command> commands) {
        int mul = 0;
        for (int i = 0; i < commands.size(); i++) {
            Command command = commands.get(i);
            String register = command.getRegister();
            if (!registers.containsKey(register)) {
                registers.put(register, 0);
            }
            String instruction = command.getInstruction();
            Integer value;
            switch (instruction) {
                case "set":
                    value = registers.containsKey(command.getValue()) ? registers.get(command.getValue()) : Integer.valueOf(command.getValue());
                    registers.put(register, value);
                    break;
                case "add":
                    value = registers.containsKey(command.getValue()) ? registers.get(command.getValue()) : Integer.valueOf(command.getValue());
                    value += registers.get(register);
                    registers.put(register, value);
                    break;
                case "sub":
                    value = registers.containsKey(command.getValue()) ? registers.get(command.getValue()) : Integer.valueOf(command.getValue());
                    value -= registers.get(register);
                    registers.put(register, value);
                    break;
                case "mul":
                    mul++;
                    System.out.println(mul);
                    value = registers.containsKey(command.getValue()) ? registers.get(command.getValue()) : Integer.valueOf(command.getValue());
                    value *= registers.get(register);
                    registers.put(register, value);
                    break;
                case "jnz":
                    value = registers.containsKey(command.getValue()) ? registers.get(command.getValue()) : Integer.valueOf(command.getValue());
                    if (value != 0) {
                        i += value;
                        i--;
                    }
                    break;
                default:
                    throw new RuntimeException("Unknown instruction " + instruction);
            }
        }
        return 0;
    }
}
