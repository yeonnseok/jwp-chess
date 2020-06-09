package chess.domain;

import java.util.Objects;

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

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Square square = (Square) o;
        return Objects.equals(position, square.position) &&
                piece == square.piece &&
                team == square.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, piece, team);
    }
}
