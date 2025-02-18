public final class MathUtils {

    private MathUtils() {
        throw new UnsupportedOperationException("Utility class should not be instantiated.");
    }

    /**
     * Computes the Manhattan Distance between the origin and (x, y).
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @return the Manhattan Distance
     */
    public static int getManhattanDistance(int x, int y) {
        return Math.abs(x) + Math.abs(y);
    }

    /**
     * Computes the Hexagonal Distance between the origin and (x, y).
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @return the Hexagonal Distance
     */
//    public static double getHexDistance(double x, double y) {
//        return (Math.abs(x) <= Math.abs(y)) ? Math.abs(x) + Math.abs(y) : 2 * Math.abs(x);
//    }

}
