package year2017.day08;

public class Instruction {

    private String register;
    private boolean increment;
    private int value;
    private String testRegister;
    private Operator operator;
    private int testValue;

    public Instruction(String register, boolean increment, int value, String testRegister, String operator, int testValue) {
        this.register = register;
        this.increment = increment;
        this.value = value;
        this.testRegister = testRegister;
        this.operator = getOperator(operator);
        this.testValue = testValue;
    }

    private Operator getOperator(String operator) {
        if (operator.equals("==")) {
            return Operator.EQ;
        } else if (operator.equals("!=")) {
            return Operator.NEQ;
        } else if (operator.equals(">")) {
            return Operator.G;
        } else if (operator.equals("<")) {
            return Operator.L;
        } else if (operator.equals(">=")) {
            return Operator.GE;
        } else if (operator.equals("<=")) {
            return Operator.LE;
        } else {
            throw new RuntimeException("Unknown operator: " + operator);
        }
    }

    public String getRegister() {
        return register;
    }

    public boolean isIncrement() {
        return increment;
    }

    public int getValue() {
        return value;
    }

    public String getTestRegister() {
        return testRegister;
    }

    public Operator getOperator() {
        return operator;
    }

    public int getTestValue() {
        return testValue;
    }

    @Override
    public String toString() {
        return "Instruction{" +
                "register='" + register + '\'' +
                ", increment=" + increment +
                ", value=" + value +
                ", testRegister='" + testRegister + '\'' +
                ", operator=" + operator +
                ", testValue=" + testValue +
                '}';
    }

    public enum Operator {
        EQ, NEQ, G, L, GE, LE
    }

}
