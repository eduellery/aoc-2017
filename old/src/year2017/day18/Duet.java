package year2017.day18;

import common.Command;
import utils.FileUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Duet {

    public static void main(String[] args) {
        List<Command> commands = getCommands(FileUtils.getLines("2017day18test.txt", Duet.class));
        Map<String, Integer> registers = new HashMap<>();
        System.out.println(commands);
        int value = processCommands(registers, commands);
        System.out.println(value);
        commands = getCommands(FileUtils.getLines("2017day18.txt", Duet.class));
        registers = new HashMap<>();
        System.out.println(commands);
        value = processCommands(registers, commands);
        System.out.println(value);
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
        Integer snd = -1;
        for (int i = 0; i < commands.size(); i++) {
            Command command = commands.get(i);
            String register = command.getRegister();
            if (!registers.containsKey(register)) {
                registers.put(register, 0);
            }
            String instruction = command.getInstruction();
            Integer value = 0;
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
                case "mul":
                    value = registers.containsKey(command.getValue()) ? registers.get(command.getValue()) : Integer.valueOf(command.getValue());
                    value *= registers.get(register);
                    registers.put(register, value);
                    break;
                case "mod":
                    value = registers.containsKey(command.getValue()) ? registers.get(command.getValue()) : Integer.valueOf(command.getValue());
                    value = registers.get(register) % value;
                    registers.put(register, value);
                    break;
                case "snd":
                    snd = registers.get(register);
                    break;
                case "rcv":
                    if (registers.get(register) <= 0) {
                        continue;
                    } else {
                        return snd;
                    }
                case "jgz":
                    if (registers.get(register) <= 0) {
                        continue;
                    } else {
                        value = Integer.valueOf(command.getValue());
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
