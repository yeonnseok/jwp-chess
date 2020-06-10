package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {

    @DisplayName("각 Piece 점수 확인")
    @ParameterizedTest
    @CsvSource({"KING,0", "QUEEN,9", "ROOK,5", "BISHOP,3", "KNIGHT,2.5", "PAWN,1"})
    void pieceScore(Piece piece, double expectedScore) {
        assertThat(piece.getScore()).isEqualTo(expectedScore);
    }

}