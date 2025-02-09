package common;

public class Command {

    private String instruction;
    private String register;
    private String value;

    public Command(String instruction, String register, String value) {
        this.instruction = instruction;
        this.register = register;
        this.value = value;
    }

    public String getInstruction() {
        return instruction;
    }

    public String getRegister() {
        return register;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return instruction + " " + register + " " + value;
    }
}
