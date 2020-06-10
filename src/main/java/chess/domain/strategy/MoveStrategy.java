package chess.domain.strategy;

import chess.domain.Board;
import chess.domain.Position;

import java.util.List;

public interface MoveStrategy {
    List<Position> findPossiblePaths(final Board board, final Position position);
}
