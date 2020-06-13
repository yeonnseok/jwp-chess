package chess.controller;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.Playing;
import chess.domain.Team;
import chess.service.BoardService;
import chess.service.board.dto.BoardResponse;
import chess.service.board.dto.ScoreResponse;
import chess.service.board.dto.StateResponse;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @DisplayName("체스 말 이동")
    @Test
    void move() throws Exception {
        // given
        final Long boardId = 1L;
        final String from = "b2";
        final String to = "b3";
        final StateResponse state = StateResponse.from(new Playing(Team.WHITE));
        given(boardService.movePiece(any(), any(), any())).willReturn(state);
        // when
        mvc.perform(put("/boards/" + boardId + "/move?" + "from=" + from + "&to=" + to))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"finished\":false,\"movedTurn\":\"WHITE\",\"nextTurn\":\"BLACK\"")));
        // then
        verify(boardService).movePiece(eq(1L), eq("b2"), eq("b3"));
    }

    @DisplayName("점수 현황 불러오기")
    @Test
    void score() throws Exception {
        // given
        final Long boardId = 1L;
        final ScoreResponse score = new ScoreResponse(38, 38);
        given(boardService.calculateScore(any())).willReturn(score);
        // when
        mvc.perform(get("/boards/" + boardId + "/score"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"whiteScore\":38.0,\"blackScore\":38.0")));
        // then
        verify(boardService).calculateScore(eq(1L));
    }
}