package chess.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Position {
    private static final int MIN_RANK_SIZE = 1;
    private static final int MAX_RANK_SIZE = 8;
    public static final int ASCII_GAP = 96;

    private static final Map<String, Position> BOARD_POSITIONS = new HashMap<>();


    static {
        IntStream.rangeClosed(MIN_RANK_SIZE, MAX_RANK_SIZE)
                .forEach(i -> IntStream.rangeClosed(MIN_RANK_SIZE, MAX_RANK_SIZE)
                        .forEach(j -> BOARD_POSITIONS.put((char) (i + ASCII_GAP) + String.valueOf(j),
                                new Position((char)(i + ASCII_GAP), j))));
    }

    private final char file;
    private final int rank;

    private Position(final char file, final int rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position from(final String position) {
        if (BOARD_POSITIONS.containsKey(position)) {
            return BOARD_POSITIONS.get(position);
        }
        throw new InvalidPositionException();
    }

    public char getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }
}

