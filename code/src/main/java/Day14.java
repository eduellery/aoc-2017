import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record Day14(int part1, int part2) {

    private static final int[] SUFFIX = {17, 31, 73, 47, 23};
    private static final int SIZE = 128;

    public static Day14 fromInput(String input) {
        List<String> denseHashes = generateHashes(input);
        int[] intHashes = generateIntHashes(denseHashes);
        return new Day14(calculateDiskSpace(intHashes), countGroups(intHashes));
    }

    private static int calculateDiskSpace(int[] hashes) {
        return Arrays.stream(hashes).map(Integer::bitCount).sum();
    }

    private static int[] generateIntHashes(List<String> hashes) {
        return hashes.stream()
                .flatMapToInt(
                        hash ->
                                hash.chars()
                                        .map(c -> Integer.parseInt(String.valueOf((char) c), 16)))
                .toArray();
    }

    private static int countGroups(int[] values) {
        boolean[][] grid = new boolean[SIZE][SIZE];
        for (int index = 0, row = 0, col = 0; index < (SIZE * SIZE) / 4; index++) {
            int value = values[index];
            for (int bit = 0; bit < 4; bit++) {
                grid[row][col] = ((value >> 3 - bit) & 1) == 1;
                col++;
            }
            if (col >= SIZE) {
                col = 0;
                row++;
            }
        }

        int groupCount = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j]) {
                    bfs(grid, i, j);
                    groupCount++;
                }
            }
        }
        return groupCount;
    }

    private static void bfs(boolean[][] grid, int x, int y) {
        grid[x][y] = false;
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        for (int i = 0; i < 4; i++) {
            int nx = (x + dx[i]);
            int ny = (y + dy[i]);
            if (nx < 0 || ny < 0 || nx >= SIZE || ny >= SIZE) {
                continue;
            }
            if (grid[nx][ny]) {
                bfs(grid, nx, ny);
            }
        }
    }

    private static List<String> generateHashes(String input) {
        return IntStream.range(0, SIZE).mapToObj(i -> computeKnotHash(input + "-" + i)).toList();
    }

    private static String computeKnotHash(String input) {
        int[] lengths = Arrays.copyOf(input.chars().toArray(), input.length() + SUFFIX.length);
        System.arraycopy(SUFFIX, 0, lengths, lengths.length - SUFFIX.length, SUFFIX.length);

        int[] nums =
                Day10.sparseHash(
                        IntStream.range(0, 256).toArray(),
                        Arrays.stream(lengths).boxed().collect(Collectors.toList()),
                        64);

        return Day10.denseHash(nums);
    }
}
