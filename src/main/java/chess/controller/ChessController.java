package chess.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChessController {

    public ChessController() {
    }

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String index() {
        return "index";
    }

    @GetMapping(value = "/start", produces = MediaType.TEXT_HTML_VALUE)
    public String start() {
        return "board";
    }

}
