package utils;

public class MathUtils {

    public static int getManhattanDistance(int x, int y) {
        int result = Math.abs(x) + Math.abs(y);
        return result;
    }

    public static double getHexDistance(double x, double y) {
        x = Math.abs(x);
        y = Math.abs(y);
        double result = x <= y ? x + y : 2 * x;
        return result;
    }
}
