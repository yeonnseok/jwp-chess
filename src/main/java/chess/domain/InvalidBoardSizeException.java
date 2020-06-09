package chess.domain;

public class InvalidBoardSizeException extends RuntimeException {
    public InvalidBoardSizeException() {
        super("보드가 올바르게 생성되지 않았습니다.");
    }
}
