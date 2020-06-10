package chess.domain;

public enum Team {
    WHITE,
    BLACK,
    NONE;

    public Team getOpposingTeam() {
        if (this == WHITE) {
            return BLACK;
        }
        if (this == BLACK) {
            return WHITE;
        }
        return NONE;
    }
}
