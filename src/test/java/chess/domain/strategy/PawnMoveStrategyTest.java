package chess.domain.strategy;

import chess.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PawnMoveStrategyTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = BoardFactory.createEmpty();
        board.updateSquareBy(Position.from("e2"), Piece.PAWN, Team.WHITE);
        board.updateSquareBy(Position.from("g4"), Piece.PAWN, Team.BLACK);
        board.updateSquareBy(Position.from("f3"), Piece.KNIGHT, Team.BLACK);
    }

    @DisplayName("폰 이동 불가능")
    @ParameterizedTest
    @ValueSource(strings = {"c1", "a3", "h7"})
    void invalidMovePawn(String toValue) {
        assertThatThrownBy(() -> {
            board.move("e2", toValue);
        }).isInstanceOf(InvalidMoveException.class);
    }

    @DisplayName("폰 이동 - white")
    @ParameterizedTest
    @ValueSource(strings = {"e3", "e4", "f3"})
    void moveWhitePawn(String toValue) {
        Position from = Position.from("e2");
        Position to = Position.from(toValue);

        board.move("e2", toValue);

        assertThat(board.findSquareBy(from).getPiece()).isEqualTo(Piece.NONE);
        assertThat(board.findSquareBy(from).getTeam()).isEqualTo(Team.NONE);
        assertThat(board.findSquareBy(to).getPiece()).isEqualTo(Piece.PAWN);
        assertThat(board.findSquareBy(to).getTeam()).isEqualTo(Team.WHITE);
    }

    @DisplayName("폰 이동 - black")
    @ParameterizedTest
    @ValueSource(strings = {"g3"})
    void moveBlackPawn(String toValue) {
        Position from = Position.from("g4");
        Position to = Position.from(toValue);
        board.updateTurn(Team.BLACK);

        board.move("g4", toValue);

        assertThat(board.findSquareBy(from).getPiece()).isEqualTo(Piece.NONE);
        assertThat(board.findSquareBy(from).getTeam()).isEqualTo(Team.NONE);
        assertThat(board.findSquareBy(to).getPiece()).isEqualTo(Piece.PAWN);
        assertThat(board.findSquareBy(to).getTeam()).isEqualTo(Team.BLACK);
    }

}