package chess.domain;

import chess.domain.direction.Direction;
import chess.domain.strategy.*;

import java.util.List;

public enum Piece {
    KING(0, new SingleMoveStrategy(Direction.getKingDirections())),
    QUEEN(9, new MultiMoveStrategy(Direction.getQueenDirections())),
    ROOK(5, new MultiMoveStrategy(Direction.getRookDirections())),
    BISHOP(3, new MultiMoveStrategy(Direction.getBishopDirections())),
    KNIGHT(2.5, new SingleMoveStrategy(Direction.getKnightDirections())),
    PAWN(1, new PawnMoveStrategy(Direction.getPawnDirections())),
    NONE(-1, null);

    private final double score;
    private final MoveStrategy moveStrategy;

    Piece(final double score, final MoveStrategy moveStrategy) {
        this.score = score;
        this.moveStrategy = moveStrategy;
    }

    public double getScore() {
        return score;
    }

    public double getHalfScore() {
        return score / 2;
    }

    public List<Position> findPossiblePaths(final Board board, final Position position) {
        System.out.println(moveStrategy.findPossiblePaths(board, position));
        return moveStrategy.findPossiblePaths(board, position);
    }
}
