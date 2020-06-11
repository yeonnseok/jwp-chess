package chess.controller;

import chess.service.BoardService;
import chess.service.board.dto.BoardResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    public BoardController(final BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping
    public ResponseEntity<Void> create() {
        final BoardResponse boardResponse = boardService.createBoard();
        return ResponseEntity.created(URI.create("/boards/" + boardResponse.getId())).build();
    }

    @GetMapping
    public ResponseEntity<List<BoardResponse>> getAll() {
        final List<BoardResponse> boardResponses = boardService.findAllBoards();
        return ResponseEntity.ok().body(boardResponses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.noContent().build();
    }

}
