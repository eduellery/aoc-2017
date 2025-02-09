package year2017.day10;

import utils.CollectionUtils;
import utils.ConversionUtils;
import utils.FileUtils;
import utils.Timer;

import java.util.Arrays;
import java.util.stream.IntStream;

public class AnotherKnotHash {

    private int pos;
    private int skips;
    private static final int[] SUFFIX = {17, 31, 73, 47, 23};

    private String input;
    private int[] lengths;
    private int[] nums;

    public AnotherKnotHash(String input) {

        this.input = input;
    }

    private void reset() {
        pos = 0;
        skips = 0;
        nums = IntStream.range(0, 256).toArray();
    }

    public void hash() {
        for (int each : lengths) {
            int[] temp = new int[each];
            int cut = 0;
            if (each <= nums.length - pos) {
                cut = each;
            } else {
                cut = (nums.length - pos);
            }
            int leftover = each - cut;

            System.arraycopy(nums, pos, temp, 0, cut);
            System.arraycopy(nums, 0, temp, cut, leftover);

            temp = CollectionUtils.reverse(temp);

            // copy back into array
            System.arraycopy(temp, 0, nums, pos, cut);
            System.arraycopy(temp, each - leftover, nums, 0, leftover);

            pos += each + skips;
            pos %= nums.length;
            skips++;
        }
    }

    public int[] part1() {
        lengths = CollectionUtils.convertStringToIntArray(input.split(","));
        reset();
        hash();
        return nums; // nums[0] * nums[1];
    }

    public String part2() {
        lengths = input.chars().toArray();
        lengths = Arrays.copyOf(lengths, lengths.length + SUFFIX.length);
        System.arraycopy(SUFFIX, 0, lengths, lengths.length - SUFFIX.length, SUFFIX.length);

        for (int l : lengths) {
            //System.out.println(l);
        }
        System.out.println(lengths.length);
        reset();
        for (int i = 0; i < 64; i++) {
            hash();
        }
        for (int value : nums) {
            System.out.println(" " + value);
        }
        return knotHash();
    }

    private String knotHash() {
        int[] dense = new int[16];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                dense[i] ^= nums[(i * 16) + j];
            }
        }
        StringBuilder output = new StringBuilder();
        for (int each : dense) {
            output.append(ConversionUtils.toHexString(each, 8));
        }
        return output.toString();
    }

    public static void main(String[] args) {

        String input = FileUtils.getLine("2017day10.txt", AnotherKnotHash.class);

        Timer.startTimer();
        AnotherKnotHash anotherKnotHash = new AnotherKnotHash(input);

        int[] p1 = anotherKnotHash.part1();
        System.out.println("Part 1: " + p1[0] * p1[1]);
        System.out.println("Part 2: " + anotherKnotHash.part2());
        System.out.println(Timer.stopTimer());

    }
}
