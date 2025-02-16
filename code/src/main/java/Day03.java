import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record Day03(int part1, int part2) {

    public Day03(int input) {
        this(solvePart1(input), solvePart2(input));
    }

    private static int solvePart1(int input) {
        int max = 1, min = -1;
        var pos = new Position(0, 0);
        Direction direction = Direction.RIGHT;

        while (input > 1) {
            pos = direction.move(pos);

            if (direction == Direction.RIGHT && pos.x() == max) {
                direction = Direction.UP;
            } else if (direction == Direction.UP && pos.y() == max) {
                max++;
                direction = Direction.LEFT;
            } else if (direction == Direction.LEFT && pos.x() == min) {
                direction = Direction.DOWN;
            } else if (direction == Direction.DOWN && pos.y() == min) {
                min--;
                direction = Direction.RIGHT;
            }

            input--;
        }

        return MathUtils.getManhattanDistance(pos.x(), pos.y());
    };

    private static int solvePart2(int input) {
        Map<Position, Integer> values = new HashMap<>();
        var pos = new Position(0, 0);
        Direction direction = Direction.RIGHT;

        values.put(pos, 1);
        int value = 1, max = 1, min = -1;

        while (value < input) {
            pos = direction.move(pos);
            value = setValue(values, pos);
            values.put(pos, value);

            if (direction == Direction.RIGHT && pos.x() == max) {
                direction = Direction.UP;
            } else if (direction == Direction.UP && pos.y() == max) {
                max++;
                direction = Direction.LEFT;
            } else if (direction == Direction.LEFT && pos.x() == min) {
                direction = Direction.DOWN;
            } else if (direction == Direction.DOWN && pos.y() == min) {
                min--;
                direction = Direction.RIGHT;
            }
        }

        return value;
    };

    private static int setValue(Map<Position, Integer> values, Position pos) {
        return pos.neighbors().stream()
            .mapToInt(neighbor -> values.getOrDefault(neighbor, 0))
            .sum();
    }

    private enum Direction {
        RIGHT, UP, LEFT, DOWN;

        public Position move(Position pos) {
            return switch (this) {
                case RIGHT -> new Position(pos.x() + 1, pos.y());
                case UP -> new Position(pos.x(), pos.y() + 1);
                case LEFT -> new Position(pos.x() - 1, pos.y());
                default -> new Position(pos.x(), pos.y() - 1); // DOWN
            };
        }
    }

    private record Position(int x, int y) {
        public List<Position> neighbors() {
            return List.of(
                new Position(x - 1, y - 1),
                new Position(x, y - 1),
                new Position(x + 1, y - 1),
                new Position(x - 1, y),
                new Position(x + 1, y),
                new Position(x - 1, y + 1),
                new Position(x, y + 1),
                new Position(x + 1, y + 1)
            );
        }
    }
}
