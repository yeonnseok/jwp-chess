package chess.domain;

import chess.domain.direction.Direction;

import javax.persistence.Embeddable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

@Embeddable
public class Position {
    static final int MIN_RANK_SIZE = 1;
    static final int MAX_RANK_SIZE = 8;
    static final int ASCII_GAP = 96;

    private static final Map<String, Position> BOARD_POSITIONS = new HashMap<>();

    static {
        IntStream.rangeClosed(MIN_RANK_SIZE, MAX_RANK_SIZE)
                .forEach(i -> IntStream.rangeClosed(MIN_RANK_SIZE, MAX_RANK_SIZE)
                        .forEach(j -> BOARD_POSITIONS.put((char) (i + ASCII_GAP) + String.valueOf(j),
                                new Position((char)(i + ASCII_GAP), j))));
    }

    private char file;
    private int rank;

    protected Position() {
    }

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

    public static Position of(final int file, final int rank) {
        return new Position((char)(file + ASCII_GAP), rank);
    }

    public char getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }

    public Position updateWith(final Direction direction) {
        return Position.from((char)(file + direction.getToFile())
                + String.valueOf(rank + direction.getToRank()));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Position position = (Position) o;
        return file == position.file &&
                rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    @Override
    public String toString() {
        return "Position{" +
                "file=" + file +
                ", rank=" + rank +
                '}';
    }
}

