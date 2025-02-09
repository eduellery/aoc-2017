package year2017.day15;

public class Generator {

    private final int seed;
    private final int factor;
    private final int dividend;
    private final int multiple;
    private long generatedValue;

    public Generator(int seed, int factor, int dividend, int multiple) {
        this.seed = seed;
        this.factor = factor;
        this.dividend = dividend;
        this.multiple = multiple;
        generatedValue = (seed * factor) % dividend;
    }

    public void generateNext() {
        do {
            generatedValue = (generatedValue * factor) % dividend;
        } while (generatedValue % multiple != 0);
    }

    public long getGeneratedValue() {
        return generatedValue;
    }
}
