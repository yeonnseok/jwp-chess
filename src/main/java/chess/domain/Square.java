package chess.domain;

public class Square {
    private final Position position;
    private final Piece piece;
    private final Team team;

    private Square(final Position position, final Piece piece, final Team team) {
        this.position = position;
        this.piece = piece;
        this.team = team;
    }

    public static Square of(final Position position, final Piece piece, final Team team) {
        return new Square(position, piece, team);
    }

    public Position getPosition() {
        return position;
    }

    public Piece getPiece() {
        return piece;
    }

    public Team getTeam() {
        return team;
    }

}
