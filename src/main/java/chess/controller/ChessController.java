package chess.controller;

import chess.service.BoardService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChessController {

    private final BoardService boardService;

    public ChessController(final BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String index() {
        return "index";
    }

    @GetMapping(value = "/start", produces = MediaType.TEXT_HTML_VALUE)
    public String start(Model model) {
        model.addAttribute("squares", boardService.createBoard());
        return "board";
    }
}
