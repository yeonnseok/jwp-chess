package chess.domain;

public class InvalidMoveException extends RuntimeException {
    public InvalidMoveException() {
        super("잘못된 이동입니다.");
    }
}
