package chess.domain.strategy;

import chess.domain.Board;
import chess.domain.InvalidPositionException;
import chess.domain.Position;
import chess.domain.Square;
import chess.domain.direction.Direction;

import java.util.ArrayList;
import java.util.List;

public class MultiMoveStrategy implements MoveStrategy {

    private final List<Direction> directions;

    public MultiMoveStrategy(final List<Direction> directions) {
        this.directions = directions;
    }

    @Override
    public List<Position> findPossiblePaths(final Board board, final Position position) {
        List<Position> paths = new ArrayList<>();

        directions.forEach(direction -> {
            try {
                Square square = board.findSquareBy(position);
                Position currentPosition = position;

                while (true) {
                    Position nextPosition = currentPosition.updateWith(direction);
                    Square nextSquare = board.findSquareBy(nextPosition);

                    if (nextSquare.isOtherTeam(square)) {
                        paths.add(nextPosition);
                        break;
                    }
                    if (nextSquare.isSameTeam(square)) {
                        break;
                    }
                    paths.add(nextPosition);
                    currentPosition = nextPosition;
                }
            } catch(InvalidPositionException ignore){
            }
        });
        return paths;
    }
}
