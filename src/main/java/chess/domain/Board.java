package chess.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import static chess.domain.Position.*;

@Entity
@Table(name = "BOARD")
public class Board {
    private static final int BOARD_SIZE = 64;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="BOARD_ID")
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "BOARD_ID")
    private List<Square> squares;

    protected Board() {
    }

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

    public State move(final String fromValue, final String toValue) {
        Position from = Position.from(fromValue);
        Position to = Position.from(toValue);
        Square fromSquare = findSquareBy(from);
        if (fromSquare.movable(this, to)) {
            Piece toPiece = findSquareBy(to).getPiece();
            Team movedTurn = fromSquare.getTeam();

            updateSquareBy(to, fromSquare.getPiece(), fromSquare.getTeam());
            updateSquareBy(from, Piece.NONE, Team.NONE);

            return getNextState(toPiece, movedTurn);
        }
        throw new InvalidMoveException();
    }

    private State getNextState(final Piece toPiece, final Team movedTurn) {
        if (toPiece.isKing()){
            return new Finished(movedTurn);
        }
        return new Playing(movedTurn);
    }

    public void updateSquareBy(final Position position, final Piece piece, final Team team) {
        squares.forEach(square -> {
            if (Objects.equals(square.getPosition(), position)) {
                square.update(piece, team);
            }
        });
    }

    public double calculateTotalScore(final Team team) {
        final int multiPawnCount = IntStream.rangeClosed(MIN_RANK_SIZE, MAX_RANK_SIZE)
                .map(i -> (int) squares.stream()
                        .filter(square -> square.isMultiPawn(team, (char) (i + ASCII_GAP)))
                        .count()
                )
                .map(count -> count > 1 ? count : 0)
                .sum();

        return squares.stream()
                .filter(square -> square.isTeam(team))
                .mapToDouble(Square::getScore)
                .sum()
                - (Piece.PAWN.getHalfScore() * multiPawnCount);
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public List<Square> getSquares() {
        return squares;
    }
}
