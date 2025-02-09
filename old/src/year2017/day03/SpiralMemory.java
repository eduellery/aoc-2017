package year2017.day03;

import utils.MathUtils;

public class SpiralMemory {

    private static final int I_OFF = 50;
    private static final int J_OFF = 50;

    public static void main(String[] args) {
//        Integer[] values = {1, 12, 23, 1024};
//        Integer[] results = {0, 3, 2, 31};
//        System.out.println(TestUtils.test(values, results, SpiralMemory::getDistance));
        int input = 325489;
        int result = getDistance(input);
        System.out.println("Result 1: " + result);
//        int test = 54;
//        System.out.println(createMatrix(50, false) == test);
        System.out.println("Result 2: " + createMatrix(input, false));
    }

    @SuppressWarnings("ConstantConditions")
    private static int getDistance(int input) {
        int max = 1, min = -1;
        int i = 0, j = 0;
        boolean right = true, up = true;

        while (input != 1) {
            if (i < max && (right && up)) {
                i++;
                if (i == max) {
                    right = false;
                }
                input--;
                continue;
            }
            if (j < max && (!right && up)) {
                j++;
                if (j == max) {
                    max++;
                    up = false;
                }
                input--;
                continue;
            }
            if (i > min && (!right && !up)) {
                i--;
                if (i == min) {
                    right = true;
                }
                input--;
                continue;
            }
            if (j > min && (right && !up)) {
                j--;
                if (j == min) {
                    min--;
                    up = true;
                }
                input--;
                continue;
            }
        }

        return MathUtils.getManhattanDistance(i, j);
    }

    @SuppressWarnings("ConstantConditions")
    private static int createMatrix(int input, boolean print) {
        int[][] values = new int[(I_OFF * 2) + 1][(J_OFF * 2) + 1];
        int max = 1;
        int min = -1;
        int i = 0;
        int j = 0;
        boolean right = true, up = true;
        int value = 1;
        values[i+I_OFF][j+J_OFF] = 1;

        while (input > value) {
            if (i < max && (right && up)) {
                i++;
                if (i == max) {
                    right = false;
                }
                value = setValue(values, i, j, print);
                continue;
            }
            if (j < max && (!right && up)) {
                j++;
                if (j == max) {
                    max++;
                    up = false;
                }
                value = setValue(values, i, j, print);
                continue;
            }
            if (i > min && (!right && !up)) {
                i--;
                if (i == min) {
                    right = true;
                }
                value = setValue(values, i, j, print);
                continue;
            }
            if (j > min && (right && !up)) {
                j--;
                if (j == min) {
                    min--;
                    up = true;
                }
                value = setValue(values, i, j, print);
                continue;
            }
        }

        return value;
    }

    private static int setValue(int[][] values, int i, int j, boolean print) {
        int sum = 0;
        for (int a = -1; a <= 1; a++) {
            for (int b = -1; b <= 1; b++) {
                if (a != 0 || b != 0) {
                    int current = values[a+i+I_OFF][b+j+J_OFF];
                    if (current > 0) {
                        sum += current;
                    }
                }
            }
        }
        if (print) {
            System.out.println("Setting value for position [" + i + "," + j + "]: " + sum);
        }
        values[i+I_OFF][j+J_OFF] = sum;
        return sum;
    }

}
