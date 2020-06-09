package chess.domain;

import java.util.List;
import java.util.Objects;

public class Board {
    private static final int BOARD_SIZE = 64;

    private final List<Square> squares;

    public Board(final List<Square> squares) {
        if(squares.size() != BOARD_SIZE) {
            throw new InvalidBoardSizeException();
        }
        this.squares = squares;
    }

    public Square findSquareBy(final Position position) {
        return squares.stream()
                .filter(square -> Objects.equals(square.getPosition(), position))
                .findFirst()
                .orElseThrow(NoSuchSquareException::new);
    }

    public List<Square> getSquares() {
        return squares;
    }
}
