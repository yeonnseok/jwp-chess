package chess.domain.strategy;

import chess.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SingleMoveStrategyTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = BoardFactory.createEmpty();
        board.updateSquareBy(Position.from("f5"), Piece.KNIGHT, Team.WHITE);
        board.updateSquareBy(Position.from("d6"), Piece.ROOK, Team.BLACK);
        board.updateSquareBy(Position.from("c3"), Piece.KING, Team.BLACK);
        board.updateSquareBy(Position.from("c2"), Piece.PAWN, Team.WHITE);
        board.updateSquareBy(Position.from("d4"), Piece.BISHOP, Team.BLACK);
    }

    @DisplayName("나이트 이동 불가")
    @ParameterizedTest
    @ValueSource(strings = {"a1", "f5", "h8"})
    void invalidMoveKnight(String toValue) {
        assertThatThrownBy(() -> {
            board.move("f5", toValue);
        }).isInstanceOf(InvalidMoveException.class);
    }

    @DisplayName("나이트 이동")
    @ParameterizedTest
    @ValueSource(strings = {"d6", "e7", "g7", "h6", "h4", "g3", "e3", "d4"})
    void moveKnight(String toValue) {
        Position from = Position.from("f5");
        Position to = Position.from(toValue);

        board.move("f5", toValue);

        assertThat(board.findSquareBy(from).getPiece()).isEqualTo(Piece.NONE);
        assertThat(board.findSquareBy(from).getTeam()).isEqualTo(Team.NONE);
        assertThat(board.findSquareBy(to).getPiece()).isEqualTo(Piece.KNIGHT);
        assertThat(board.findSquareBy(to).getTeam()).isEqualTo(Team.WHITE);
    }

    @DisplayName("킹 이동 불가능")
    @ParameterizedTest
    @ValueSource(strings = {"h8", "d7", "a6", "d4"})
    void invalidMoveKing(String toValue) {
        assertThatThrownBy(() -> {
            board.move("c3", toValue);
        }).isInstanceOf(InvalidMoveException.class);
    }

    @DisplayName("킹 이동")
    @ParameterizedTest
    @ValueSource(strings = {"c4", "d3", "d2", "c2", "b2", "b3", "b4"})
    void moveKing(String toValue) {
        Position from = Position.from("c3");
        Position to = Position.from(toValue);

        board.move("c3", toValue);

        assertThat(board.findSquareBy(from).getPiece()).isEqualTo(Piece.NONE);
        assertThat(board.findSquareBy(from).getTeam()).isEqualTo(Team.NONE);
        assertThat(board.findSquareBy(to).getPiece()).isEqualTo(Piece.KING);
        assertThat(board.findSquareBy(to).getTeam()).isEqualTo(Team.BLACK);
    }
}