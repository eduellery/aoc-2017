package year2017.day14;

import year2017.day10.AnotherKnotHash;

import java.util.ArrayList;
import java.util.List;

public class DiskDefragmentation {

    public static void main(String[] args) {
        String test = "flqrgnkx";
        int space;
        space = calculateDikSpace(test);
        System.out.println(space);
        System.out.println(calculateDikSpace("ljoxqyyw"));
    }

    private static int calculateDikSpace(String input) {
        List<String> hashes = generateHashes(input);
        int count = 0;
        for (String hash : hashes) {
            for (int i = 0; i < hash.length(); i += 2 ) {
                int value = Integer.parseInt(hash.substring(i, i+2), 16);
                count += numOnesInBinary(value);
            }
        }
        return count;
    }

    private static List<String> generateHashes(String test) {
        List<String> hashes = new ArrayList<>();
        for (int i = 0; i < 128; i++) {
            AnotherKnotHash hash = new AnotherKnotHash(test + "-" + String.valueOf(i));
            hashes.add(hash.part2());
        }
        return hashes;
    }

    public static int numOnesInBinary(int n) {
        if (n < 0) return -1;

        int j = 0;
        while ( n > Math.pow(2, j)) j++;

        int result = 0;
        for (int i=j; i >=0; i--){
            if (n >= Math.pow(2, i)) {
                n = (int) (n - Math.pow(2,i));
                result++;
            }
        }

        return result;
    }
}
