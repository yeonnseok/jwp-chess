package chess.domain;

public enum Piece {
    KING(0),
    QUEEN(9),
    ROOK(5),
    BISHOP(3),
    KNIGHT(2.5),
    PAWN(1),
    NONE(-1);

    private final double score;

    Piece(final double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }
}
