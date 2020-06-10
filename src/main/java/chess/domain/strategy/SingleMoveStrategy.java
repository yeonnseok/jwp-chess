package chess.domain.strategy;

import chess.domain.Board;
import chess.domain.InvalidPositionException;
import chess.domain.Position;
import chess.domain.Square;
import chess.domain.direction.Direction;

import java.util.ArrayList;
import java.util.List;

public class SingleMoveStrategy implements MoveStrategy {

    private final List<Direction> directions;

    public SingleMoveStrategy(final List<Direction> directions) {
        this.directions = directions;
    }

    @Override
    public List<Position> findPossiblePaths(final Board board, final Position position) {
        List<Position> paths = new ArrayList<>();
        directions.forEach(direction -> {
           try {
               Square square = board.findSquareBy(position);

               Position nextPosition = position.updateWith(direction);
               Square nextSquare = board.findSquareBy(nextPosition);
               if (nextSquare.isSameTeam(square)) {
                   return;
               }
               paths.add(nextPosition);
           }catch(InvalidPositionException ignore) {
           }
        });
        return paths;
    }
}
