package year2018.day01;

import utils.FileUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChronalCalibration {

    public static void main(String[] args) {
        String file = "2018day01.txt";
        System.out.println("Part 1: " + getResultingFrequency(FileUtils.getLines(file, ChronalCalibration.class)));
        System.out.println("Part 2: " + getDuplicateFrequency(FileUtils.getLines(file, ChronalCalibration.class)));
    }

    private static long getResultingFrequency(List<String> input) {
        long result = 0L;
        for (String value : input) {
            result += Long.valueOf(value);
        }
        return result;
    }

    private static long getDuplicateFrequency(List<String> input) {
        long result = 0L;
        Set<Long> frequencies = new HashSet<>();         
        frequencies.add(0L);
        for (int i = 0; i < 1000000; i++) { 
            for (String value : input) { 
                result += Long.valueOf(value);
                if (frequencies.contains(result)) {
                    return result;
                }
                frequencies.add(result);
            }
        }
        throw new RuntimeException("Didn't get any result in 1000000 runs");
    }
}
