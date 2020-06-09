package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BoardFactoryTest {

    @DisplayName("초기화된 보드 생성")
    @ParameterizedTest
    @MethodSource("provideInitialBoardState")
    void create(Position position, Piece expectedPiece, Team expectedTeam) {
        Board board = BoardFactory.create();

        assertThat(board.findSquareBy(position).getPiece()).isEqualTo(expectedPiece);
        assertThat(board.findSquareBy(position).getTeam()).isEqualTo(expectedTeam);
    }

    private static Stream<Arguments> provideInitialBoardState() {
        return Stream.of(
                Arguments.of(Position.from("a1"), Piece.ROOK, Team.WHITE),
                Arguments.of(Position.from("b1"), Piece.KNIGHT, Team.WHITE),
                Arguments.of(Position.from("c1"), Piece.BISHOP, Team.WHITE),
                Arguments.of(Position.from("d1"), Piece.QUEEN, Team.WHITE),
                Arguments.of(Position.from("e1"), Piece.KING, Team.WHITE),
                Arguments.of(Position.from("f1"), Piece.BISHOP, Team.WHITE),
                Arguments.of(Position.from("g1"), Piece.KNIGHT, Team.WHITE),
                Arguments.of(Position.from("h1"), Piece.ROOK, Team.WHITE),

                Arguments.of(Position.from("a2"), Piece.PAWN, Team.WHITE),
                Arguments.of(Position.from("b2"), Piece.PAWN, Team.WHITE),
                Arguments.of(Position.from("c2"), Piece.PAWN, Team.WHITE),
                Arguments.of(Position.from("d2"), Piece.PAWN, Team.WHITE),
                Arguments.of(Position.from("e2"), Piece.PAWN, Team.WHITE),
                Arguments.of(Position.from("f2"), Piece.PAWN, Team.WHITE),
                Arguments.of(Position.from("g2"), Piece.PAWN, Team.WHITE),
                Arguments.of(Position.from("h2"), Piece.PAWN, Team.WHITE),

                Arguments.of(Position.from("a3"), Piece.NONE, Team.NONE),
                Arguments.of(Position.from("b3"), Piece.NONE, Team.NONE),
                Arguments.of(Position.from("c3"), Piece.NONE, Team.NONE),
                Arguments.of(Position.from("d3"), Piece.NONE, Team.NONE),
                Arguments.of(Position.from("e3"), Piece.NONE, Team.NONE),
                Arguments.of(Position.from("f3"), Piece.NONE, Team.NONE),
                Arguments.of(Position.from("g3"), Piece.NONE, Team.NONE),
                Arguments.of(Position.from("h3"), Piece.NONE, Team.NONE),

                Arguments.of(Position.from("a4"), Piece.NONE, Team.NONE),
                Arguments.of(Position.from("b4"), Piece.NONE, Team.NONE),
                Arguments.of(Position.from("c4"), Piece.NONE, Team.NONE),
                Arguments.of(Position.from("d4"), Piece.NONE, Team.NONE),
                Arguments.of(Position.from("e4"), Piece.NONE, Team.NONE),
                Arguments.of(Position.from("f4"), Piece.NONE, Team.NONE),
                Arguments.of(Position.from("g4"), Piece.NONE, Team.NONE),
                Arguments.of(Position.from("h4"), Piece.NONE, Team.NONE),

                Arguments.of(Position.from("a5"), Piece.NONE, Team.NONE),
                Arguments.of(Position.from("b5"), Piece.NONE, Team.NONE),
                Arguments.of(Position.from("c5"), Piece.NONE, Team.NONE),
                Arguments.of(Position.from("d5"), Piece.NONE, Team.NONE),
                Arguments.of(Position.from("e5"), Piece.NONE, Team.NONE),
                Arguments.of(Position.from("f5"), Piece.NONE, Team.NONE),
                Arguments.of(Position.from("g5"), Piece.NONE, Team.NONE),
                Arguments.of(Position.from("h5"), Piece.NONE, Team.NONE),

                Arguments.of(Position.from("a6"), Piece.NONE, Team.NONE),
                Arguments.of(Position.from("b6"), Piece.NONE, Team.NONE),
                Arguments.of(Position.from("c6"), Piece.NONE, Team.NONE),
                Arguments.of(Position.from("d6"), Piece.NONE, Team.NONE),
                Arguments.of(Position.from("e6"), Piece.NONE, Team.NONE),
                Arguments.of(Position.from("f6"), Piece.NONE, Team.NONE),
                Arguments.of(Position.from("g6"), Piece.NONE, Team.NONE),
                Arguments.of(Position.from("h6"), Piece.NONE, Team.NONE),

                Arguments.of(Position.from("a7"), Piece.PAWN, Team.BLACK),
                Arguments.of(Position.from("b7"), Piece.PAWN, Team.BLACK),
                Arguments.of(Position.from("c7"), Piece.PAWN, Team.BLACK),
                Arguments.of(Position.from("d7"), Piece.PAWN, Team.BLACK),
                Arguments.of(Position.from("e7"), Piece.PAWN, Team.BLACK),
                Arguments.of(Position.from("f7"), Piece.PAWN, Team.BLACK),
                Arguments.of(Position.from("g7"), Piece.PAWN, Team.BLACK),
                Arguments.of(Position.from("h7"), Piece.PAWN, Team.BLACK),

                Arguments.of(Position.from("a8"), Piece.ROOK, Team.BLACK),
                Arguments.of(Position.from("b8"), Piece.KNIGHT, Team.BLACK),
                Arguments.of(Position.from("c8"), Piece.BISHOP, Team.BLACK),
                Arguments.of(Position.from("d8"), Piece.QUEEN, Team.BLACK),
                Arguments.of(Position.from("e8"), Piece.KING, Team.BLACK),
                Arguments.of(Position.from("f8"), Piece.BISHOP, Team.BLACK),
                Arguments.of(Position.from("g8"), Piece.KNIGHT, Team.BLACK),
                Arguments.of(Position.from("h8"), Piece.ROOK, Team.BLACK)
        );
    }


}