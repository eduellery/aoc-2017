package year2017.day15;

import utils.ConversionUtils;

public class DuelingGenerators {

    public static void main(String[] args) {
        Generator genA = new Generator(65, 16807, 2147483647, 1);
        Generator genB = new Generator(8921, 48271, 2147483647, 1);
        System.out.println(countMatches(genA, genB, 40000000));
        genA = new Generator(277, 16807, 2147483647,1);
        genB = new Generator(349, 48271, 2147483647,1);
        System.out.println(countMatches(genA, genB, 40000000));
        genA = new Generator(65, 16807, 2147483647, 4);
        genB = new Generator(8921, 48271, 2147483647, 8);
        System.out.println(countMatches(genA, genB, 5000000));
        genA = new Generator(277, 16807, 2147483647,4);
        genB = new Generator(349, 48271, 2147483647,8);
        System.out.println(countMatches(genA, genB, 5000000));
    }

    private static int countMatches(Generator genA, Generator genB, int times) {
        int matches = 0;
        for (int i = 0; i < times; i++) {
            if (compareTrailingBits(ConversionUtils.toBinaryString(genA.getGeneratedValue()), ConversionUtils.toBinaryString(genB.getGeneratedValue()), 16)) {
                matches++;
            }
            genA.generateNext();
            genB.generateNext();
        }
        return matches;
    }

    private static boolean compareTrailingBits(String binaryStringA, String binaryStringB, int bits) {
        String trailA = binaryStringA.substring(binaryStringA.length() - bits);
        String trailB = binaryStringB.substring(binaryStringB.length() - bits);
        return trailA.equalsIgnoreCase(trailB);
    }
}
