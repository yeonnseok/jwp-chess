package chess.domain.strategy;

import chess.domain.Board;
import chess.domain.InvalidPositionException;
import chess.domain.Position;
import chess.domain.Square;
import chess.domain.direction.Direction;

import java.util.ArrayList;
import java.util.List;

public class PawnMoveStrategy implements MoveStrategy {

    private final List<Direction> whiteDirections;
    private final List<Direction> blackDirections;

    public PawnMoveStrategy(final List<Direction> directions) {
        this.whiteDirections = directions.subList(0, 3);
        this.blackDirections = directions.subList(3, 6);
    }

    @Override
    public List<Position> findPossiblePaths(final Board board, final Position position) {
        Square square = board.findSquareBy(position);

        if (square.isWhite()) {
            if (square.isFirstTurn()) {
                return findPathWith(Direction.firstWhitePawnDirections(), square, board, position);
            }
            return findPathWith(whiteDirections, square, board, position);
        }

        if (square.isFirstTurn()) {
            return findPathWith(Direction.firstBlackPawnDirections(), square, board, position);
        }
        return findPathWith(blackDirections, square, board, position);
    }

    private List<Position> findPathWith(List<Direction> directions, Square square, Board board, Position position) {
        List<Position> paths = new ArrayList<>();

        directions.forEach(direction -> {
            try {
                Position nextPosition = position.updateWith(direction);
                Square nextSquare = board.findSquareBy(nextPosition);

                if (direction.isStraight() && nextSquare.isBlank()) {
                    paths.add(nextPosition);
                }

                if (direction.isDiagonal() && nextSquare.isOtherTeam(square)) {
                    paths.add(nextPosition);
                }
            } catch(InvalidPositionException ignore) {
            }
        });
        return paths;
    }
}
