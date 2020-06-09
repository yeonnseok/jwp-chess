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
        board.updateSquareBy(Position.from("f3"), Piece.KNIGHT, Team.BLACK);
    }

    @DisplayName("폰 이동 불가능")
    @ParameterizedTest
    @ValueSource(strings = {"c1", "a3", "h7"})
    void invalidMovePawn(String toValue) {
        assertThatThrownBy(() -> {
            Position from = Position.from("e2");
            Position to = Position.from(toValue);

            board.move(from, to);
        }).isInstanceOf(InvalidMoveException.class);
    }

    @DisplayName("폰 이동")
    @ParameterizedTest
    @ValueSource(strings = {"e3", "e4", "f3"})
    void movePawn(String toValue) {
        Position from = Position.from("e2");
        Position to = Position.from(toValue);

        board.move(from, to);

        assertThat(board.findSquareBy(from).getPiece()).isEqualTo(Piece.NONE);
        assertThat(board.findSquareBy(from).getTeam()).isEqualTo(Team.NONE);
        assertThat(board.findSquareBy(to).getPiece()).isEqualTo(Piece.PAWN);
        assertThat(board.findSquareBy(to).getTeam()).isEqualTo(Team.WHITE);
    }

}