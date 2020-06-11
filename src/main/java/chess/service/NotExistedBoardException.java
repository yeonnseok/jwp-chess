package chess.service;

public class NotExistedBoardException extends RuntimeException {
    public NotExistedBoardException() {
        super("보드 정보가 존재하지 않습니다.");
    }
}
