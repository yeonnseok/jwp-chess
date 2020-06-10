package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SquareTest {

    @DisplayName("정적 팩토리 메서드로 생성")
    @Test
    void construct() {
        Square square = Square.of(Position.from("a1"), Piece.ROOK, Team.WHITE);

        assertThat(square).isNotNull();
        assertThat(square.getPosition().getFile()).isEqualTo('a');
        assertThat(square.getPosition().getRank()).isEqualTo(1);
        assertThat(square.getPiece().name()).isEqualTo("ROOK");
        assertThat(square.getTeam().name()).isEqualTo("WHITE");
    }

}