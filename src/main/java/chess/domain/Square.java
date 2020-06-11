package chess.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "SQUARE")
public class Square {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SQUARE_ID")
    private Long id;

    @Embedded
    private Position position;

    @Enumerated(EnumType.STRING)
    private Piece piece;

    @Enumerated(EnumType.STRING)
    private Team team;

    protected Square() {
    }

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

    public boolean isTeam(final Team team) {
        return this.team == team;
    }

    public boolean isOtherTeam(final Square square) {
        return team != square.team && (team != Team.NONE && square.team != Team.NONE);
    }

    public boolean isFirstTurn() {
        return (team == Team.WHITE && position.getRank() == 2) |
                (team == Team.BLACK && position.getRank() == 7);
    }

    public boolean isMultiPawn(Team team, char file) {
        return this.team == team
                && piece == Piece.PAWN
                && Objects.equals(position.getFile(), file);
    }

    public double getScore() {
        return piece.getScore();
    }

    public Long getId() {
        return id;
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

    public String getPositionValue() {
        return position.getFile() + String.valueOf(position.getRank());
    }

    public String getPieceTeam() {
        if (piece == Piece.NONE) {
            return "BLANK";
        }
        return team.name() + "_" + piece.name();
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
