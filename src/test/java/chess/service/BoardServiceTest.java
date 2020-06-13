package chess.service;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.BoardRepository;
import chess.domain.Team;
import chess.service.board.dto.BoardResponse;
import chess.service.board.dto.ScoreResponse;
import chess.service.board.dto.StateResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


class BoardServiceTest {

    private BoardService boardService;

    @Mock
    private BoardRepository boardRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        boardService = new BoardService(boardRepository);
    }

    @DisplayName("보드 생성")
    @Test
    void createBoard() {
        // given
        final Board board = BoardFactory.create();
        given(boardRepository.save(any())).willReturn(board);
        // when
        boardService.createBoard();
        // then
        verify(boardRepository).save(any());
    }

    @DisplayName("보드 목록 불러오기")
    @Test
    void getAllBoards() {
        // given
        final List<Board> boards = new ArrayList<>();
        final Board board = BoardFactory.create();
        board.setId(63L);
        boards.add(board);
        given(boardRepository.findAll()).willReturn(boards);
        // when
        final List<BoardResponse> boardResponses = boardService.findAllBoards();
        // then
        verify(boardRepository).findAll();
        assertThat(boardResponses).hasSize(1);
        assertThat(boardResponses.get(0).getId()).isEqualTo(63L);
    }

    @DisplayName("보드 하나 가져오기")
    @Test
    void getBoard() {
        // given
        final Board board = BoardFactory.create();
        board.setId(63L);
        given(boardRepository.findById(any())).willReturn(Optional.of(board));
        // when
        final BoardResponse boardResponse = boardService.findBoard(board.getId());
        // then
        verify(boardRepository).findById(eq(63L));
        assertThat(boardResponse).isNotNull();
        assertThat(boardResponse.getId()).isEqualTo(63L);
    }

    @DisplayName("보드 삭제하기")
    @Test
    void deleteBoard() {
        // given
        final Board board = BoardFactory.create();
        board.setId(63L);
        // when
        boardService.deleteBoard(board.getId());
        // then
        verify(boardRepository).deleteById(eq(63L));
    }

    @DisplayName("체스 말 움직이기")
    @Test
    void movePiece() {
        // given
        final Board board = BoardFactory.create();
        board.setId(63L);
        given(boardRepository.findById(any())).willReturn(Optional.of(board));
        // when
        boardService.movePiece(63L, "b2", "b3");
        // then
        final BoardResponse boardResponse = boardService.findBoard(63L);
        assertThat(boardResponse.getSquares().get("b2")).isEqualTo("BLANK");
        assertThat(boardResponse.getSquares().get("b3")).isEqualTo("WHITE_PAWN");
    }

    @DisplayName("처음 시작 순서 확인")
    @Test
    void startTurn() {
        // given
        final Board board = BoardFactory.create();
        board.setId(63L);
        given(boardRepository.findById(any())).willReturn(Optional.of(board));
        // when
        final StateResponse stateResponse = boardService.movePiece(63L, "b2", "b3");
        // then
        assertThat(stateResponse.getNextTurn()).isEqualTo("BLACK");
        assertThat(stateResponse.isFinished()).isFalse();
    }

    @DisplayName("게임 진행 순서 확인")
    @Test
    void playingTurn() {
        // given
        final Board board = BoardFactory.create();
        board.setId(63L);
        board.updateTurn(Team.BLACK);
        given(boardRepository.findById(any())).willReturn(Optional.of(board));
        // when
        final StateResponse stateResponse = boardService.movePiece(63L, "b7", "b5");
        // then
        assertThat(stateResponse.getNextTurn()).isEqualTo("WHITE");
        assertThat(stateResponse.isFinished()).isFalse();
        // when
    }

    @DisplayName("점수계산")
    @Test
    void calculateScore() {
        // given
        final Board board = BoardFactory.create();
        board.setId(63L);
        given(boardRepository.findById(any())).willReturn(Optional.of(board));
        // when
        final ScoreResponse scoreResponse = boardService.calculateScore(board.getId());
        // then
        verify(boardRepository).findById(eq(63L));
        assertThat(scoreResponse).isNotNull();
        assertThat(scoreResponse.getBlackScore()).isEqualTo(38);
        assertThat(scoreResponse.getWhiteScore()).isEqualTo(38);
    }
}