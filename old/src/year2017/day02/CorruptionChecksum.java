package year2017.day02;

import utils.FileUtils;

import java.util.List;
import java.util.function.Function;

public class CorruptionChecksum {

    public static void main(String[] args) {
//        String testEntry1 = "2017day02test1.txt";
//        System.out.println(getMaxDistChecksum(FileUtils.getLines(testEntry1, CorruptionChecksum.class), false));
//        String testEntry2 = "2017day02test2.txt";
//        System.out.println(getMaxDistChecksum(FileUtils.getLines(testEntry2, CorruptionChecksum.class), true));
        String inputEntry = "2017day02.txt";
        System.out.println(getMaxDistChecksum(FileUtils.getLines(inputEntry, CorruptionChecksum.class), false));
        System.out.println(getMaxDistChecksum(FileUtils.getLines(inputEntry, CorruptionChecksum.class), true));
        List<List<Integer>> spreadsheet = null;
        Integer a = spreadsheet.stream()
                .map(row -> row.stream().flatMap(i -> row.stream().filter(j -> j != i && j % i == 0).map(j -> j / i)))
                .flatMap(Function.identity()).reduce(0, Integer::sum);
    }

    private static int getMaxDistChecksum(List<String> lines, boolean div) {
        int checksum = 0;
        for (String line : lines) {
            checksum += div ? getDivDist(line) : getMaxDist(line);
        }
        return checksum;
    }

    private static int getMaxDist(String input) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        String[] values = input.split("\\s");
        for (String value : values) {
            int intValue = Integer.valueOf(value);
            if (intValue <= min) {
                min = intValue;
            }
            if (intValue >= max) {
                max = intValue;
            }
        }
        return Math.abs(max - min);
    }


    private static int getDivDist(String input) {
        String[] values = input.split("\\s");
        for (int i = 0; i < values.length - 1; i++) {
            for (int j = i + 1; j < values.length; j++) {
                int a = Integer.valueOf(values[i]);
                int b = Integer.valueOf(values[j]);
                int div = a > b ? a % b : b % a;
                if (div == 0) {
                    return a > b ? a / b : b / a;
                }
            }
        }
        throw new RuntimeException("Couldn't find divisible numbers");
    }

}
