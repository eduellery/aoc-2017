package year2017.day13;

public class Layer {

    private int depth;
    private int range;

    public Layer(int depth, int range) {
        this.depth = depth;
        this.range = range;
    }

    public boolean isCaught(int delay) {
        return ((depth + delay) % (2 * (range - 1)) == 0);
    }

    public int getSeverity() {
        return depth * range;
    }
}
