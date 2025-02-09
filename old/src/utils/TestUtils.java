package utils;

import java.util.function.Function;

@SuppressWarnings("Duplicates")
public class TestUtils {

    public static Boolean test(String[] input, Integer[] output, Function<String, Integer> fn){
        if (input.length != output.length) {
            System.out.println("Different input sizes: " + input.length + " and " + output.length);
            return false;
        }
        for (int i = 0; i < input.length; i++) {
            int test = fn.apply(input[i]);
            if (output[i] != test) {
                System.out.println("Expecting " + output[i] + " for " + input[i] + " instead of " + test);
                return false;
            }
        }
        return true;
    }

    public static Boolean test(Integer[] input, Integer[] output, Function<Integer, Integer> fn){
        if (input.length != output.length) {
            System.out.println("Different input sizes: " + input.length + " and " + output.length);
            return false;
        }
        for (int i = 0; i < input.length; i++) {
            int test = fn.apply(input[i]);
            if (output[i] != test) {
                System.out.println("Expecting " + output[i] + " for " + input[i] + " instead of " + test);
                return false;
            }
        }
        return true;
    }

}
