package utils;

public class ConversionUtils {

    private static String ZEROS_32 = "00000000000000000000000000000000";

    public static char[] toHexString(int each, int i) {
        char[] fixedResult = {'0','0'};
        char[] result = Integer.toHexString(each).toCharArray();
        if (result.length == 1) {
            System.arraycopy(result, 0, fixedResult, 1, 1);
            result = fixedResult;
        }
        return result;
    }

    public static String toBinaryString(long value) {
        String result = Long.toBinaryString(value);
//        System.out.println( ZEROS_32.substring(0,32-result.length()).concat(result) );
        return ZEROS_32.substring(0,32-result.length()).concat(result);
    }
}
