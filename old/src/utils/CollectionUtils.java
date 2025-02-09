package utils;

import java.util.Collection;

public class CollectionUtils {

    public static int getMaxValue(Collection<Integer> values) {
        int max = Integer.MIN_VALUE;
        for (int i : values) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }

    public static int[] reverse(int[] array) {
        int[] reverse = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            reverse[array.length - 1 - i] = array[i];
        }
        return reverse;
    }

    public static int[] convertStringToIntArray(String[] stringArray) {
        int[] intArray = new int[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            intArray[i] = Integer.valueOf(stringArray[i]);
        }
        return intArray;
    }
}
