import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public record Day10(int part1, String part2) {

    private static int max;
    private static final List<Integer> SUFFIX = List.of(17, 31, 73, 47, 23);

    public static Day10 fromValues(String input, int length) {
        List<Integer> lengths = Arrays.stream(input.split(",")).map(Integer::parseInt).toList();

        List<Integer> fullLengths = Stream.concat(input.chars().boxed(), SUFFIX.stream()).toList();

        max = length;

        int[] sparseHash1 = sparseHash(IntStream.range(0, max).toArray(), lengths, 1);
        int[] sparseHash2 = sparseHash(IntStream.range(0, max).toArray(), fullLengths, 64);

        int part1 = sparseHash1[0] * sparseHash1[1];

        String part2 = sparseHash1.length < 16 ? "" : denseHash(sparseHash2);

        return new Day10(part1, part2);
    }

    public static int[] sparseHash(int[] list, List<Integer> lengths, int rounds) {
        int current = 0, skip = 0;
        for (int round = 0; round < rounds; round++) {
            for (int length : lengths) {
                reverseSegment(list, current, length);
                current = (current + length + skip) % max;
                skip++;
            }
        }
        return list;
    }

    public static String denseHash(int[] sparseHash) {
        return IntStream.range(0, 16)
                .map(
                        i ->
                                Arrays.stream(sparseHash, i * 16, (i + 1) * 16)
                                        .reduce((a, b) -> a ^ b)
                                        .orElse(0))
                .mapToObj(i -> String.format("%02x", i))
                .collect(Collectors.joining());
    }

    private static void reverseSegment(int[] list, int start, int length) {
        for (int i = 0; i < length / 2; i++) {
            int idx1 = (start + i) % max;
            int idx2 = (start + length - 1 - i) % max;
            int temp = list[idx1];
            list[idx1] = list[idx2];
            list[idx2] = temp;
        }
    }
}
