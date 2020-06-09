package chess.domain;

import java.util.Objects;

public class Square {
    private final Position position;
    private Piece piece;
    private Team team;

    private Square(final Position position, final Piece piece, final Team team) {
        this.position = position;
        this.piece = piece;
        this.team = team;
    }

    public static Square of(final Position position, final Piece piece, final Team team) {
        return new Square(position, piece, team);
    }

    public boolean movable(final Board board, final Position to) {
        return piece.findPossiblePaths(board, position).contains(to);
    }

    public void update(final Piece piece, final Team team) {
        this.piece = piece;
        this.team = team;
    }

    public boolean isWhite() {
        return team == Team.WHITE;
    }

    public boolean isSameTeam(Square targetSquare) {
        return team == targetSquare.getTeam();
    }

    public boolean isBlank() {
        return piece == Piece.NONE && team == Team.NONE;
    }

    public boolean isOtherTeam(final Square square) {
        return team != square.team && (team != Team.NONE && square.team != Team.NONE);
    }

    public boolean isFirstTurn() {
        return (team == Team.WHITE && position.getRank() == 2) |
                (team == Team.BLACK && position.getRank() == 7);
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
