package chess.domain.strategy;

import chess.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MultiMoveStrategyTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = BoardFactory.createEmpty();
        board.updateSquareBy(Position.from("f4"), Piece.QUEEN, Team.WHITE);
        board.updateSquareBy(Position.from("g4"), Piece.KNIGHT, Team.BLACK);
        board.updateSquareBy(Position.from("f6"), Piece.PAWN, Team.WHITE);
        board.updateSquareBy(Position.from("e4"), Piece.KING, Team.WHITE);
        board.updateSquareBy(Position.from("c2"), Piece.ROOK, Team.WHITE);
        board.updateSquareBy(Position.from("d2"), Piece.BISHOP, Team.WHITE);
        board.updateSquareBy(Position.from("c3"), Piece.BISHOP, Team.WHITE);
    }

    @DisplayName("퀸 이동 불가능")
    @ParameterizedTest
    @ValueSource(strings = {"f6", "e4", "a1", "b2"})
    void invalidMoveQueen(String toValue) {
        assertThatThrownBy(() -> {
            Position from = Position.from("f4");
            Position to = Position.from(toValue);

            board.move(from, to);
        }).isInstanceOf(InvalidMoveException.class);
    }

    @DisplayName("퀸 이동")
    @ParameterizedTest
    @ValueSource(strings = {"f5", "h6", "g4", "g5", "g3", "h2", "e3"})
    void moveQueen(String toValue) {
        Position from = Position.from("f4");
        Position to = Position.from(toValue);

        board.move(from, to);

        assertThat(board.findSquareBy(from).getPiece()).isEqualTo(Piece.NONE);
        assertThat(board.findSquareBy(from).getTeam()).isEqualTo(Team.NONE);
        assertThat(board.findSquareBy(to).getPiece()).isEqualTo(Piece.QUEEN);
        assertThat(board.findSquareBy(to).getTeam()).isEqualTo(Team.WHITE);
    }

    @DisplayName("룩 이동 불가능")
    @ParameterizedTest
    @ValueSource(strings = {"h7", "g5", "c6", "f2"})
    void invalidMoveRook(String toValue) {
        assertThatThrownBy(() -> {
            Position from = Position.from("c2");
            Position to = Position.from(toValue);

            board.move(from, to);
        }).isInstanceOf(InvalidMoveException.class);
    }

    @DisplayName("룩 이동")
    @ParameterizedTest
    @ValueSource(strings = {"a2", "b2", "c1"})
    void moveRook(String toValue) {
        Position from = Position.from("c2");
        Position to = Position.from(toValue);

        board.move(from, to);

        assertThat(board.findSquareBy(from).getPiece()).isEqualTo(Piece.NONE);
        assertThat(board.findSquareBy(from).getTeam()).isEqualTo(Team.NONE);
        assertThat(board.findSquareBy(to).getPiece()).isEqualTo(Piece.ROOK);
        assertThat(board.findSquareBy(to).getTeam()).isEqualTo(Team.WHITE);
    }

    @DisplayName("비숍 이동 불가능")
    @ParameterizedTest
    @ValueSource(strings = {"d3", "g8", "f4", "a1"})
    void invalidMoveBishop(String toValue) {
        assertThatThrownBy(() -> {
            Position from = Position.from("d2");
            Position to = Position.from(toValue);

            board.move(from, to);
        }).isInstanceOf(InvalidMoveException.class);
    }

    @DisplayName("퀸 이동")
    @ParameterizedTest
    @ValueSource(strings = {"c1", "e1", "e3"})
    void moveBishop(String toValue) {
        Position from = Position.from("d2");
        Position to = Position.from(toValue);

        board.move(from, to);

        assertThat(board.findSquareBy(from).getPiece()).isEqualTo(Piece.NONE);
        assertThat(board.findSquareBy(from).getTeam()).isEqualTo(Team.NONE);
        assertThat(board.findSquareBy(to).getPiece()).isEqualTo(Piece.BISHOP);
        assertThat(board.findSquareBy(to).getTeam()).isEqualTo(Team.WHITE);
    }
}