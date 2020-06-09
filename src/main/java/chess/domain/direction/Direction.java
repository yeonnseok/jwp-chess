package chess.domain.direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Direction {
    NORTH(0, 1),
    SOUTH(0, -1),
    EAST(1, 0),
    WEST(-1, 0),

    NORTH_EAST(1, 1),
    NORTH_WEST(-1, 1),
    SOUTH_EAST(1, -1),
    SOUTH_WEST(-1, -1),

    SOUTH_SOUTH_EAST(1, -2),
    SOUTH_SOUTH_WEST(-1, -2),
    NORTH_NORTH_WEST(-1, 2),
    NORTH_NORTH_EAST(1, 2),
    EAST_EAST_SOUTH(2, -1),
    EAST_EAST_NORTH(2, 1),
    WEST_WEST_SOUTH(-2, -1),
    WEST_WEST_NORTH(-2, 1),

    NORTH_NORTH(0, 2),
    SOUTH_SOUTH(0, -2);

    private final int toFile;
    private final int toRank;

    Direction(final int toFile, final int toRank) {
        this.toFile = toFile;
        this.toRank = toRank;
    }

    public static List<Direction> getKingDirections() {
        return new ArrayList<>(
                Arrays.asList(NORTH, NORTH_EAST, EAST, SOUTH_EAST, SOUTH, SOUTH_WEST, WEST, NORTH_WEST)
        );
    }

    public static List<Direction> getQueenDirections() {
        return new ArrayList<>(
                Arrays.asList(NORTH, NORTH_EAST, EAST, SOUTH_EAST, SOUTH, SOUTH_WEST, WEST, NORTH_WEST)
        );
    }

    public static List<Direction> getRookDirections() {
        return new ArrayList<>(
                Arrays.asList(NORTH, EAST, SOUTH, WEST)
        );
    }

    public static List<Direction> getBishopDirections() {
        return new ArrayList<>(
                Arrays.asList(NORTH_EAST, SOUTH_EAST, SOUTH_WEST, NORTH_WEST)
        );
    }

    public static List<Direction> getKnightDirections() {
        return new ArrayList<>(
                Arrays.asList(NORTH_NORTH_EAST, EAST_EAST_NORTH,
                        EAST_EAST_SOUTH, SOUTH_SOUTH_EAST,
                        SOUTH_SOUTH_WEST, WEST_WEST_SOUTH,
                        WEST_WEST_NORTH, NORTH_NORTH_WEST)
        );
    }

    public static List<Direction> getPawnDirections() {
        return new ArrayList<>(
                Arrays.asList(NORTH, NORTH_EAST, NORTH_WEST, SOUTH, SOUTH_EAST, SOUTH_WEST)
        );
    }

    public static List<Direction> firstWhitePawnDirections() {
        return new ArrayList<>(
                Arrays.asList(NORTH, NORTH_NORTH, NORTH_EAST, NORTH_WEST)
        );
    }

    public static List<Direction> firstBlackPawnDirections() {
        return new ArrayList<>(
                Arrays.asList(SOUTH, SOUTH_SOUTH, SOUTH_EAST, SOUTH_WEST)
        );
    }

    public boolean isStraight() {
        return this == SOUTH | this == SOUTH_SOUTH |
                this == NORTH | this == NORTH_NORTH;
    }

    public boolean isDiagonal() {
        return this == SOUTH_EAST | this == SOUTH_WEST |
                this == NORTH_EAST | this == NORTH_WEST;
    }

    public int getToFile() {
        return toFile;
    }

    public int getToRank() {
        return toRank;
    }
}
