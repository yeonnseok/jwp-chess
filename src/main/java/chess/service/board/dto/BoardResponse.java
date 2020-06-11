package chess.service.board.dto;

import chess.domain.Board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BoardResponse {
    private Long id;
    private Map<String, String> squares;

    public BoardResponse(final Long id, final Map<String, String> squares) {
        this.id = id;
        this.squares = squares;
    }

    public static BoardResponse from(final Board board) {
        Map<String, String> squares = new HashMap<>();
        board.getSquares().forEach(square -> {
            squares.put(square.getPositionValue(), square.getPieceTeam());
        });
        return new BoardResponse(board.getId(), squares);
    }

    public static List<BoardResponse> fromList(final List<Board> boards) {
        return boards.stream()
                .map(BoardResponse::from)
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public Map<String, String> getSquares() {
        return squares;
    }
}
