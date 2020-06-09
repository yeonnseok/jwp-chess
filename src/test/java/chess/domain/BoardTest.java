package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BoardTest {

    @DisplayName("보드 생성시 square 갯수가 64개가 아니면 예외발생")
    @Test
    void constructWithInvalidAttributes() {
        assertThatThrownBy(() -> {
            final List<Square> rawBoard = new ArrayList<>();

            Board board = new Board(rawBoard);
        }).isInstanceOf(InvalidBoardSizeException.class);
    }

    @DisplayName("보드 생성")
    @Test
    void construct() {
        final List<Square> rawBoard = new ArrayList<>();
        IntStream.range(0, 64)
                .forEach(i -> rawBoard.add(Square.of(Position.from("a1"), Piece.KING, Team.WHITE)));

        Board board = new Board(rawBoard);

        assertThat(board).isNotNull();
        assertThat(board.getSquares()).hasSize(64);
    }

    @DisplayName("포지션으로 찾고자하는 스퀘어가 없을 경우 예외 발생")
    @Test
    void notFindSquareByPosition() {
        assertThatThrownBy(() -> {
            final List<Square> rawBoard = new ArrayList<>();
            IntStream.range(0, 64)
                    .forEach(i -> rawBoard.add(Square.of(null, null, null)));
            Board board = new Board(rawBoard);

            board.findSquareBy(Position.from("a1"));
        }).isInstanceOf(NoSuchSquareException.class);
    }

    @DisplayName("포지션으로 스퀘어 찾기")
    @ParameterizedTest
    @MethodSource("provideSquares")
    void findSquareByPosition(Position position, Square expectedSquare) {
        Board board = BoardFactory.create();
        assertThat(board.findSquareBy(position)).isEqualTo(expectedSquare);
    }

    private static Stream<Arguments> provideSquares() {
        return Stream.of(
                Arguments.of(Position.from("a1"), Square.of(Position.from("a1"), Piece.ROOK, Team.WHITE)),
                Arguments.of(Position.from("b1"), Square.of(Position.from("b1"), Piece.KNIGHT, Team.WHITE)),
                Arguments.of(Position.from("c1"), Square.of(Position.from("c1"), Piece.BISHOP, Team.WHITE)),
                Arguments.of(Position.from("d1"), Square.of(Position.from("d1"), Piece.QUEEN, Team.WHITE)),
                Arguments.of(Position.from("e1"), Square.of(Position.from("e1"), Piece.KING, Team.WHITE)),
                Arguments.of(Position.from("f1"), Square.of(Position.from("f1"), Piece.BISHOP, Team.WHITE)),
                Arguments.of(Position.from("g1"), Square.of(Position.from("g1"), Piece.KNIGHT, Team.WHITE)),
                Arguments.of(Position.from("h1"), Square.of(Position.from("h1"), Piece.ROOK, Team.WHITE)),

                Arguments.of(Position.from("a2"), Square.of(Position.from("a2"), Piece.PAWN, Team.WHITE)),
                Arguments.of(Position.from("b2"), Square.of(Position.from("b2"), Piece.PAWN, Team.WHITE)),
                Arguments.of(Position.from("c2"), Square.of(Position.from("c2"), Piece.PAWN, Team.WHITE)),
                Arguments.of(Position.from("d2"), Square.of(Position.from("d2"), Piece.PAWN, Team.WHITE)),
                Arguments.of(Position.from("e2"), Square.of(Position.from("e2"), Piece.PAWN, Team.WHITE)),
                Arguments.of(Position.from("f2"), Square.of(Position.from("f2"), Piece.PAWN, Team.WHITE)),
                Arguments.of(Position.from("g2"), Square.of(Position.from("g2"), Piece.PAWN, Team.WHITE)),
                Arguments.of(Position.from("h2"), Square.of(Position.from("h2"), Piece.PAWN, Team.WHITE)),

                Arguments.of(Position.from("a3"), Square.of(Position.from("a3"), Piece.NONE, Team.NONE)),
                Arguments.of(Position.from("b3"), Square.of(Position.from("b3"), Piece.NONE, Team.NONE)),
                Arguments.of(Position.from("c3"), Square.of(Position.from("c3"), Piece.NONE, Team.NONE)),
                Arguments.of(Position.from("d3"), Square.of(Position.from("d3"), Piece.NONE, Team.NONE)),
                Arguments.of(Position.from("e3"), Square.of(Position.from("e3"), Piece.NONE, Team.NONE)),
                Arguments.of(Position.from("f3"), Square.of(Position.from("f3"), Piece.NONE, Team.NONE)),
                Arguments.of(Position.from("g3"), Square.of(Position.from("g3"), Piece.NONE, Team.NONE)),
                Arguments.of(Position.from("h3"), Square.of(Position.from("h3"), Piece.NONE, Team.NONE)),

                Arguments.of(Position.from("a4"), Square.of(Position.from("a4"), Piece.NONE, Team.NONE)),
                Arguments.of(Position.from("b4"), Square.of(Position.from("b4"), Piece.NONE, Team.NONE)),
                Arguments.of(Position.from("c4"), Square.of(Position.from("c4"), Piece.NONE, Team.NONE)),
                Arguments.of(Position.from("d4"), Square.of(Position.from("d4"), Piece.NONE, Team.NONE)),
                Arguments.of(Position.from("e4"), Square.of(Position.from("e4"), Piece.NONE, Team.NONE)),
                Arguments.of(Position.from("f4"), Square.of(Position.from("f4"), Piece.NONE, Team.NONE)),
                Arguments.of(Position.from("g4"), Square.of(Position.from("g4"), Piece.NONE, Team.NONE)),
                Arguments.of(Position.from("h4"), Square.of(Position.from("h4"), Piece.NONE, Team.NONE)),

                Arguments.of(Position.from("a5"), Square.of(Position.from("a5"), Piece.NONE, Team.NONE)),
                Arguments.of(Position.from("b5"), Square.of(Position.from("b5"), Piece.NONE, Team.NONE)),
                Arguments.of(Position.from("c5"), Square.of(Position.from("c5"), Piece.NONE, Team.NONE)),
                Arguments.of(Position.from("d5"), Square.of(Position.from("d5"), Piece.NONE, Team.NONE)),
                Arguments.of(Position.from("e5"), Square.of(Position.from("e5"), Piece.NONE, Team.NONE)),
                Arguments.of(Position.from("f5"), Square.of(Position.from("f5"), Piece.NONE, Team.NONE)),
                Arguments.of(Position.from("g5"), Square.of(Position.from("g5"), Piece.NONE, Team.NONE)),
                Arguments.of(Position.from("h5"), Square.of(Position.from("h5"), Piece.NONE, Team.NONE)),

                Arguments.of(Position.from("a6"), Square.of(Position.from("a6"), Piece.NONE, Team.NONE)),
                Arguments.of(Position.from("b6"), Square.of(Position.from("b6"), Piece.NONE, Team.NONE)),
                Arguments.of(Position.from("c6"), Square.of(Position.from("c6"), Piece.NONE, Team.NONE)),
                Arguments.of(Position.from("d6"), Square.of(Position.from("d6"), Piece.NONE, Team.NONE)),
                Arguments.of(Position.from("e6"), Square.of(Position.from("e6"), Piece.NONE, Team.NONE)),
                Arguments.of(Position.from("f6"), Square.of(Position.from("f6"), Piece.NONE, Team.NONE)),
                Arguments.of(Position.from("g6"), Square.of(Position.from("g6"), Piece.NONE, Team.NONE)),
                Arguments.of(Position.from("h6"), Square.of(Position.from("h6"), Piece.NONE, Team.NONE)),

                Arguments.of(Position.from("a7"), Square.of(Position.from("a7"), Piece.PAWN, Team.BLACK)),
                Arguments.of(Position.from("b7"), Square.of(Position.from("b7"), Piece.PAWN, Team.BLACK)),
                Arguments.of(Position.from("c7"), Square.of(Position.from("c7"), Piece.PAWN, Team.BLACK)),
                Arguments.of(Position.from("d7"), Square.of(Position.from("d7"), Piece.PAWN, Team.BLACK)),
                Arguments.of(Position.from("e7"), Square.of(Position.from("e7"), Piece.PAWN, Team.BLACK)),
                Arguments.of(Position.from("f7"), Square.of(Position.from("f7"), Piece.PAWN, Team.BLACK)),
                Arguments.of(Position.from("g7"), Square.of(Position.from("g7"), Piece.PAWN, Team.BLACK)),
                Arguments.of(Position.from("h7"), Square.of(Position.from("h7"), Piece.PAWN, Team.BLACK)),

                Arguments.of(Position.from("a8"), Square.of(Position.from("a8"), Piece.ROOK, Team.BLACK)),
                Arguments.of(Position.from("b8"), Square.of(Position.from("b8"), Piece.KNIGHT, Team.BLACK)),
                Arguments.of(Position.from("c8"), Square.of(Position.from("c8"), Piece.BISHOP, Team.BLACK)),
                Arguments.of(Position.from("d8"), Square.of(Position.from("d8"), Piece.QUEEN, Team.BLACK)),
                Arguments.of(Position.from("e8"), Square.of(Position.from("e8"), Piece.KING, Team.BLACK)),
                Arguments.of(Position.from("f8"), Square.of(Position.from("f8"), Piece.BISHOP, Team.BLACK)),
                Arguments.of(Position.from("g8"), Square.of(Position.from("g8"), Piece.KNIGHT, Team.BLACK)),
                Arguments.of(Position.from("h8"), Square.of(Position.from("h8"), Piece.ROOK, Team.BLACK))
        );
    }

}