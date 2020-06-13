package chess.controller;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.service.BoardService;
import chess.service.board.dto.BoardResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class BoardControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BoardService boardService;

    @DisplayName("보드 생성")
    @Test
    void create() throws Exception {
        // given
        final Board board = BoardFactory.create();
        final BoardResponse boardResponse = BoardResponse.from(board);
        given(boardService.createBoard()).willReturn(boardResponse);
        // when
        mvc.perform(post("/boards/"))
                .andExpect(status().isCreated());
    }

    @DisplayName("보드 목록 조회")
    @Test
    void getAllBoard() throws Exception {
        // given
        final List<BoardResponse> boardResponses = new ArrayList<>();
        final Board board = BoardFactory.create();
        boardResponses.add(BoardResponse.from(board));
        given(boardService.findAllBoards()).willReturn(boardResponses);
        // when
        mvc.perform(get("/boards"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"a1\":\"WHITE_ROOK\"")))
                .andExpect(content().string(containsString("\"b1\":\"WHITE_KNIGHT\"")))
                .andExpect(content().string(containsString("\"c1\":\"WHITE_BISHOP\"")))
                .andExpect(content().string(containsString("\"d1\":\"WHITE_QUEEN\"")))
                .andExpect(content().string(containsString("\"e1\":\"WHITE_KING\"")));
    }

    @DisplayName("보드 단건 조회")
    @Test
    void getBoard() throws Exception {
        // given
        final Board board = BoardFactory.create();
        final BoardResponse boardResponse = BoardResponse.from(board);
        given(boardService.findBoard(any())).willReturn(boardResponse);
        // when
        mvc.perform(get("/boards/{id}", 5L))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"a1\":\"WHITE_ROOK\"")))
                .andExpect(content().string(containsString("\"b1\":\"WHITE_KNIGHT\"")))
                .andExpect(content().string(containsString("\"a8\":\"BLACK_ROOK\"")))
                .andExpect(content().string(containsString("\"b8\":\"BLACK_KNIGHT\"")))
                .andExpect(content().string(containsString("\"d5\":\"BLANK\"")))
                .andExpect(content().string(containsString("\"f5\":\"BLANK\"")));
    }

    @DisplayName("보드 삭제")
    @Test
    void destroy() throws Exception {
        // when
        mvc.perform(delete("/boards/{id}", 5L))
                .andExpect(status().isNoContent());
        // given
        verify(boardService).deleteBoard(eq(5L));
    }


}