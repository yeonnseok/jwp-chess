package chess.domain;

import java.util.List;
import java.util.Objects;

public class Board {
    private static final int BOARD_SIZE = 64;

    private List<Square> squares;

    public Board(final List<Square> squares) {
        if (squares.size() != BOARD_SIZE) {
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

    public void move(final Position from, final Position to) {
        Square fromSquare = findSquareBy(from);
        if (fromSquare.movable(this, to)) {
            updateSquareBy(to, fromSquare.getPiece(), fromSquare.getTeam());
            updateSquareBy(from, Piece.NONE, Team.NONE);
            return;
        }
        throw new InvalidMoveException();
    }

    public void updateSquareBy(final Position position, final Piece piece, final Team team) {
        squares.forEach(square -> {
            if (Objects.equals(square.getPosition(), position)) {
                square.update(piece, team);
            }
        });
    }

    public List<Square> getSquares() {
        return squares;
    }
}
