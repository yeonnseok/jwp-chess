package chess.service;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.BoardRepository;
import chess.domain.State;
import chess.service.board.dto.BoardResponse;
import chess.service.board.dto.StateResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(final BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public BoardResponse createBoard() {
        Board board = BoardFactory.create();
        boardRepository.save(board);  // 영속화
        return BoardResponse.from(board);
    }

    public List<BoardResponse> findAllBoards() {
        List<Board> boards = boardRepository.findAll();
        return BoardResponse.fromList(boards);
    }

    public BoardResponse findBoard(final Long id) {
        final Board board = boardRepository.findById(id)
                .orElseThrow(NotExistedBoardException::new);
        return BoardResponse.from(board);
    }

    public void deleteBoard(final Long id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public StateResponse movePiece(final Long id, final String from, final String to) {
        final Board board = boardRepository.findById(id)
                .orElseThrow(NotExistedBoardException::new);
        State state = board.move(from, to);
        board.updateTurn(state.getNextTurn());
        return StateResponse.from(state);
    }
}
