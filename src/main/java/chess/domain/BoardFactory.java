package chess.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class BoardFactory {

    public static Board create() {
        final List<Square> squares = new ArrayList<>();
        squares.addAll(initWhiteTeam());
        squares.addAll(initBlank());
        squares.addAll(initBlackTeam());
        return new Board(squares);
    }

    private static List<Square> initWhiteTeam() {
        return new ArrayList<>(
                Arrays.asList(
                        Square.of(Position.from("a1"), Piece.ROOK, Team.WHITE),
                        Square.of(Position.from("b1"), Piece.KNIGHT, Team.WHITE),
                        Square.of(Position.from("c1"), Piece.BISHOP, Team.WHITE),
                        Square.of(Position.from("d1"), Piece.QUEEN, Team.WHITE),
                        Square.of(Position.from("e1"), Piece.KING, Team.WHITE),
                        Square.of(Position.from("f1"), Piece.BISHOP, Team.WHITE),
                        Square.of(Position.from("g1"), Piece.KNIGHT, Team.WHITE),
                        Square.of(Position.from("h1"), Piece.ROOK, Team.WHITE),

                        Square.of(Position.from("a2"), Piece.PAWN, Team.WHITE),
                        Square.of(Position.from("b2"), Piece.PAWN, Team.WHITE),
                        Square.of(Position.from("c2"), Piece.PAWN, Team.WHITE),
                        Square.of(Position.from("d2"), Piece.PAWN, Team.WHITE),
                        Square.of(Position.from("e2"), Piece.PAWN, Team.WHITE),
                        Square.of(Position.from("f2"), Piece.PAWN, Team.WHITE),
                        Square.of(Position.from("g2"), Piece.PAWN, Team.WHITE),
                        Square.of(Position.from("h2"), Piece.PAWN, Team.WHITE)
                )
        );
    }

    private static List<Square> initBlank() {
        return new ArrayList<>(
                Arrays.asList(
                        Square.of(Position.from("a3"), Piece.NONE, Team.NONE),
                        Square.of(Position.from("b3"), Piece.NONE, Team.NONE),
                        Square.of(Position.from("c3"), Piece.NONE, Team.NONE),
                        Square.of(Position.from("d3"), Piece.NONE, Team.NONE),
                        Square.of(Position.from("e3"), Piece.NONE, Team.NONE),
                        Square.of(Position.from("f3"), Piece.NONE, Team.NONE),
                        Square.of(Position.from("g3"), Piece.NONE, Team.NONE),
                        Square.of(Position.from("h3"), Piece.NONE, Team.NONE),

                        Square.of(Position.from("a4"), Piece.NONE, Team.NONE),
                        Square.of(Position.from("b4"), Piece.NONE, Team.NONE),
                        Square.of(Position.from("c4"), Piece.NONE, Team.NONE),
                        Square.of(Position.from("d4"), Piece.NONE, Team.NONE),
                        Square.of(Position.from("e4"), Piece.NONE, Team.NONE),
                        Square.of(Position.from("f4"), Piece.NONE, Team.NONE),
                        Square.of(Position.from("g4"), Piece.NONE, Team.NONE),
                        Square.of(Position.from("h4"), Piece.NONE, Team.NONE),

                        Square.of(Position.from("a5"), Piece.NONE, Team.NONE),
                        Square.of(Position.from("b5"), Piece.NONE, Team.NONE),
                        Square.of(Position.from("c5"), Piece.NONE, Team.NONE),
                        Square.of(Position.from("d5"), Piece.NONE, Team.NONE),
                        Square.of(Position.from("e5"), Piece.NONE, Team.NONE),
                        Square.of(Position.from("f5"), Piece.NONE, Team.NONE),
                        Square.of(Position.from("g5"), Piece.NONE, Team.NONE),
                        Square.of(Position.from("h5"), Piece.NONE, Team.NONE),

                        Square.of(Position.from("a6"), Piece.NONE, Team.NONE),
                        Square.of(Position.from("b6"), Piece.NONE, Team.NONE),
                        Square.of(Position.from("c6"), Piece.NONE, Team.NONE),
                        Square.of(Position.from("d6"), Piece.NONE, Team.NONE),
                        Square.of(Position.from("e6"), Piece.NONE, Team.NONE),
                        Square.of(Position.from("f6"), Piece.NONE, Team.NONE),
                        Square.of(Position.from("g6"), Piece.NONE, Team.NONE),
                        Square.of(Position.from("h6"), Piece.NONE, Team.NONE)
                )
        );
    }

    private static List<Square> initBlackTeam() {
        return new ArrayList<>(
                Arrays.asList(
                        Square.of(Position.from("a7"), Piece.PAWN, Team.BLACK),
                        Square.of(Position.from("b7"), Piece.PAWN, Team.BLACK),
                        Square.of(Position.from("c7"), Piece.PAWN, Team.BLACK),
                        Square.of(Position.from("d7"), Piece.PAWN, Team.BLACK),
                        Square.of(Position.from("e7"), Piece.PAWN, Team.BLACK),
                        Square.of(Position.from("f7"), Piece.PAWN, Team.BLACK),
                        Square.of(Position.from("g7"), Piece.PAWN, Team.BLACK),
                        Square.of(Position.from("h7"), Piece.PAWN, Team.BLACK),

                        Square.of(Position.from("a8"), Piece.ROOK, Team.BLACK),
                        Square.of(Position.from("b8"), Piece.KNIGHT, Team.BLACK),
                        Square.of(Position.from("c8"), Piece.BISHOP, Team.BLACK),
                        Square.of(Position.from("d8"), Piece.QUEEN, Team.BLACK),
                        Square.of(Position.from("e8"), Piece.KING, Team.BLACK),
                        Square.of(Position.from("f8"), Piece.BISHOP, Team.BLACK),
                        Square.of(Position.from("g8"), Piece.KNIGHT, Team.BLACK),
                        Square.of(Position.from("h8"), Piece.ROOK, Team.BLACK)
                )
        );
    }

    public static Board createEmpty() {
        final List<Square> squares = new ArrayList<>();
        IntStream.rangeClosed(1, 8)
                .forEach(i -> IntStream.rangeClosed(1, 8)
                        .forEach(j -> squares.add(Square.of(Position.of(i, j), Piece.NONE, Team.NONE)))
                );
        return new Board(squares);
    }
}
