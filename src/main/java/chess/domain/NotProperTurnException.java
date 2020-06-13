package chess.domain;

public class NotProperTurnException extends RuntimeException {
    public NotProperTurnException() {
        super("올바른 순서가 아닙니다.");
    }
}
